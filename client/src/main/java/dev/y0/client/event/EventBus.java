package dev.y0.client.event;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final Map<Class<? extends Event>, List<Listener>> listeners = new ConcurrentHashMap<>();

    public void register(Object subscriber) {
        for (Method method : subscriber.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class)) continue;
            if (method.getParameterCount() != 1) continue;

            Class<?> paramType = method.getParameterTypes()[0];
            if (!Event.class.isAssignableFrom(paramType)) continue;

            method.setAccessible(true);
            @SuppressWarnings("unchecked")
            Class<? extends Event> eventType = (Class<? extends Event>) paramType;
            int priority = method.getAnnotation(EventHandler.class).priority();

            listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                    .add(new Listener(subscriber, method, priority));

            listeners.get(eventType).sort(Comparator.comparingInt(l -> -l.priority));
        }
    }

    public void unregister(Object subscriber) {
        listeners.values().forEach(list ->
                list.removeIf(l -> l.subscriber == subscriber));
    }

    public <T extends Event> T post(T event) {
        List<Listener> list = listeners.get(event.getClass());
        if (list == null) return event;

        for (Listener listener : list) {
            try {
                listener.method.invoke(listener.subscriber, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return event;
    }

    private record Listener(Object subscriber, Method method, int priority) {}
}
