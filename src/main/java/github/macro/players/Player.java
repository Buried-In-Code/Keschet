package github.macro.players;

import github.macro.Board;
import github.macro.Square;
import github.macro.console.Colour;
import github.macro.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2021-Jun-02.
 */
public abstract class Player {
	@NotNull
	protected final String name;
	@NotNull
	protected final Colour colour;

	protected Player(@NotNull String name, @NotNull Colour colour) {
		this.name = name;
		this.colour = colour;
	}

	@NotNull
	public String getName() {
		return name;
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	@NotNull
	public abstract Square placePiece(@NotNull Board board, @NotNull Piece piece);

	@NotNull
	public abstract Square placeStolenPiece(@NotNull Board board, @NotNull Piece piece, @NotNull Square thiefSquare);

	@NotNull
	public abstract Square selectPiece(@NotNull Board board);

	@NotNull
	public abstract Square movePieceTo(@NotNull Board board, @NotNull Piece piece);

	@Override
	public String toString() {
		return "Player{" +
				       "name='" + name + '\'' +
				       ", colour=" + colour +
				       '}';
	}
}