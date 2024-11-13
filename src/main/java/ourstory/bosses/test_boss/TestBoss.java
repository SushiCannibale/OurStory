package ourstory.bosses.test_boss;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import ourstory.bosses.AbstractBoss;
import ourstory.bosses.state.AbstractState;
import ourstory.bosses.state.IdleState;

public class TestBoss extends AbstractBoss {
	public TestBoss() {
		super("TestBoss", EntityType.WITHER_SKELETON);
	}
}
