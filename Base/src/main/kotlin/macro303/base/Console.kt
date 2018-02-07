package macro303.base

import macro303.base.pieces.*
import org.apache.logging.log4j.LogManager
import java.util.*

/**
 * Created by Macro303 on 2017-10-20.
 */
object Console {
	private val LOGGER = LogManager.getLogger(Console::class.java)

	private val headingColour = Colour.MAGENTA
	private val importantColour = Colour.YELLOW
	private val messageColour = Colour.WHITE
	private val squareColour = Colour.GREEN

	private fun colourConsole(
		title: String? = null,
		titleColour: Colour = headingColour,
		message: String?,
		messageColour: Colour
	) {
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

	fun showValue(title: String?, message: String) = colourConsole(
		title = "$title: ",
		titleColour = importantColour,
		message = message,
		messageColour = messageColour
	)

	fun showSquare(piece: Piece?) {
		print((piece?.teamColour ?: squareColour).colourCode)
		if (piece != null)
			print(" ${getSymbol(piece = piece)} ")
		else
			print(" ~ ")
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
				val temp: Piece? = when {
					input.contains(
						"<${getSymbol(Archer::class.java)}>",
						ignoreCase = true
					) || input.contains(Archer::class.java.simpleName, ignoreCase = true) -> {
						Archer(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(Emperor::class.java)}>",
						ignoreCase = true
					) || input.contains(Emperor::class.java.simpleName, ignoreCase = true) -> {
						Emperor(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(General::class.java)}>",
						ignoreCase = true
					) || input.contains(General::class.java.simpleName, ignoreCase = true) -> {
						General(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(Lancer::class.java)}>",
						ignoreCase = true
					) || input.contains(Lancer::class.java.simpleName, ignoreCase = true) -> {
						Lancer(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(Merchant::class.java)}>",
						ignoreCase = true
					) || input.contains(Merchant::class.java.simpleName, ignoreCase = true) -> {
						Merchant(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(Scholar::class.java)}>",
						ignoreCase = true
					) || input.contains(Scholar::class.java.simpleName, ignoreCase = true) -> {
						Scholar(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(Spearman::class.java)}>",
						ignoreCase = true
					) || input.contains(Spearman::class.java.simpleName, ignoreCase = true) -> {
						Spearman(teamColour = Colour.RESET)
					}
					input.contains(
						"<${getSymbol(Thief::class.java)}>",
						ignoreCase = true
					) || input.contains(Thief::class.java.simpleName, ignoreCase = true) -> {
						Thief(teamColour = Colour.RESET)
					}
					else -> null
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
		showValue(
			title = "Help <Symbol>",
			message = "Shows you all the information about the piece with that symbol (Symbol must be inside <>)."
		)
		showValue(title = "Help Name", message = "Shows you all the information about the piece with that name.")
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
		showTitle(title = "${piece::class.java.simpleName} Help")
		showValue(title = "Piece", message = piece::class.java.simpleName)
		showValue(title = "Symbol", message = getSymbol(piece = piece))
		showValue(title = "Max Distance", message = piece.maxDistance.toString())
		showValue(title = "Valid Directions", message = Arrays.toString(piece.validDirections))
		when (piece) {
			is Emperor -> showValue(
				title = "Ability",
				message = "The game is won if the Emperor is taken or if the Emperor is the only piece remaining to the losing player"
			)
			is Merchant -> showValue(
				title = "Ability",
				message = "The Merchant can move to any vacant square surrounding the square occupied by the Emperor, if the route is unobstructed by another piece."
			)
			is Scholar -> showValue(
				title = "Ability",
				message = "Any piece in an surrounding square to the Scholar is protected and cannot be taken."
			)
			is Thief -> showValue(
				title = "Ability",
				message = "Any piece taken by the Thief is then placed back on the board under the player's control in one of the surrounding squares."
			)
		}
	}

	private fun getSymbol(piece: Piece): String {
		return getSymbol(clazz = piece::class.java)
	}

	private fun getSymbol(clazz: Class<*>): String {
		return clazz.simpleName.subSequence(startIndex = 0, endIndex = 2).toString()
	}
}
