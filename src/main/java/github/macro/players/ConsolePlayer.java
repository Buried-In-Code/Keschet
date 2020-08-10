package github.macro.players;

import github.macro.Coordinate;
import github.macro.GameBoard;
import github.macro.console.Colour;
import github.macro.console.Console;
import github.macro.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-12.
 */
public class ConsolePlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger(ConsolePlayer.class);

	public ConsolePlayer(@NotNull String name, @NotNull Colour colour, int playerNum) {
		super(name, colour, playerNum);
	}

	@NotNull
	@Override
	public Coordinate placePiece(@NotNull GameBoard board, @NotNull Piece piece) {
		return requestLocation();
	}

	@NotNull
	@Override
	public Coordinate selectPiece(@NotNull GameBoard board) {
		return requestLocation();
	}

	@NotNull
	@Override
	public Coordinate movePieceTo(@NotNull GameBoard board, @NotNull Piece piece) {
		return requestLocation();
	}

	@Override
	public String toString() {
		return "ConsolePlayer{} " + super.toString();
	}

	@NotNull
	private Coordinate requestLocation() {
		boolean valid = false;
		int row = -1;
		int col = -1;
		do {
			String input = Console.displayPrompt("Location (Row:Col)");
			String[] location = input.split(":");
			try {
				row = Integer.parseInt(location[0]);
				col = Integer.parseInt(location[1]);
				valid = true;
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				LOGGER.warn("Invalid Selection");
			}
		} while (!valid);
		return new Coordinate(row, col);
	}
}