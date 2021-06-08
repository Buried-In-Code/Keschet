package github.macro.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

/**
 * Created by Macro303 on 2021-Jun-02.
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