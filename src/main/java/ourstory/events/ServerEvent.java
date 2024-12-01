package ourstory.events;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ourstory.bosses.AbstractBoss;
import ourstory.bosses.BossManager;

public class ServerEvent implements Listener {
    @EventHandler
    public void onServerTick(ServerTickStartEvent event) {
        BossManager manager = BossManager.getInstance();
        for (AbstractBoss boss : manager.getRegisteredBosses().values()) {
            boss.tick();
        }
    }
}
