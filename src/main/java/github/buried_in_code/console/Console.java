package github.buried_in_code.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by BuriedInCode on 2021-Jun-02.
 */
public abstract class Console {
	private static final Logger LOGGER = LogManager.getLogger();

	public static void displayHeading(@NotNull String text) {
		colourConsole("=".repeat(text.length() + 4), Colour.BLUE);
		colourConsole(text, Colour.BLUE);
		colourConsole("=".repeat(text.length() + 4), Colour.BLUE);
	}

	@NotNull
	public static String displayPrompt(@NotNull String prompt) {
		return Reader.readConsole(prompt);
	}

	public static boolean displayAgreement(@NotNull String prompt) {
		var input = displayPrompt(prompt + " (Y/N)");
		return input.equalsIgnoreCase("y");
	}

	public static void displayMessage(@NotNull String text) {
		displayMessage(text, Colour.WHITE);
	}

	public static void displayMessage(@NotNull String text, @NotNull Colour colour) {
		colourConsole(text, colour);
	}

	public static void displayMessageFormat(@NotNull String format, @Nullable Object... args) {
		displayMessageFormat(format, Colour.WHITE, args);
	}

	public static void displayMessageFormat(@NotNull String format, @NotNull Colour colour, @Nullable Object... args) {
		colourConsole(String.format(format, args), colour);
	}

	private static void colourConsole(@Nullable String text, @NotNull Colour colour) {
		System.out.printf("%s%s%s\n", colour, text, Colour.RESET);
	}
}
