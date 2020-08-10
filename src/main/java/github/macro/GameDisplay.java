package github.macro;

import github.macro.console.Colour;
import github.macro.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-22.
 */
public class GameDisplay {
	@NotNull
	private static final Logger LOGGER = LogManager.getLogger(GameDisplay.class);
	@NotNull
	private static final Colour HEADER_COLOUR = Colour.BLUE;
	@NotNull
	private static final Colour BOARD_COLOUR = Colour.MAGENTA;
	@NotNull
	private final GameBoard board;

	public GameDisplay(@NotNull GameBoard board) {
		this.board = board;
//		super(false, true, true);
	}

	public void draw() {
		for (int row = -1; row < board.getHeight(); row++) {
			for (int col = -1; col < board.getWidth(); col++) {
				System.out.print(HEADER_COLOUR.getANSICode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(row, col);
					assert square != null;
					System.out.print(BOARD_COLOUR.getANSICode());
					System.out.print(square.draw());
				}
				System.out.print(Colour.RESET.getANSICode());
			}
			System.out.println();
		}
	}

	public void draw(Square location) {
		Piece piece = location.getPiece();
		assert piece != null;
		for (int row = -1; row < board.getHeight(); row++) {
			for (int col = -1; col < board.getWidth(); col++) {
				System.out.print(HEADER_COLOUR.getANSICode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(row, col);
					assert square != null;
					Piece item = square.getPiece();
					System.out.print(BOARD_COLOUR.getANSICode());
					boolean valid = Util.validMovement(board, location, square);
					if (valid) {
						System.out.print(Colour.CYAN.getANSICode());
					} else if (item != null) {
						System.out.print(item.getColour().getANSICode());
					}
					if (item == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + item.getColouredSymbol() + " ");
					}
				}
				System.out.print(Colour.RESET.getANSICode());
			}
			System.out.println();
		}
	}

	public void showTitle(String message, Colour msgColour) {
		System.out.println(String.format("%s%s%s", msgColour.getANSICode(), message, Colour.RESET.getANSICode()));
	}

	public void showMessage(String message) {
		showMessage(message, Colour.WHITE);
	}

	public void showMessage(String message, Colour msgColour) {
		System.out.println(String.format("%s%s%s", msgColour.getANSICode(), message, Colour.RESET.getANSICode()));
	}
}