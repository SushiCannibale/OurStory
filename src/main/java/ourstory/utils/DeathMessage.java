package ourstory.utils;

import java.util.Locale;
import org.bukkit.entity.Player;
import ourstory.OurStoryPlugin;

public class DeathMessage {
	public static String getRandomDeathMessage(Locale locale, Player player, int rng) {
		String country = locale.getCountry().toLowerCase();

		String deathMessage = OurStoryPlugin.deathMessagesEn.get(rng);

		if (country.contains("fr"))
			deathMessage = OurStoryPlugin.deathMessagesFr.get(rng);

		deathMessage = deathMessage.replace("[player]", player.getName());

		return deathMessage;
	}
}
