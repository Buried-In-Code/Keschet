package macro303.keschet

import macro303.keschet.pieces.*
import org.apache.logging.log4j.LogManager
import java.util.*

object Console {
	private val LOGGER = LogManager.getLogger(Console::class.java)

	private val headingColour = Colour.MAGENTA
	private val importantColour = Colour.YELLOW
	private val messageColour = Colour.WHITE
	private val squareColour = Colour.GREEN

	private fun colourConsole(title: String? = null, titleColour: Colour = headingColour, message: String?, messageColour: Colour) {
		if (title != null) {
			print(titleColour.colourCode)
			print(title)
		}
		print(messageColour.colourCode)
		print(message)
		println(Colour.RESET.colourCode)
	}

	fun showTitle(title: String?, colour: Colour = headingColour) {
		colourConsole(message = "=".repeat(title?.length?.plus(4) ?: 8), messageColour = colour)
		colourConsole(message = "  $title  ", messageColour = colour)
		colourConsole(message = "=".repeat(title?.length?.plus(4) ?: 8), messageColour = colour)
	}

	fun showMessage(message: String?) = colourConsole(message = message, messageColour = messageColour)

	fun showError(message: String?) = colourConsole(message = message, messageColour = importantColour)

	fun showValue(title: String?, message: String) = colourConsole(title = "$title: ", titleColour = importantColour, message = message, messageColour = messageColour)

	fun showSquare(piece: Piece?) {
		print((piece?.teamColour ?: squareColour).colourCode)
		print(" ${piece?.symbol ?: "~"} ")
		print(Colour.RESET.colourCode)
	}

	fun showRules() {
		showTitle(title = "Rules")
		showMessage(message = "At the start of the game a player may place any piece in any square in the first 3 rows at the player's end of the board.")
		showMessage(message = "A piece is taken if the square it occupies is occupied by an opposing piece.")
		showPieces()
	}

	fun helpMenu(input: String) {
		when {
			input.equals("Help", ignoreCase = true) -> showHelp()
			input.contains("pieces", ignoreCase = true) -> showPieces()
			input.contains("rules", ignoreCase = true) -> showRules()
			else -> {
				var temp: Piece? = null
				when {
					input.contains("<a>", ignoreCase = true) || input.contains("archer", ignoreCase = true) -> temp = Archer(teamColour = Colour.RESET)
					input.contains("<e>", ignoreCase = true) || input.contains("emperor", ignoreCase = true) -> temp = Emperor(teamColour = Colour.RESET)
					input.contains("<g>", ignoreCase = true) || input.contains("general", ignoreCase = true) -> temp = General(teamColour = Colour.RESET)
					input.contains("<l>", ignoreCase = true) || input.contains("lancer", ignoreCase = true) -> temp = Lancer(teamColour = Colour.RESET)
					input.contains("<m>", ignoreCase = true) || input.contains("merchant", ignoreCase = true) -> temp = Merchant(teamColour = Colour.RESET)
					input.contains("<c>", ignoreCase = true) || input.contains("scholar", ignoreCase = true) -> temp = Scholar(teamColour = Colour.RESET)
					input.contains("<p>", ignoreCase = true) || input.contains("spearman", ignoreCase = true) -> temp = Spearman(teamColour = Colour.RESET)
					input.contains("<t>", ignoreCase = true) || input.contains("thief", ignoreCase = true) -> temp = Thief(teamColour = Colour.RESET)
				}
				if (temp != null)
					showPiece(temp)
			}
		}
	}

	private fun showHelp() {
		showTitle(title = "Help")
		showValue(title = "Help Rules", message = "Shows the rules.")
		showValue(title = "Help Pieces", message = "Shows you all the pieces.")
		showValue(title = "Help <Symbol> OR Help Name", message = "Shows you all the information about the piece with that symbol or name (Symbol must be inside <>).")
	}

	private fun showPieces() {
		showPiece(piece = Archer(teamColour = Colour.RESET))
		showPiece(piece = Emperor(teamColour = Colour.RESET))
		showPiece(piece = General(teamColour = Colour.RESET))
		showPiece(piece = Lancer(teamColour = Colour.RESET))
		showPiece(piece = Merchant(teamColour = Colour.RESET))
		showPiece(piece = Scholar(teamColour = Colour.RESET))
		showPiece(piece = Spearman(teamColour = Colour.RESET))
		showPiece(piece = Thief(teamColour = Colour.RESET))
	}

	private fun showPiece(piece: Piece) {
		showTitle(title = "${piece.javaClass.simpleName} Help")
		showValue(title = "Piece", message = piece.javaClass.simpleName)
		showValue(title = "Symbol", message = piece.symbol)
		showValue(title = "Max Distance", message = piece.maxDistance.toString())
		showValue(title = "Valid Directions", message = Arrays.toString(piece.validDirections))
		when (piece) {
			is Emperor -> showValue(title = "Ability", message = "The game is won if the Emperor is taken or if the Emperor is the only piece remaining to the losing player")
			is Merchant -> showValue(title = "Ability", message = "The Merchant can move to any vacant square surrounding the square occupied by the Emperor, if the route is unobstructed by another piece.")
			is Scholar -> showValue(title = "Ability", message = "Any piece in an surrounding square to the Scholar is protected and cannot be taken.")
			is Thief -> showValue(title = "Ability", message = "Any piece taken by the Thief is then placed back on the board under the player's control in one of the surrounding squares.")
		}
	}

	enum class Colour(internal val colourCode: String) {
		RESET(colourCode = "\u001B[0m"),
		RED(colourCode = "\u001B[31m"),
		GREEN(colourCode = "\u001B[32m"),
		YELLOW(colourCode = "\u001B[33m"),
		BLUE(colourCode = "\u001B[34m"),
		MAGENTA(colourCode = "\u001B[35m"),
		CYAN(colourCode = "\u001B[36m"),
		WHITE(colourCode = "\u001B[37m")
	}
}
