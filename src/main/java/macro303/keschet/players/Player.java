package macro303.keschet.players;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.board.Board;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Player {
	@NotNull
	private final Colour colour;

	protected Player(@NotNull Colour colour) {
		this.colour = colour;
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	@NotNull
	public abstract Coordinates placePiece(@NotNull Board board, @NotNull Piece piece);

	@NotNull
	public abstract Coordinates selectPiece(@NotNull Board board);

	@NotNull
	public abstract Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece);

	public abstract void showInfo(@NotNull String message);

	public abstract void showWarning(@NotNull String message);

	public abstract void drawBoard(@NotNull Board board, boolean colourSides);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		return colour == player.colour;
	}

	@Override
	public int hashCode() {
		return colour.hashCode();
	}

	@Override
	public String toString() {
		return "Player{" +
				"colour=" + colour +
				'}';
	}
}
