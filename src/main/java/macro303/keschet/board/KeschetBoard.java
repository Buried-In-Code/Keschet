package macro303.keschet.board;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
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
public class KeschetBoard extends Board {
	private static final Logger LOGGER = LogManager.getLogger(KeschetBoard.class);

	public KeschetBoard(int width, int height, Colour boardColour, Colour headerColour) {
		super(width, height, boardColour, headerColour);
	}

	public KeschetBoard(int width, int height) {
		super(width, height);
	}

	public KeschetBoard(int size, Colour boardColour, Colour headerColour) {
		super(size, boardColour, headerColour);
	}

	public KeschetBoard(int size) {
		super(size);
	}

	public int countPieces(@NotNull Player player) {
		int counter = 0;
		for (int row = 0; row < Util.SIZE; row++) {
			for (int col = 0; col < Util.SIZE; col++) {
				Square location = getSquare(new Coordinates(row, col));
				assert location != null;
				if (location.getItem() != null && ((Piece) location.getItem()).getTeamColour() == player.getTeamColour())
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
				if (location.getItem() != null && location.getItem().getClass().equals(clazz) && ((Piece) location.getItem()).getTeamColour() == teamColour)
					temp = location;
			}
		}
		return temp;
	}

	public void draw(boolean colourSides) {
		for (int row = -1; row < height; row++) {
			for (int col = -1; col < width; col++) {
				System.out.print(headerColour.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = getSquare(new Coordinates(row, col));
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
		for (int row = -1; row < height; row++) {
			for (int col = -1; col < width; col++) {
				System.out.print(headerColour.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = getSquare(new Coordinates(row, col));
					assert square != null;
					System.out.print(boardColour.getColourCode());
					boolean valid = Util.validMovement(this, location, square);
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
