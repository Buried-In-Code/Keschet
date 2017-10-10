package macro303.keschet

import macro303.keschet.pieces.*
import org.slf4j.LoggerFactory
import java.util.*

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

	fun info(message: String?, colour: Colour = Colour.WHITE) {
		colourOutput(message = message, colour = colour)
	}

	fun info(title: String?, titleColour: Colour = Colour.CYAN, value: String?, valueColour: Colour = Colour.WHITE) {
		colourOutput(message = "$title: ${valueColour.colourCode}$value", colour = titleColour)
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

	fun showRules() {
		showTitle(title = "Rules", colour = Colour.YELLOW)
		info(title = "Setup", value = "At the start of the game a player may place any piece in any square in the first 3 rows at the player's end of the board.")
		info(title = "Gameplay", value = "A piece is taken if the square it occupies is occupied by an opposing piece.")
		helpMenu(input = "Help <Archer>")
		helpMenu(input = "Help <Emperor>")
		helpMenu(input = "Help <General>")
		helpMenu(input = "Help <Lancer>")
		helpMenu(input = "Help <Merchant>")
		helpMenu(input = "Help <Scholar>")
		helpMenu(input = "Help <Spearman>")
		helpMenu(input = "Help <Thief>")
	}

	fun helpMenu(input: String) {
		val temp: IPiece? = when {
			input.equals("Help", ignoreCase = true) -> {
				Console.info(message = "'Help <Symbol>' OR 'Help <Piece>' for information about piece")
				println()
				Console.info(title = "Example", value = "Help <A>")
				Console.info(title = "Example", value = "Help <Archer>")
				null
			}
			input.contains("All", ignoreCase = true) || input.contains("Pieces") -> {
				Console.showTitle(title = "All Pieces", colour = Colour.CYAN)
				Console.info(message = "<A>Archer\n<E>Emperor\n<G>General\n<L>Lancer\n<M>Merchant\n<C>Scholar\n<P>Spearman\n<T>Thief")
				null
			}
			input.contains("Rules", ignoreCase = true) -> {
				showRules()
				null
			}
			input.contains("<A>", ignoreCase = true) || input.contains("<Archer>", ignoreCase = true) -> Archer(teamColour = Colour.RESET)
			input.contains("<E>", ignoreCase = true) || input.contains("<Emperor>", ignoreCase = true) -> Emperor(teamColour = Colour.RESET)
			input.contains("<G>", ignoreCase = true) || input.contains("<General>", ignoreCase = true) -> General(teamColour = Colour.RESET)
			input.contains("<L>", ignoreCase = true) || input.contains("<Lancer>", ignoreCase = true) -> Lancer(teamColour = Colour.RESET)
			input.contains("<M>", ignoreCase = true) || input.contains("<Merchant>", ignoreCase = true) -> Merchant(teamColour = Colour.RESET)
			input.contains("<C>", ignoreCase = true) || input.contains("<Scholar>", ignoreCase = true) -> Scholar(teamColour = Colour.RESET)
			input.contains("<P>", ignoreCase = true) || input.contains("<Spearman>", ignoreCase = true) -> Spearman(teamColour = Colour.RESET)
			input.contains("<T>", ignoreCase = true) || input.contains("<Thief>", ignoreCase = true) -> Thief(teamColour = Colour.RESET)
			else -> null
		}
		if (temp != null) {
			Console.showTitle(title = "${temp.javaClass.simpleName} Help", colour = Colour.MAGENTA)
			Console.info(title = "Piece", value = temp.javaClass.simpleName)
			Console.info(title = "Symbol", value = temp.symbol)
			Console.info(title = "Max Distance", value = temp.maxDistance.toString())
			Console.info(title = "Valid Directions", value = Arrays.toString(temp.validDirections))
			when (temp) {
				is Emperor -> Console.info(title = "Ability", value = "The game is won if the Emperor is taken or if the Emperor is the only piece remaining to the losing player")
				is Merchant -> Console.info(title = "Ability [WIP]", value = "The Merchant can move to any vacant square adjoining the square occupied by the Emperor, if the route is unobstructed by another piece.")
				is Scholar -> Console.info(title = "Ability", value = "Any piece in an adjoining square to the Scholar is protected and cannot be taken.")
				is Thief -> Console.info(title = "Ability [WIP]", value = "Any piece taken by the Thief is then placed back on the board under the player's control in one of the adjoining squares.")
			}
		}
	}
}