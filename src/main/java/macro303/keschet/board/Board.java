package macro303.keschet.board;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.Util;
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
				board[col][row] = new Square();
	}

	public void draw() {
		draw(false);
	}

	public void draw(boolean teamSides) {
		for (int row = -1; row < Util.SIZE; row++) {
			for (int col = -1; col < Util.SIZE; col++) {
				System.out.print(Colour.CYAN.getColourCode());
				if (row == -1 && col == -1)
					System.out.print("  ");
				else if (col == -1)
					System.out.print(row + " ");
				else if (row == -1)
					System.out.print(" " + col + " ");
				else {
					System.out.print(Colour.YELLOW.getColourCode());
					if (teamSides) {
						if (row < 3)
							System.out.print(Colour.BLUE.getColourCode());
						else if (row > 6)
							System.out.print(Colour.RED.getColourCode());
					}
					Square square = getSquare(new Coordinates(row, col));
					assert square != null;
					if (square.getPiece() == null)
						System.out.print(" ~ ");
					else
						System.out.print(" " + square.getPiece().getTeamColour().getColourCode() + square.getPiece().getSymbol() + " ");
				}
			}
			System.out.println();
		}
		System.out.print(Colour.RESET.getColourCode());
	}

	@Nullable
	public Square getSquare(Coordinates location) {
		if (location.getRow() < 0 || location.getRow() >= Util.SIZE || location.getCol() < 0 || location.getCol() >= Util.SIZE)
			return null;
		return board[location.getCol()][location.getRow()];
	}
}
