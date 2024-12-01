package ourstory.bosses;

import java.util.HashMap;
import java.util.Map;

public final class BossManager {
    private static BossManager instance;

    public static synchronized BossManager getInstance() {
        if (instance == null) {
            instance = new BossManager();
        }
        return instance;
    }

    /* TODO: Make map persistent through reloads / restarts */
    private final Map<String, AbstractBoss> registered_bosses;

    private BossManager() {
        this.registered_bosses = new HashMap<>();
    }

    public void registerBoss(AbstractBoss boss) {
        this.registered_bosses.put(boss.getName(), boss);
    }

    public void unregisterBoss(AbstractBoss boss) {
        this.registered_bosses.remove(boss.getName());
    }

    public AbstractBoss getBoss(String name) {
        return this.registered_bosses.get(name);
    }

    public Map<String, AbstractBoss> getRegisteredBosses() {
        return this.registered_bosses;
    }
}
