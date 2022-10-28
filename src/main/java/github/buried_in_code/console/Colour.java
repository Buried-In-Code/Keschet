package github.buried_in_code.console;

import org.jetbrains.annotations.NotNull;

/**
 * Created by BuriedInCode on 2021-Jun-02.
 */
public enum Colour {
	RESET("\u001B[0m"),
	BLACK("\u001B[30m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	MAGENTA("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m");

	@NotNull
	private final String ansicode;

	Colour(@NotNull String ansicode) {
		this.ansicode = ansicode;
	}

	@NotNull
	@Override
	public String toString() {
		return ansicode;
	}
}
