package ourstory.bosses;

import org.bukkit.inventory.ItemStack;

public record LootEntry(ItemStack item, int min_quantity, int maxQuantity, double probability) {
}
