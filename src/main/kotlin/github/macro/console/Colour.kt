package github.macro.console

enum class Colour(private val ansicode: String) {
	RESET(ansicode = "\u001B[0m"),
	BLACK(ansicode = "\u001B[30m"),
	RED(ansicode = "\u001B[31m"),
	GREEN(ansicode = "\u001B[32m"),
	YELLOW(ansicode = "\u001B[33m"),
	BLUE(ansicode = "\u001B[34m"),
	MAGENTA(ansicode = "\u001B[35m"),
	CYAN(ansicode = "\u001B[36m"),
	WHITE(ansicode = "\u001B[37m");

	override fun toString(): String = ansicode
}