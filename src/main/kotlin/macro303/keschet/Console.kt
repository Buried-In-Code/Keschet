package macro303.keschet

import org.slf4j.LoggerFactory

internal object Console {
	private val LOGGER = LoggerFactory.getLogger(Console.javaClass)

	fun info(message: String?) {
		colourOutput(message = message, colour = Colour.WHITE)
	}

	fun error(message: String? ){
		colourOutput(message = message, colour = Colour.YELLOW)
		println()
	}

	fun cell(cell: Cell) {
		colourOutput(message = " ${cell.piece?.symbol ?: "~"} ", colour = cell.piece?.teamColour ?: cell.colour)
	}

	private fun colourOutput(message: String?, colour: Colour) {
		print(colour.colourCode)
		print(message)
		print(Colour.RESET.colourCode)
	}
}