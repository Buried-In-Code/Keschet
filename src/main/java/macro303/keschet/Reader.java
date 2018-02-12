package macro303.keschet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Reader {
	private static final Logger LOGGER = LogManager.getLogger(Reader.class);
	private static final Scanner reader = new Scanner(System.in);

	@NotNull
	public static String readConsole(String prompt) {
		System.out.println(prompt);
		System.out.print("> ");
		return reader.nextLine().trim();
	}
}
