package github.macro.players;

import github.macro.Coordinate;
import github.macro.GameBoard;
import github.macro.console.Colour;
import github.macro.pieces.Piece;
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
	public Coordinate placePiece(@NotNull GameBoard board, @NotNull Piece piece) {
		/*boolean isPlayer1 = getPlayerNum() == 1;
		for (int row = isPlayer1 ? 0 : (board.getHeight() - 1); isPlayer1 ? (row < board.getHeight()) : (row >= 0); row = isPlayer1 ? row + 1 : row - 1) {
			for (int col = 0; col < board.getWidth(); col++) {
				Square square = board.getSquare(row, col);
				assert square != null;
				if (square.getPiece() == null)
					return square.getCoord();
			}
		}
		return requestLocation();*/
		//TODO Random piece placement
		return null;
	}

	@NotNull
	@Override
	public Coordinate selectPiece(@NotNull GameBoard board) {
		//TODO Random piece selection
		return null;
	}

	@NotNull
	@Override
	public Coordinate movePieceTo(@NotNull GameBoard board, @NotNull Piece piece) {
		//TODO Random piece movement
		return null;
	}

	@Override
	public String toString() {
		return "AutoPlayer{} " + super.toString();
	}
}