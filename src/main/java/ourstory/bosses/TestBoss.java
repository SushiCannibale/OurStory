package ourstory.bosses;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.jetbrains.annotations.Nullable;
import ourstory.OurStoryPlugin;
import ourstory.skills.AbstractSkill;
import ourstory.skills.BroadcastMessageSkill;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class TestBoss extends AbstractBoss {
	public TestBoss(Monster entity) {
		super("test_boss", entity);
	}

	private boolean broadcastPredicate(AbstractBoss boss) {
		return boss.getEntity().getHealth() <= 10.0f && !boss.hasRunningSkills();
	}

	@Override
	protected Map<Predicate<AbstractBoss>, AbstractSkill> getSkills() {
		Map<Predicate<AbstractBoss>, AbstractSkill> skills = new HashMap<>();

		skills.put(this::broadcastPredicate, new BroadcastMessageSkill());

		return skills;
	}

	@Override
	public String getLootTableName() {
		return "test_boss";
	}
}
