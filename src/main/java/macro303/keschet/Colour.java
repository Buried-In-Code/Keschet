package macro303.keschet;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

	@NotNull
	private final String colourCode;

	Colour(@NotNull String colourCode) {
		this.colourCode = colourCode;
	}

	@Contract(pure = true)
	@NotNull
	public String getColourCode() {
		return colourCode;
	}
}