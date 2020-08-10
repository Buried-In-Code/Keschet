package github.macro.players;

import github.macro.Coordinate;
import github.macro.GameBoard;
import github.macro.console.Colour;
import github.macro.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Player {
	@NotNull
	private final String name;
	@NotNull
	private final Colour colour;
	private final int playerNum;

	protected Player(@NotNull String name, @NotNull Colour colour, int playerNum) {
		this.name = name;
		this.colour = colour;
		this.playerNum = playerNum;
	}

	@NotNull
	public abstract Coordinate placePiece(@NotNull GameBoard board, @NotNull Piece piece);

	@NotNull
	public abstract Coordinate selectPiece(@NotNull GameBoard board);

	@NotNull
	public abstract Coordinate movePieceTo(@NotNull GameBoard board, @NotNull Piece piece);

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		if (playerNum != player.playerNum) return false;
		if (!name.equals(player.name)) return false;
		return colour == player.colour;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + colour.hashCode();
		result = 31 * result + playerNum;
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", colour=" + colour +
				", playerNum=" + playerNum +
				'}';
	}
}
