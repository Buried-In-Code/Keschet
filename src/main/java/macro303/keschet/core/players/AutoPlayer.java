package macro303.keschet.core.players;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Square;
import macro303.keschet.Util;
import macro303.keschet.core.Coordinates;
import macro303.keschet.core.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-22.
 */
public class AutoPlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger(AutoPlayer.class);

	public AutoPlayer(@NotNull String name, @NotNull Colour colour, int playerNum) {
		super(name, colour, playerNum);
	}

	@NotNull
	@Override
	public Coordinates placePiece(@NotNull Board board, @NotNull Piece piece) {
		boolean isPlayer1 = getPlayerNum() == 1;
		for (int row = isPlayer1 ? 0 : (board.getHeight() - 1); isPlayer1 ? (row < board.getHeight()) : (row >= 0); row = isPlayer1 ? row + 1 : row - 1) {
			for (int col = 0; col < board.getWidth(); col++) {
				Square square = board.getSquare(row, col);
				assert square != null;
				if (square.getPiece() == null)
					return new Coordinates(square.getY(), square.getX());
			}
		}
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
		return "AutoPlayer{} " + super.toString();
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