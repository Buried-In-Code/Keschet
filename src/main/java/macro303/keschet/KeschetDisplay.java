package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;

public class KeschetDisplay extends macro303.board_game.Display {

	public KeschetDisplay(@NotNull Board board, @NotNull Colour boardColour, @NotNull Colour headerColour) {
		super(board, boardColour, headerColour);
	}

	public KeschetDisplay(@NotNull Board board) {
		super(board);
	}

	public void draw(boolean colourSides) {
		for (int row = -1; row < board.getHeight(); row++) {
			for (int col = -1; col < board.getWidth(); col++) {
				System.out.print(headerColour.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(new Coordinates(row, col));
					assert square != null;
					System.out.print(boardColour.getColourCode());
					if (colourSides) {
						if (row < 3) {
							System.out.print(Util.player1Colour.getColourCode());
						} else if (row > 6) {
							System.out.print(Util.player2Colour.getColourCode());
						}
					} else if (square.getItem() != null) {
						System.out.print(((Piece) square.getItem()).getTeamColour().getColourCode());
					}
					if (square.getItem() == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + square.getItem().getSymbol() + " ");
					}
				}
				System.out.print(Colour.RESET.getColourCode());
			}
			System.out.println();
		}
	}

	public void draw(Square location) {
		Piece piece = (Piece) location.getItem();
		assert piece != null;
		for (int row = -1; row < board.getHeight(); row++) {
			for (int col = -1; col < board.getWidth(); col++) {
				System.out.print(headerColour.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(new Coordinates(row, col));
					assert square != null;
					System.out.print(boardColour.getColourCode());
					boolean valid = Util.validMovement(board, location, square);
					if (valid) {
						System.out.print(Colour.CYAN.getColourCode());
					} else if (square.getItem() != null) {
						System.out.print(((Piece) square.getItem()).getTeamColour().getColourCode());
					}
					if (square.getItem() == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + square.getItem().getSymbol() + " ");
					}
				}
				System.out.print(Colour.RESET.getColourCode());
			}
			System.out.println();
		}
	}
}