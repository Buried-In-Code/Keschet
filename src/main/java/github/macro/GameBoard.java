package github.macro;

import github.macro.console.Colour;
import github.macro.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class GameBoard {
	private static final Logger LOGGER = LogManager.getLogger(GameBoard.class);
	private static final int HEIGHT = 10;
	private static final int WIDTH = 10;
	private final Square[][] boardSquares;

	public GameBoard() {
		boardSquares = new Square[HEIGHT][WIDTH];
		for (int row = 0; row < WIDTH; row++) {
			var temp = new Square[WIDTH];
			for (int col = 0; col < HEIGHT; col++) {
				temp[col] = new Square(new Coordinate(row, col));
			}
			boardSquares[row] = temp;
		}
	}

	public int countPieces(@NotNull Player player) {
		int counter = 0;
		for (int row = 0; row < WIDTH; row++) {
			for (int col = 0; col < HEIGHT; col++) {
				Square location = getSquare(row, col);
				assert location != null;
				if (location.getPiece() != null && location.getPiece().getColour() == player.getColour())
					counter++;
			}
		}
		return counter;
	}

	@Nullable
	public Square findPiece(@NotNull Class clazz, @NotNull Colour teamColour) {
		Square temp = null;
		for (int row = 0; row < WIDTH; row++) {
			for (int col = 0; col < HEIGHT; col++) {
				Square location = getSquare(row, col);
				assert location != null;
				if (location.getPiece() != null && location.getPiece().getClass().equals(clazz) && location.getPiece().getColour() == teamColour)
					temp = location;
			}
		}
		return temp;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}

	@Nullable
	public Square getSquare(@NotNull Coordinate coord) {
		return boardSquares[coord.getRow()][coord.getCol()];
	}

	@Nullable
	public Square getSquare(int row, int col) {
		return getSquare(new Coordinate(row, col));
	}
}
