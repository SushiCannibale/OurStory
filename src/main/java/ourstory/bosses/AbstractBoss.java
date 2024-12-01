package ourstory.bosses;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Boss;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ourstory.OurStoryPlugin;
import ourstory.skills.AbstractSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class AbstractBoss {
	public static final String METADATA = "isBoss";

	protected String name;
	protected Monster entity;
	protected Map<Predicate<AbstractBoss>, AbstractSkill> skills;
	protected ArrayList<AbstractSkill> running_skills;
	protected ArrayList<LivingEntity> targets;

	public AbstractBoss(String name, Monster entity) {
		this.name = name;
		this.entity = entity;
		this.skills = new HashMap<>();
		this.running_skills = new ArrayList<>();
		this.targets = new ArrayList<>();
	}

	public String getName() {
		return this.name;
	}

	public Monster getEntity() {
		return this.entity;
	}

	public boolean hasRunningSkills() {
		return !this.running_skills.isEmpty();
	}

	/* A map of all skills usable by the Boss */
	protected abstract Map<Predicate<AbstractBoss>, AbstractSkill> getSkills();

	/* The name will be used to fetch the loot table. So it MUST be in 'camel_case' */
	public LootTable getLootTable() {
		NamespacedKey key = new NamespacedKey(OurStoryPlugin.namespace, this.name);
		return Bukkit.getLootTable(key);
	}

	/* Tries to run a new skill */
	public void tick() {
		for (var predicate : skills.keySet()) {
			AbstractSkill skill = skills.get(predicate);
			if (predicate.test(this) && skill.canBeTriggered() && !skill.isRunning()) {
				skill.setCaster(this.entity);
				skill.setTargets(this.targets);
				skill.start();
				this.running_skills.add(skill);
			}
		}
	}

	public void onSpawn() {
		Plugin plugin = JavaPlugin.getPlugin(OurStoryPlugin.class);
		entity.setMetadata("isBoss", new FixedMetadataValue(plugin, true));

		this.entity.setLootTable(this.getLootTable());

		BossManager.getInstance().registerBoss(this);
	}

	public void onHit() {

	}

	public void onDeath() {
		/* TODO: Make sure all running skills are stopped */
		BossManager.getInstance().unregisterBoss(this);
	}
}
