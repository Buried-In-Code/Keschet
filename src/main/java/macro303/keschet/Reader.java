package macro303.keschet;

import java.util.Scanner;

public class Reader {
	private Scanner reader = new Scanner(System.in);

	public String readConsole(String prompt) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException ignored) {
		}
		System.out.println(prompt);
		System.out.print("> ");
		return reader.nextLine().trim();
	}
}
