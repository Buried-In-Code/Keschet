package macro303.keschet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Reader {
	private static final Logger LOGGER = LogManager.getLogger(Reader.class);

	private Scanner reader = new Scanner(System.in);

	public String readConsole(String prompt) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException ignored) {
		}
		System.out.println(prompt);
		System.out.print("> ");
		String input = reader.nextLine().trim();
		LOGGER.trace("String readConsole(String) = " + input);
		return input;
	}
}
