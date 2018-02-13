package macro303.keschet.players.console;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.board.Board;
import macro303.keschet.pieces.Piece;
import macro303.keschet.players.Player;
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
		if (currentPiece != piece)
			Console.showTitle("Place " + piece.getClass().getSimpleName(), getColour());
		currentPiece = piece;
		return selectLocation();
	}

	@NotNull
	@Override
	public Coordinates selectPiece(@NotNull Board board) {
		Console.showTitle("Select a piece", getColour());
		return selectLocation();
	}

	@NotNull
	@Override
	public Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece) {
		Console.showTitle("Move " + piece.getClass().getSimpleName() + " to", getColour());
		return selectLocation();
	}

	@Override
	public void showInfo(@NotNull String message) {
		Console.showMessage(message);
	}

	@Override
	public void showWarning(@NotNull String message) {
		Console.showError(message);
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
				showWarning("Invalid Selection");
			}
		} while (!valid);
		return new Coordinates(row, col);
	}
}
