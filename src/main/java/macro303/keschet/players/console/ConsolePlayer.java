package macro303.keschet.players.console;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.keschet.pieces.Piece;
import macro303.keschet.players.Player;
import macro303.keschet.players.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-12.
 */
public class ConsolePlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger(ConsolePlayer.class);

	public ConsolePlayer(@NotNull String name, @NotNull Colour colour) {
		super(name, colour);
	}

	@NotNull
	@Override
	public Coordinates placePiece(@NotNull Board board, @NotNull Piece piece) {
		return requestLocation();
	}

	@NotNull
	@Override
	public Coordinates selectPiece(@NotNull Board board) {
		return requestLocation();
	}

	@NotNull
	@Override
	public Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece) {
		return requestLocation();
	}

	@Override
	public String toString() {
		return "ConsolePlayer{} " + super.toString();
	}

	@NotNull
	private Coordinates requestLocation() {
		boolean valid = false;
		int row = -1;
		int col = -1;
		do {
			String input = Reader.readConsole("Location (Row:Col)");
			String[] location = input.split(":");
			try {
				row = Integer.parseInt(location[0]);
				col = Integer.parseInt(location[1]);
				valid = true;
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				LOGGER.warn("Invalid Selection");
			}
		} while (!valid);
		return new Coordinates(row, col);
	}
}