package ourstory.bosses;

import org.bukkit.entity.LivingEntity;
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
	public void onHit() {

	}

	@Override
	public void onDeath() {

	}
}
