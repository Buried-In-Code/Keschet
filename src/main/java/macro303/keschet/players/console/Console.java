package macro303.keschet.players.console;

import macro303.keschet.Colour;
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

	static void showTitle(String title, Colour colour) {
		String string = IntStream.range(0, title.length() + 4).mapToObj(i -> "=").collect(Collectors.joining());
		colourConsole(string, colour);
		colourConsole("  " + title + "  ", colour);
		colourConsole(string, colour);
	}

	private static void colourConsole(String message, Colour messageColour) {
		System.out.print(messageColour.getColourCode());
		System.out.print(message);
		System.out.println(Colour.RESET.getColourCode());
	}

	private static void colourConsole(String title, Colour titleColour, String message, Colour messageColour) {
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
}
