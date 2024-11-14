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

	protected void setAttributes(AttributeInstance instance, Difficulty difficuly) {
		instance.setBaseValue(Attribute.GENERIC_MAX_HEALTH, 800.0 * difficuly.level);
		instance.setBaseValue(Attribute.GENERIC_MOVEMENT_SPEED, 0.1 * difficuly.level);
		instance.setBaseValue(Attribute.GENERIC_ATTACK_DAMAGE, 12.0 * difficuly.level);
	}
}
