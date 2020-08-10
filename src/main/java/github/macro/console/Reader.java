package github.macro.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public abstract class Reader {
	private static final Logger LOGGER = LogManager.getLogger(Reader.class);
	@NotNull
	private static final Scanner READER = new Scanner(System.in);

	@NotNull
	public static String readConsole(String prompt, @NotNull Colour promptColour, @NotNull Colour inputColour) {
		System.out.print(String.format("%s%s >> %s", promptColour.getANSICode(), prompt, inputColour.getANSICode()));
		var input = READER.nextLine().trim();
		System.out.print(Colour.RESET.getANSICode());
		return input;
	}
}
