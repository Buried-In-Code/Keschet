package github.macro.players;

import github.macro.Board;
import github.macro.Square;
import github.macro.console.Colour;
import github.macro.console.Console;
import github.macro.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-Feb-12.
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