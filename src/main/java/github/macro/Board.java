package github.macro;

import github.macro.console.Colour;
import github.macro.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2021-Jun-02.
 */
public class Board {
	public static final int HEIGHT = 10;
	public static final int WIDTH = 10;
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Colour HEADER_COLOUR = Colour.BLUE;
	private final Square[][] board = new Square[HEIGHT][WIDTH];

	public Board() {
		for (var row = 0; row < HEIGHT; row++) {
			for (var col = 0; col < WIDTH; col++) {
				board[row][col] = new Square(row, col);
			}
		}
	}

	public int countPieces(@NotNull Player player) {
		var count = 0;
		for (var row = 0; row < HEIGHT; row++) {
			for (var col = 0; col < WIDTH; col++) {
				var location = getSquare(row, col);
				if (location == null)
					continue;
				if (location.getPiece() != null && location.getPiece().getPlayer() == player)
					count++;
			}
		}
		return count;
	}

	@Nullable
	public Square findPiece(@NotNull Class clazz, @NotNull Player player) {
		Square temp = null;
		for (var row = 0; row < HEIGHT; row++) {
			for (var col = 0; col < WIDTH; col++) {
				var location = getSquare(row, col);
				if (location == null || location.getPiece() == null)
					continue;
				if (location.getPiece().getClass() == clazz && location.getPiece().getPlayer() == player)
					temp = location;
			}
		}
		return temp;
	}

	@Nullable
	public Square getSquare(int row, int col) {
		try {
			return board[row][col];
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			return null;
		}
	}

	public void draw() {
		draw(false, null);
	}

	public void draw(boolean highlight) {
		draw(highlight, null);
	}

	public void draw(@Nullable Square location) {
		draw(false, location);
	}

	public void draw(boolean highlight, @Nullable Square location) {
		for (var row = -1; row < HEIGHT; row++) {
			for (var col = -1; col < WIDTH; col++) {
				if (row == -1 && col == -1)
					System.out.print("  ");
				else if (col == -1)
					System.out.printf("%s%s%s ", HEADER_COLOUR, row, Colour.RESET);
				else if (row == -1)
					System.out.printf(" %s%s%s ", HEADER_COLOUR, col, Colour.RESET);
				else {
					var square = getSquare(row, col);
					if (square == null)
						continue;
					var piece = square.getPiece();
					var symbol = piece == null ? "~" : piece.getSymbol();
					if (location != null && Utils.validMovement(this, location, square))
						System.out.printf(" %s%s%s ", Colour.GREEN, symbol, Colour.RESET);
					else if (highlight && row < 3)
						System.out.printf(" %s%s%s ", Colour.YELLOW, symbol, Colour.RESET);
					else if (highlight && row > 6)
						System.out.printf(" %s%s%s ", Colour.RED, symbol, Colour.RESET);
					else
						System.out.print(square.display());
				}
			}
			System.out.println();
		}
	}
}