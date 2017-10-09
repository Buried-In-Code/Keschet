package macro303.keschet

import org.slf4j.LoggerFactory

internal object Console {
	private val LOGGER = LoggerFactory.getLogger(Console.javaClass)

	fun showTitle(title: String, colour: Colour) {
		Thread.sleep(10)
		print(colour.colourCode)
		println("=".repeat(title.length + 4))
		println("  $title  ")
		println("=".repeat(title.length + 4))
		print(Colour.RESET.colourCode)
	}

	fun info(message: String?) {
		colourOutput(message = message, colour = Colour.WHITE)
	}

	fun info(title: String?, value: String?) {
		colourOutput(message = "$title: ${Colour.WHITE.colourCode}$value", colour = Colour.CYAN)
		println()
	}

	fun error(message: String?) {
		colourOutput(message = message, colour = Colour.YELLOW)
		println()
	}

	fun cell(cell: Cell) {
		colourOutput(message = " ${cell.piece?.symbol ?: "~"} ", colour = cell.piece?.teamColour ?: cell.colour)
	}

	private fun colourOutput(message: String?, colour: Colour) {
		Thread.sleep(10)
		print(colour.colourCode)
		print(message)
		print(Colour.RESET.colourCode)
	}
}