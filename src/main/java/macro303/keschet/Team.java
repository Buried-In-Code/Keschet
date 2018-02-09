package macro303.keschet;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Team {
	@NotNull
	private final Colour colour;

	protected Team(@NotNull Colour colour) {
		this.colour = colour;
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Team)) return false;

		Team team = (Team) o;

		return colour == team.colour;
	}

	@Override
	public int hashCode() {
		return colour.hashCode();
	}

	@Override
	public String toString() {
		return "Team{" +
				"colour=" + colour +
				'}';
	}
}
