package macro303.keschet.display.console;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.Util;
import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import macro303.keschet.display.Display;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Macro303 on 2018-02-14.
 */
public class Console implements Display {
	private static final Logger LOGGER = LogManager.getLogger(Console.class);

	public void showTitle(@NotNull String title, @NotNull Colour colour) {
		String string = IntStream.range(0, title.length() + 4).mapToObj(i -> "=").collect(Collectors.joining());
		colourConsole(string, colour);
		colourConsole("  " + title + "  ", colour);
		colourConsole(string, colour);
	}

	private void colourConsole(@NotNull String message, @NotNull Colour messageColour) {
		System.out.print(messageColour.getColourCode());
		System.out.print(message);
		System.out.println(Colour.RESET.getColourCode());
	}

	@Override
	public void showInfo(@NotNull String message) {
		colourConsole(message, Colour.WHITE);
		LOGGER.info(message);
	}

	@Override
	public void showWarning(@NotNull String message) {
		colourConsole(message, Colour.MAGENTA);
		LOGGER.warn(message);
	}

	@Override
	public void drawBoard(@NotNull Board board) {
		drawBoard(board, false);
	}

	@Override
	public void drawBoard(@NotNull Board board, boolean colourSides) {
		for (int row = -1; row < Util.SIZE; row++) {
			for (int col = -1; col < Util.SIZE; col++) {
				System.out.print(Colour.GREEN.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(new Coordinates(row, col));
					assert square != null;
					if (colourSides) {
						if (row < 3) {
							System.out.print(Colour.BLUE.getColourCode());
						} else if (row > 6) {
							System.out.print(Colour.RED.getColourCode());
						} else {
							System.out.print(Colour.YELLOW.getColourCode());
						}
					} else if (square.getPiece() == null) {
						System.out.print(Colour.YELLOW.getColourCode());
					} else {
						System.out.print(square.getPiece().getTeamColour().getColourCode());
					}
					if (square.getPiece() == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + square.getPiece().getSymbol() + " ");
					}
				}
			}
			System.out.println();
		}
		System.out.print(Colour.RESET.getColourCode());
	}

	@Override
	public void drawBoard(@NotNull Board board, @NotNull Piece piece) {
		Square location = board.findPiece(piece);
		assert location != null;
		for (int row = -1; row < Util.SIZE; row++) {
			for (int col = -1; col < Util.SIZE; col++) {
				System.out.print(Colour.GREEN.getColourCode());
				if (row == -1 && col == -1) {
					System.out.print("  ");
				} else if (col == -1) {
					System.out.print(row + " ");
				} else if (row == -1) {
					System.out.print(" " + col + " ");
				} else {
					Square square = board.getSquare(new Coordinates(row, col));
					assert square != null;
					boolean valid = Util.validMovement(piece, location, square);
					if (valid) {
						System.out.print(Colour.CYAN.getColourCode());
					} else if (square.getPiece() == null) {
						System.out.print(Colour.YELLOW.getColourCode());
					} else {
						System.out.print(square.getPiece().getTeamColour().getColourCode());
					}
					if (square.getPiece() == null) {
						System.out.print(" ~ ");
					} else {
						System.out.print(" " + square.getPiece().getSymbol() + " ");
					}
				}
			}
			System.out.println();
		}
		System.out.print(Colour.RESET.getColourCode());
	}

	@NotNull
	@Override
	public Coordinates requestLocation() {
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
				showWarning("Invalid Selection");
			}
		} while (!valid);
		return new Coordinates(row, col);
	}
}
