package dev.y0.client.module;

import dev.y0.client.event.EventHandler;
import dev.y0.client.event.events.EventKeyPress;
import dev.y0.client.module.combat.*;
import dev.y0.client.module.misc.*;
import dev.y0.client.module.movement.*;
import dev.y0.client.module.player.*;
import dev.y0.client.module.render.*;
import dev.y0.client.module.world.*;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void init() {
        // Combat (43)
        add(new AimAssist());
        add(new Anchor());
        add(new AntiAim());
        add(new AntiBot());
        add(new AntiKnockback());
        add(new Aura());
        add(new AutoAnchor());
        add(new AutoBow());
        add(new AutoClicker());
        add(new AutoCrystal());
        add(new AutoGapple());
        add(new AutoPot());
        add(new AutoProjectile());
        add(new AutoShield());
        add(new AutoSword());
        add(new AutoTNT());
        add(new AutoTotem());
        add(new AutoWeapon());
        add(new AutoWeb());
        add(new BackTrack());
        add(new BedAura());
        add(new Blocker());
        add(new BowAimbot());
        add(new BowSpam());
        add(new CevBreaker());
        add(new ClickTP());
        add(new CombatSurround());
        add(new Criticals());
        add(new CrystalOptimize());
        add(new FastBow());
        add(new HitSelect());
        add(new Hitbox());
        add(new InfiniteAura());
        add(new KillAura());
        add(new Offhand());
        add(new Reach());
        add(new Replenish());
        add(new SmartCrit());
        add(new TPAura());
        add(new TargetStrafe());
        add(new TriggerBot());
        add(new Velocity());
        add(new WTap());

        // Movement (45)
        add(new AirJump());
        add(new AntiPush());
        add(new AntiVoid());
        add(new AutoJump());
        add(new AutoSprint());
        add(new Blink());
        add(new BoatFly());
        add(new Bounce());
        add(new Burrow());
        add(new Clip());
        add(new Crawl());
        add(new Dash());
        add(new ElytraFly());
        add(new EntitySpeed());
        add(new FastClimb());
        add(new FastSneak());
        add(new FastSwim());
        add(new Flight());
        add(new Fly());
        add(new Glide());
        add(new GuiMove());
        add(new HighJump());
        add(new IceSpeed());
        add(new Jesus());
        add(new Jetpack());
        add(new LongJump());
        add(new NoClip());
        add(new NoJumpDelay());
        add(new NoSlow());
        add(new OrbitMove());
        add(new Parkour());
        add(new Phase());
        add(new ReverseStep());
        add(new SafeWalk());
        add(new Scaffold());
        add(new Slime());
        add(new Sneak());
        add(new Speed());
        add(new Spider());
        add(new Sprint());
        add(new Step());
        add(new Strafe());
        add(new TridentBoost());
        add(new WallClimb());
        add(new WebMove());

        // Render (45)
        add(new AmbientLight());
        add(new Animations());
        add(new AntiOverlay());
        add(new Aspect());
        add(new BlockHighlight());
        add(new BlockOverlay());
        add(new Breadcrumbs());
        add(new CameraClip());
        add(new Chams());
        add(new CityESP());
        add(new ClickESP());
        add(new Crosshair());
        add(new CustomFOV());
        add(new ESP());
        add(new EntityGlow());
        add(new FogRemover());
        add(new FreeLook());
        add(new Fullbright());
        add(new HUD());
        add(new HandESP());
        add(new HoleESP());
        add(new ItemPhysics());
        add(new JumpCircle());
        add(new LogoutSpot());
        add(new LowFire());
        add(new MobOwner());
        add(new NameTags());
        add(new NewChunks());
        add(new NoBob());
        add(new NoFog());
        add(new NoHurtCam());
        add(new NoRender());
        add(new NoWeather());
        add(new Particles());
        add(new Radar());
        add(new Skeleton());
        add(new SkyColor());
        add(new StorageESP());
        add(new TNTTimer());
        add(new TimeChanger());
        add(new Tracers());
        add(new TunnelVision());
        add(new ViewClip());
        add(new Waypoints());
        add(new Zoom());

        // Player (43)
        add(new AntiAFK());
        add(new AntiHunger());
        add(new AntiLevitation());
        add(new AutoArmor());
        add(new AutoDisconnect());
        add(new AutoDrop());
        add(new AutoEat());
        add(new AutoEnchant());
        add(new AutoFill());
        add(new AutoGap());
        add(new AutoLog());
        add(new AutoMend());
        add(new AutoMount());
        add(new AutoRespawn());
        add(new AutoSneak());
        add(new AutoTool());
        add(new AutoWalk());
        add(new BindHotbar());
        add(new ChestStealer());
        add(new DeathCoords());
        add(new ExperienceAlert());
        add(new ExtendReach());
        add(new FakeCreative());
        add(new FastBreak());
        add(new FastPlace());
        add(new Freecam());
        add(new GhostHand());
        add(new HeadRotation());
        add(new InventorySorter());
        add(new ItemSaver());
        add(new MCF());
        add(new MiddleClick());
        add(new MultiTask());
        add(new NoFall());
        add(new NoRotate());
        add(new PacketFly());
        add(new PacketMine());
        add(new Portals());
        add(new Regen());
        add(new SkinBlink());
        add(new TowerUp());
        add(new XCarry());
        add(new YawLock());

        // World (37)
        add(new AirPlace());
        add(new AntiGhostBlock());
        add(new AutoBuild());
        add(new AutoFarm());
        add(new AutoMine());
        add(new AutoSign());
        add(new AutoTrap());
        add(new AutoWither());
        add(new BedBomb());
        add(new BlockBreaker());
        add(new BlockPlace());
        add(new BuildHelper());
        add(new ChestAura());
        add(new Flatten());
        add(new HoleFiller());
        add(new Ignite());
        add(new InstantBreak());
        add(new LiquidFiller());
        add(new NoGravity());
        add(new NoInteract());
        add(new Nuker());
        add(new PacketCancel());
        add(new PistonPush());
        add(new Prune());
        add(new Quarry());
        add(new SeedFinder());
        add(new SpeedMine());
        add(new StashFinder());
        add(new Surround());
        add(new Terraformer());
        add(new Timer());
        add(new TorchPlace());
        add(new TreeCutter());
        add(new VeinMiner());
        add(new WaterBucket());
        add(new WitherBuilder());
        add(new XRay());

        // Misc (37)
        add(new AntiCrash());
        add(new AntiPacketKick());
        add(new AntiScreenshot());
        add(new AntiVanish());
        add(new AutoFish());
        add(new AutoGG());
        add(new AutoReconnect());
        add(new AutoRegister());
        add(new AutoReply());
        add(new AutoTPA());
        add(new BetterChat());
        add(new BookBot());
        add(new ChatSuffix());
        add(new ClickGUIModule());
        add(new Coords());
        add(new Disabler());
        add(new DiscordRPC());
        add(new ExtraTab());
        add(new FakePlayer());
        add(new GameTweaks());
        add(new IRC());
        add(new InventoryDupe());
        add(new MountBypass());
        add(new NameProtect());
        add(new NoSoundLag());
        add(new PacketLogger());
        add(new Panic());
        add(new PingSpoof());
        add(new PortalGodMode());
        add(new SelfDestruct());
        add(new ServerPlugins());
        add(new SkinChanger());
        add(new Spammer());
        add(new TPSSync());
        add(new Timestamps());
        add(new Unfocused());
        add(new Watermark());
    }

    public void add(Module module) {
        modules.add(module);
    }

    @EventHandler
    public void onKeyPress(EventKeyPress event) {
        if (MinecraftClient.getInstance().currentScreen != null) return;

        for (Module module : modules) {
            if (module.getKeyBind() == event.getKey()) {
                module.toggle();
            }
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Module getByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
