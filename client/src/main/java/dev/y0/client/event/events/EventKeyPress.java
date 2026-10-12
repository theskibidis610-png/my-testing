package dev.y0.client.event.events;

import dev.y0.client.event.Event;

public class EventKeyPress extends Event {
    private final int key;
    private final int scanCode;

    public EventKeyPress(int key, int scanCode) {
        this.key = key;
        this.scanCode = scanCode;
    }

    public int getKey() {
        return key;
    }

    public int getScanCode() {
        return scanCode;
    }
}
