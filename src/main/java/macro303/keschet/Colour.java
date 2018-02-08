package macro303.keschet;

/**
 * Created by Macro303 on 2018-02-08.
 */
public enum Colour {
	RESET("\u001B[0m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	MAGENTA("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m");

	private final String colourCode;

	Colour(String colourCode) {
		this.colourCode = colourCode;
	}

	public String getColourCode() {
		return colourCode;
	}
}