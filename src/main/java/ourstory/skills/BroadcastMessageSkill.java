package ourstory.skills;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class BroadcastMessageSkill extends AbstractSkill {
    public BroadcastMessageSkill() {
        super(20);
    }

    @Override
    public void run() {
        for (LivingEntity target : targets) {
            if (target instanceof Player player) {
                player.sendMessage("Hahaa !!! you have been broadcasted!");
            }
        }
    }
}
