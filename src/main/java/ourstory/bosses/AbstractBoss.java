package ourstory.bosses;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Random;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import ourstory.bosses.state.AbstractState;
import ourstory.bosses.state.IdleState;

/**
 * Logically, a boss is a Finite State Machine. Il can be seen as a thread that runs whenever the
 * `onSpawn()` method is called, and ends with the `onDeath()` method.
 * 
 * The skills are totally delegated to the state themselves.
 */
public abstract class AbstractBoss {
	private String name;
	private EntityType entity_type;
	private Thread thread;

	public AbstractState state;
	public ArrayList<LivingEntity> targets;


	public AbstractBoss(String name, EntityType entity_type) {
		this.name = name;
		this.entity_type = entity_type;
		this.state = new IdleState(this);
		this.thread = new Thread();

		this.targets = new ArrayList<>();
	}

	public void setState(AbstractState new_state) {
		this.state = new_state;
	}

	public void onSpawn() {
		thread.start();
	}

	public void onDeath() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * If a boss is killed in hard mode, then we trigger all loots tables bellow
	 */
	public void generateDrops(EntityDeathEvent event, Map<Difficulty, List<LootEntry>> loots) {
		Random random = new Random();

		for (Map.Entry<Difficulty, List<LootEntry>> entry : loots.entrySet()) {
			if (this.difficulty.level >= entry.getKey().level) {
				for (LootEntry le : entry.getValue()) {
					int rng = random.nextInt(101);

					if (rng < le.proba()) {
						int quantity = Math.max(1, random.nextInt(le.maxQuantity() + 1));

						ItemStack item = le.item().clone();
						item.setAmount(quantity);
						event.getDrops().add(item);
					}
				}
			}
		}
	}
}
