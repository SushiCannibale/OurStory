package ourstory.bosses.state;

import ourstory.bosses.AbstractBoss;

public abstract class AbstractState {
	protected AbstractBoss boss;

	public AbstractState(AbstractBoss boss) {
		this.boss = boss;
	}

	public abstract void onEnterState();

	public abstract void onTick();

	public abstract void onQuitState();
}
