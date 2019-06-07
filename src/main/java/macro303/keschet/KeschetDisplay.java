package macro303.keschet;

import macro303.board_game.Colour;
import macro303.board_game.Display;
import macro303.board_game.Square;
import macro303.keschet.core.pieces.Piece;

/**
 * Created by Macro303 on 2018-02-22.
 */
public class KeschetDisplay extends Display {

	public KeschetDisplay() {
		super(false, true, true);
	}

	@Override
	public void draw() {
		draw(false);
	}

	public void draw(boolean colourSides) {
		for (int row = -1; row < board.getHeight(); row++) {
			for (int col = -1; col < board.getWidth(); col++) {
				System.out.print(headingsColour.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(row, col);
					assert square != null;
					System.out.print(boardColour.getColourCode());
					if (colourSides) {
						if (row < 3) {
							System.out.print(Util.player1Colour.getColourCode());
						} else if (row > 6) {
							System.out.print(Util.player2Colour.getColourCode());
						}
					}
					if (square.getPiece() == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + square.getPiece().getSymbol() + " ");
					}
				}
				System.out.print(Colour.RESET.getColourCode());
			}
			System.out.println();
		}
	}

	public void draw(Square location) {
		Piece piece = (Piece) location.getPiece();
		assert piece != null;
		for (int row = -1; row < board.getHeight(); row++) {
			for (int col = -1; col < board.getWidth(); col++) {
				System.out.print(headingsColour.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(row, col);
					assert square != null;
					Piece item = (Piece) square.getPiece();
					System.out.print(boardColour.getColourCode());
					boolean valid = Util.validMovement(board, location, square);
					if (valid) {
						System.out.print(Colour.RED.getColourCode());
					} else if (item != null) {
						System.out.print(item.getColour().getColourCode());
					}
					if (item == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + item.getSymbol2() + " ");
					}
				}
				System.out.print(Colour.RESET.getColourCode());
			}
			System.out.println();
		}
	}
}