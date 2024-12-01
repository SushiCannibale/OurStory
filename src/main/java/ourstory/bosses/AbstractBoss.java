package ourstory.bosses;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ourstory.OurStoryPlugin;
import ourstory.skills.AbstractSkill;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;

public abstract class AbstractBoss {
	public static final String METADATA = "isBoss";

	protected String name;
	protected LivingEntity entity;
	protected Map<Predicate<AbstractBoss>, AbstractSkill> skills;
	protected ArrayList<LivingEntity> targets;

	public AbstractBoss(String name, LivingEntity entity, Map<Predicate<AbstractBoss>, AbstractSkill> skills) {
		this.name = name;
		this.entity = entity;
		this.skills = skills;
		this.targets = new ArrayList<>();

		Plugin plugin = JavaPlugin.getPlugin(OurStoryPlugin.class);
		entity.setMetadata("isBoss", new FixedMetadataValue(plugin, true));
	}

	public String getName() {
		return this.name;
	}

	public LivingEntity getEntity() {
		return this.entity;
	}

	public void onSpawn() {
		BossManager.getInstance().registerBoss(this);
	}

	public abstract void onHit();
	// TODO: Drop loot, end thread if running

	public void onDeath() {
		BossManager.getInstance().unregisterBoss(this);
	}

	public void tick() {
		for (var predicate : skills.keySet()) {
			AbstractSkill skill = skills.get(predicate);
			if (predicate.test(this) && skill.canBeTriggered() && !skill.isRunning()) {
				skill.setCaster(this.entity);
				skill.setTargets(this.targets);
				skill.start();
			}
		}
	}

	/*
	 * If a boss is killed in hard mode, then we trigger all loots tables bellow
	 */

//	public void generateDrops(EntityDeathEvent event, Map<Difficulty, List<LootEntry>> loots) {
//		Random random = new Random();
//
//		for (Map.Entry<Difficulty, List<LootEntry>> entry : loots.entrySet()) {
//			if (this.difficulty.level >= entry.getKey().level) {
//				for (LootEntry le : entry.getValue()) {
//					int rng = random.nextInt(101);
//
//					if (rng < le.proba()) {
//						int quantity = Math.max(1, random.nextInt(le.maxQuantity() + 1));
//
//						ItemStack item = le.item().clone();
//						item.setAmount(quantity);
//						event.getDrops().add(item);
//					}
//				}
//			}
//		}
//	}
}
