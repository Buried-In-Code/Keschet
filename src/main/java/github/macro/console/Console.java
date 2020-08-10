package github.macro.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Console {
	@NotNull
	private static final Logger LOGGER = LogManager.getLogger(Console.class);
	@NotNull
	private static final Colour HEADING = Colour.BLUE;
	@NotNull
	private static final Colour HIGHLIGHT = Colour.MAGENTA;
	@NotNull
	private static final Colour PROMPT = Colour.CYAN;
	@NotNull
	private static final Colour INPUT = Colour.GREEN;
	@NotNull
	private static final Colour DEFAULT = Colour.WHITE;

	public static void displayHeading(@NotNull String text) {
		colourConsole("=".repeat(text.length() + 4), HEADING);
		colourConsole(text, HEADING);
		colourConsole("=".repeat(text.length() + 4), HEADING);
	}

	@NotNull
	public static String displayPrompt(@NotNull String text) {
		return Reader.readConsole(text, PROMPT, INPUT).trim();
	}

	public static boolean displayAgreement(@NotNull String text) {
		var input = displayPrompt(String.format("%s (Y/N)", text));
		return input.equalsIgnoreCase("y");
	}

	public static void display(@NotNull String text) {
		display(text, DEFAULT);
	}

	public static void display(@NotNull String text, @NotNull Colour colour) {
		colourConsole(text, colour);
	}

	private static void colourConsole(@Nullable String text, @NotNull Colour colour) {
		System.out.println(String.format("%s%s%s", colour.getANSICode(), text, Colour.RESET.getANSICode()));
	}
}