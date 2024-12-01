package ourstory.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import ourstory.bosses.AbstractBoss;
import ourstory.bosses.BossManager;
import ourstory.storage.Storage;

public final class BossEvent implements Listener {
    @EventHandler
    public void onBossSpawns(EntitySpawnEvent event) {
        if (event.getEntity().getMetadata("isBoss").isEmpty()) {
            return;
        }
        BossManager.getInstance().getRegisteredBosses().values().forEach(AbstractBoss::onSpawn);
    }

    @EventHandler
    public void onBossHit(EntityDamageByEntityEvent event) {
        if (event.getEntity().getMetadata("isBoss").isEmpty()) {
            return;
        }
        BossManager.getInstance().getRegisteredBosses().values().forEach(AbstractBoss::onHit);
    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent event) {
        if (event.getEntity().getMetadata(AbstractBoss.METADATA).isEmpty()) {
            return;
        }

        BossManager.getInstance().getRegisteredBosses().values().forEach(AbstractBoss::onDeath);
    }
}
