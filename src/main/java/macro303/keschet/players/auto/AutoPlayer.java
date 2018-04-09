package macro303.keschet.players.auto;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.Util;
import macro303.keschet.pieces.Piece;
import macro303.keschet.players.Player;
import macro303.keschet.players.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-22.
 */
public class AutoPlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger(AutoPlayer.class);

	public AutoPlayer(@NotNull String name, @NotNull Colour colour) {
		super(name, colour);
	}

	@NotNull
	@Override
	public Coordinates placePiece(@NotNull Board board, @NotNull Piece piece) {
		for (int row = getTeamColour() == Util.player1Colour ? 0 : (board.getHeight() - 1); getTeamColour() == Util.player1Colour ? (row < board.getHeight()) : (row >= 0); row = getTeamColour() == Util.player1Colour ? row + 1 : row - 1) {
			for (int col = 0; col < board.getWidth(); col++) {
				Square square = board.getSquare(new Coordinates(row, col));
				assert square != null;
				if (square.getItem() == null)
					return square.getCoordinates();
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

	@Override
	public String toString() {
		return "AutoPlayer{} " + super.toString();
	}
}