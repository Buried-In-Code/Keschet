package github.buried_in_code.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

/**
 * Created by BuriedInCode on 2018-Feb-08.
 */
public abstract class Reader {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Scanner READER = new Scanner(System.in);

	@NotNull
	public static String readConsole(@Nullable String prompt) {
		System.out.printf("%s%s >> ", Colour.CYAN, prompt);
		var input = READER.nextLine().trim();
		System.out.print(Colour.RESET);
		return input;
	}
}
