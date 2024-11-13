package ourstory.bosses.state;

import ourstory.bosses.AbstractBoss;

/**
 * This states makes the boss forget about its targets and do nothing.
 */
public class IdleState extends AbstractState {
	public IdleState(AbstractBoss boss) {
		super(boss);
	}

	@Override
	public void onEnterState() {
		this.boss.targets.clear();
	}

	@Override
	public void onQuitState() {

	}

	@Override
	public void onTick() {

	}
}
