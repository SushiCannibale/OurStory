package ourstory.utils;

import java.util.Collection;
import java.util.List;
import org.bukkit.entity.Player;

public class PlayerUtils {
	public static void broadcastToPlayers(Collection<Player> players, String message) {
		for (Player p : players)
			p.sendMessage(message);
	}
}
