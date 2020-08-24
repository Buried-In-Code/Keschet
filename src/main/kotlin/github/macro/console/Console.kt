package github.macro.console

import org.apache.logging.log4j.LogManager

object Console {
	private val LOGGER = LogManager.getLogger(Console::class.java)

	@JvmStatic
	fun displayHeading(text: String) {
		colourConsole("=".repeat(text.length + 4), Colour.BLUE)
		colourConsole(text, Colour.BLUE)
		colourConsole("=".repeat(text.length + 4), Colour.BLUE)
	}

	@JvmStatic
	fun displayPrompt(text: String): String = Reader.readConsole(text)

	@JvmStatic
	fun displayAgreement(text: String): Boolean {
		val input = displayPrompt("$text (Y/N)")
		return input.equals("y", ignoreCase = true)
	}

	@JvmStatic
	@JvmOverloads
	fun displayMessage(text: String, colour: Colour = Colour.WHITE) = colourConsole(text, colour)

	private fun colourConsole(text: String?, colour: Colour) {
		println("$colour$text${Colour.RESET}")
	}
}