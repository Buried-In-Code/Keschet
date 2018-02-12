package macro303.keschet.players.console;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.players.Player;
import macro303.keschet.Reader;
import macro303.keschet.board.Board;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-12.
 */
public class ConsolePlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger(ConsolePlayer.class);
	private Piece currentPiece = null;

	public ConsolePlayer(@NotNull Colour colour) {
		super(colour);
	}

	@NotNull
	@Override
	public Coordinates placePiece(@NotNull Board board, @NotNull Piece piece) {
		if(currentPiece != piece)
			Console.showTitle("Place " + piece.getClass().getSimpleName(), getColour());
		currentPiece = piece;
		return selectLocation();
	}

	@NotNull
	@Override
	public Coordinates selectPiece(@NotNull Board board) {
		return new Coordinates(0, 0);
	}

	@NotNull
	@Override
	public Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece) {
		return new Coordinates(0, 0);
	}

	@NotNull
	private Coordinates selectLocation() {
		boolean valid = false;
		int row = -1;
		int col = -1;
		do {
			String input = Reader.readConsole("Location (row:col)");
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
