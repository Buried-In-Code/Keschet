package github.buried_in_code.players;

import github.buried_in_code.Board;
import github.buried_in_code.Square;
import github.buried_in_code.console.Colour;
import github.buried_in_code.console.Console;
import github.buried_in_code.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by BuriedInCode on 2018-Feb-12.
 */
public class ConsolePlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger();

	public ConsolePlayer(@NotNull String name, @NotNull Colour colour) {
		super(name, colour);
	}

	@NotNull
	@Override
	public Square placePiece(@NotNull Board board, @NotNull Piece piece) {
		return requestLocation(board);
	}

	@NotNull
	@Override
	public Square placeStolenPiece(@NotNull Board board, @NotNull Piece piece, @NotNull Square thiefSquare) {
		return requestLocation(board);
	}

	@NotNull
	@Override
	public Square selectPiece(@NotNull Board board) {
		return requestLocation(board);
	}

	@NotNull
	@Override
	public Square movePieceTo(@NotNull Board board, @NotNull Piece piece) {
		return requestLocation(board);
	}

	@NotNull
	protected Square requestLocation(@NotNull Board board) {
		int row = -1;
		int col = -1;
		do {
			var input = Console.displayPrompt("Location (Row:Col)");
			var location = input.split(":");
			try {
				row = Integer.parseInt(location[0]);
				col = Integer.parseInt(location[1]);
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				row = -1;
				col = -1;
			}
			if (row < 0 || col < 0)
				LOGGER.warn("Invalid Selection");
		} while (row < 0 || col < 0);
		var square = board.getSquare(row, col);
		if (square == null)
			return requestLocation(board);
		return square;
	}

	@NotNull
	@Override
	public String toString() {
		return "ConsolePlayer{} " + super.toString();
	}
}
