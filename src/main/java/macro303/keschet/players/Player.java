package macro303.keschet.players;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Player {
	@NotNull
	protected final Colour teamColour;
	@NotNull
	private final String name;

	protected Player(@NotNull String name, @NotNull Colour teamColour) {
		this.name = name;
		this.teamColour = teamColour;
	}

	@NotNull
	public String getName() {
		return name;
	}

	@NotNull
	public Colour getTeamColour() {
		return teamColour;
	}

	@NotNull
	public abstract Coordinates placePiece(@NotNull Board board, @NotNull Piece piece);

	@NotNull
	public abstract Coordinates selectPiece(@NotNull Board board);

	@NotNull
	public abstract Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		if (teamColour != player.teamColour) return false;
		return name.equals(player.name);
	}

	@Override
	public int hashCode() {
		int result = teamColour.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"teamColour=" + teamColour +
				", name='" + name + '\'' +
				'}';
	}
}
