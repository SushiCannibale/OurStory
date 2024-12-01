package ourstory.bosses.test_boss;

import org.bukkit.entity.LivingEntity;
import ourstory.bosses.AbstractBoss;
import ourstory.skills.AbstractSkill;
import ourstory.skills.BroadcastMessageSkill;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class TestBoss extends AbstractBoss {
	private static Map<Predicate<AbstractBoss>, AbstractSkill> getSkills() {
		Map<Predicate<AbstractBoss>, AbstractSkill> skills = new HashMap<>();

		skills.put(boss -> boss.getEntity().getHealth() <= 10.0f, new BroadcastMessageSkill());

		return skills;
	}

	public TestBoss(LivingEntity entity) {
		super("TestBoss", entity, getSkills());
	}

	@Override
	public void onSpawn() {

	}

	@Override
	public void onDeath() {

	}

	protected void setAttributes(AttributeInstance instance, Difficulty difficuly) {
		instance.setBaseValue(Attribute.GENERIC_MAX_HEALTH, 800.0 * difficuly.level);
		instance.setBaseValue(Attribute.GENERIC_MOVEMENT_SPEED, 0.1 * difficuly.level);
		instance.setBaseValue(Attribute.GENERIC_ATTACK_DAMAGE, 12.0 * difficuly.level);
	}
}
