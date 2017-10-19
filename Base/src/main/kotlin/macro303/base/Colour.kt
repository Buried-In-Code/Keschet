package macro303.base

import java.awt.Color

enum class Colour(val colourCode: String, private val colour: Color) {
	RESET("\u001B[0m", Color.BLACK),
	RED("\u001B[31m", Color.RED),
	GREEN("\u001B[32m", Color.GREEN),
	YELLOW("\u001B[33m", Color.YELLOW),
	BLUE("\u001B[34m", Color.BLUE),
	MAGENTA("\u001B[35m", Color.MAGENTA),
	CYAN("\u001B[36m", Color.CYAN),
	WHITE("\u001B[37m", Color.WHITE);

	val hexCode: String
		get() = String.format("#%02X%02X%02X%02X", colour.red, colour.green, colour.blue, colour.alpha)
}