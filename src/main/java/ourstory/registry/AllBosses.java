package ourstory.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import ourstory.bosses.AbstractBoss;

public class AllBosses {
	private static final Map<String, AbstractBoss> map = new HashMap<String, AbstractBoss>();

	public static void register() {
		map.put(AbyssalSentinel());
	}

	public static Optional<AbstractBoss> getBossByName(String name) {
		AbstractBoss boss = map.get(name);
		if (boss == null) {
			return Optional.empty();
		}

		return Optional.of(boss);
	}
}
