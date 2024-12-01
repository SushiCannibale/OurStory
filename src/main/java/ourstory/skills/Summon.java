package ourstory.skills;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ourstory.storage.Storage;
import ourstory.utils.EnchantItem;
import ourstory.utils.PlayerUtils;

public class Summon extends AbstractSkill {
	public Summon(int max_cooldown, LivingEntity caster, ArrayList<LivingEntity> targets) {
		super(max_cooldown, caster, targets);
	}
//	@Override
	public void cast(Entity caster, List<Entity> targets) {
		Storage s = Storage.getInstance();
		Monster boss = s.bossInstance.monster.entity;

		PlayerUtils.broadcastToPlayers(s.bossInstance.players, "Go Minions, show no mercy !");

		for (int i = 0; i < 3; i++) {
			new BukkitRunnable() {
				@Override
				public void run() {
					Zombie minion = (Zombie) boss.getWorld().spawnEntity(boss.getLocation(), EntityType.ZOMBIE);

					EntityEquipment equipment = minion.getEquipment();
					ItemStack[] armor = {
							EnchantItem.createEnchantedItem(Material.GOLDEN_BOOTS, Map.of(Enchantment.THORNS, 3)),
							EnchantItem.createEnchantedItem(Material.GOLDEN_LEGGINGS, Map.of()),
							EnchantItem.createEnchantedItem(Material.GOLDEN_CHESTPLATE, Map.of(Enchantment.THORNS, 1)),
							EnchantItem.createEnchantedItem(Material.GOLDEN_HELMET, Map.of(Enchantment.THORNS, 3))
					};

					equipment.setArmorContents(armor);

					minion.setBaby();
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("OurStory"), i * 2);
		}
	}

	@Override
	public void run() {
		Storage s = Storage.getInstance();
		PlayerUtils.broadcastToPlayers(this.targets, "Go Minions, show no mercy !");
	}
}
