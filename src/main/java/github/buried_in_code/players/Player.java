package github.buried_in_code.players;

import github.buried_in_code.Board;
import github.buried_in_code.Square;
import github.buried_in_code.console.Colour;
import github.buried_in_code.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by BuriedInCode on 2018-Feb-08.
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
