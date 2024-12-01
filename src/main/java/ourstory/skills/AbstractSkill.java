package ourstory.skills;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class AbstractSkill extends BukkitRunnable {
	protected LivingEntity caster;
	protected ArrayList<LivingEntity> targets;

	/* Cooldown in ticks */
	protected final int max_cooldown;
	protected int cooldown;

	private boolean is_running;

	public AbstractSkill(int max_cooldown) {
		this.max_cooldown = max_cooldown;
		this.cooldown = 0;
		this.is_running = false;
	}

	public void setCaster(LivingEntity caster) {
		this.caster = caster;
	}

	public void setTargets(Collection<LivingEntity> entities) {
		this.targets = new ArrayList<>(entities);
	}

	public boolean canBeTriggered() {
		return cooldown == 0;
	}

	public void resetCooldown() {
		this.cooldown = this.max_cooldown;
	}

	public void start() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("OurStory");
		if (plugin == null) {
			return;
		}
		this.runTask(plugin);
		this.is_running = true;
		this.resetCooldown();
	}

	public boolean isRunning() {
		return this.is_running;
	}
}
