package macro303.keschet;

public enum ConsoleColour {
	RESET("\u001B[0m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	MAGENTA("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m");

	String colourCode;

	ConsoleColour(String colourCode) {
		this.colourCode = colourCode;
	}
}
