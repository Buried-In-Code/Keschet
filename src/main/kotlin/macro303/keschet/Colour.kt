package macro303.keschet

internal enum class Colour(val colourCode: String) {
	RESET(colourCode = "\u001B[0m"),
	RED(colourCode = "\u001B[31m"),
	GREEN(colourCode = "\u001B[32m"),
	YELLOW(colourCode = "\u001B[33m"),
	BLUE(colourCode = "\u001B[34m"),
	MAGENTA(colourCode = "\u001B[35m"),
	WHITE(colourCode = "\u001B[37m");
}