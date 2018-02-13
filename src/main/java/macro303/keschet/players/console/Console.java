package macro303.keschet.players.console;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.Util;
import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Macro303 on 2018-02-12.
 */
abstract class Console {
	private static final Logger LOGGER = LogManager.getLogger(Console.class);

	static void showTitle(@NotNull String title, @NotNull Colour colour) {
		String string = IntStream.range(0, title.length() + 4).mapToObj(i -> "=").collect(Collectors.joining());
		colourConsole(string, colour);
		colourConsole("  " + title + "  ", colour);
		colourConsole(string, colour);
	}

	private static void colourConsole(@NotNull String message, @NotNull Colour messageColour) {
		System.out.print(messageColour.getColourCode());
		System.out.print(message);
		System.out.println(Colour.RESET.getColourCode());
	}

	private static void colourConsole(@NotNull String title, @NotNull Colour titleColour, @NotNull String message, @NotNull Colour messageColour) {
		System.out.print(titleColour.getColourCode());
		System.out.print(title);
		colourConsole(message, messageColour);
	}

	static void showMessage(@NotNull String message) {
		colourConsole(message, Colour.WHITE);
		LOGGER.info(message);
	}

	static void showError(@NotNull String message) {
		colourConsole(message, Colour.MAGENTA);
		LOGGER.warn(message);
	}

	static void drawBoard(@NotNull Board board, boolean colourSides) {
		for (int row = -1; row < Util.SIZE; row++) {
			for (int col = -1; col < Util.SIZE; col++) {
				System.out.print(Colour.GREEN.getColourCode());
				if (row == -1 && col == -1)
					System.out.print("  ");
				else if (col == -1)
					System.out.print(row + " ");
				else if (row == -1)
					System.out.print(" " + col + " ");
				else {
					System.out.print(Colour.YELLOW.getColourCode());
					if (colourSides) {
						if (row < 3)
							System.out.print(Colour.BLUE.getColourCode());
						else if (row > 6)
							System.out.print(Colour.RED.getColourCode());
					}
					Square square = board.getSquare(new Coordinates(row, col));
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
}
