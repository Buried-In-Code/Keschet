package macro303.keschet.board;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.Util;
import macro303.keschet.pieces.Piece;
import macro303.keschet.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Board {
	private static final Logger LOGGER = LogManager.getLogger(Board.class);
	@NotNull
	private final Square[][] board = new Square[Util.SIZE][Util.SIZE];

	public Board() {
		for (int row = 0; row < Util.SIZE; row++)
			for (int col = 0; col < Util.SIZE; col++)
				board[col][row] = new Square(new Coordinates(row, col));
	}

	@Nullable
	public Square getSquare(@NotNull Coordinates location) {
		if (location.getRow() < 0 || location.getRow() >= Util.SIZE || location.getCol() < 0 || location.getCol() >= Util.SIZE)
			return null;
		return board[location.getCol()][location.getRow()];
	}

	public int countPieces(@NotNull Player player) {
		int counter = 0;
		for (int row = 0; row < Util.SIZE; row++) {
			for (int col = 0; col < Util.SIZE; col++) {
				Square location = getSquare(new Coordinates(row, col));
				assert location != null;
				if (location.getPiece() != null && location.getPiece().getTeamColour() == player.getTeamColour())
					counter++;
			}
		}
		return counter;
	}

	@Nullable
	public Square findPiece(@NotNull Class clazz, @NotNull Colour teamColour) {
		Square temp = null;
		for (int row = 0; row < Util.SIZE; row++) {
			for (int col = 0; col < Util.SIZE; col++) {
				Square location = getSquare(new Coordinates(row, col));
				assert location != null;
				if (location.getPiece() != null && location.getPiece().getClass().equals(clazz) && location.getPiece().getTeamColour() == teamColour)
					temp = location;
			}
		}
		return temp;
	}
}
