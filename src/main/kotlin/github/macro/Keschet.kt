package github.macro

import github.macro.Util.P1_COLOUR
import github.macro.Util.P2_COLOUR
import github.macro.Util.validMovement
import github.macro.console.Colour
import github.macro.console.Console.displayMessage
import github.macro.console.Console.displayPrompt
import github.macro.pieces.*
import github.macro.players.AutoPlayer
import github.macro.players.ConsolePlayer
import github.macro.players.Player
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import kotlin.math.abs

/**
 * Created by Macro303 on 2018-02-08.
 */
class Keschet(private val p1: Player, private val p2: Player) {
	private val board: Board = Board()

	private fun setPlayer(colour: Colour): Player {
		val player: Player = ConsolePlayer(displayPrompt("Player name"), colour)
		displayMessage("Welcome Player ${player.name}", player.colour)
		return player
	}

	private fun placePieces() {
		for (x in 0 until 7) {
			for (player in arrayOf(p1, p2)) {
				if (x < 1) {
					placePiece(player, Emperor(player))
					placePiece(player, General(player))
					placePiece(player, Scholar(player))
				}
				if (x < 2)
					placePiece(player, Merchant(player))
				if (x < 3)
					placePiece(player, Thief(player))
				if (x < 4)
					placePiece(player, Lancer(player))
				if (x < 5)
					placePiece(player, Archer(player))
				placePiece(player, Spearman(player))
			}
		}
	}

	private fun placePiece(player: Player, piece: Piece) {
		board.draw(true)
		displayMessage("Placing ${piece.name}", player.colour)
		var placed = false
		do {
			val location = player.placePiece(board, piece) ?: continue
			if (location.piece != null)
				displayMessage("Must be placed on an empty square => $location")
			else if ((player.colour != P1_COLOUR || location.row >= 3) && (player.colour != P2_COLOUR || location.row <= 6))
				displayMessage("Must be placed within 3 rows on your side => $location")
			else {
				location.piece = piece
				placed = true
			}
		} while (!placed)
	}

	private fun executeTurn(player: Player) {
		var valid = false
		do {
			board.draw()
			displayMessage("Select a Piece", player.colour)
			val fromLocation = player.selectPiece(board) ?: continue
			if (fromLocation?.piece != null && fromLocation.piece!!.player == player) {
				board.draw(false, fromLocation)
				displayMessage("Move ${fromLocation.piece!!.name}", player.colour)
				val toLocation = player.movePieceTo(board, fromLocation.piece!!) ?: continue
				if (toLocation.piece == null || toLocation.piece!!.player != player) {
					val validMovement = validMovement(board, fromLocation, toLocation)
					if (validMovement) {
						var taken: Piece? = null
						if (toLocation.piece != null && fromLocation.piece is Thief)
							taken = toLocation.piece
						toLocation.piece = fromLocation.piece
						fromLocation.piece = null
						valid = true
						taken?.let { stealPiece(toLocation, it, player) }
					} else
						displayMessage("That is an invalid move try again")
				} else if (toLocation.piece != null && toLocation.piece!!.player == player)
					displayMessage("That's your piece, you can't take your own piece")
				else
					displayMessage("You did something wrong. Call the Wizard!")
			} else if (fromLocation.piece == null)
				displayMessage("No Piece at that location")
			else if (fromLocation.piece!!.player != player)
				displayMessage("That's not your piece, put it back")
			else
				displayMessage("You did something wrong. Call the Wizard!")
		} while (!valid)
	}

	private fun checkWinCondition(): Player? {
		val player1Count = board.countPieces(p1)
		val player1Emperor = board.findPiece(Emperor::class.java, p1) != null
		val player2Count = board.countPieces(p2)
		val player2Emperor = board.findPiece(Emperor::class.java, p2) != null
		val winner = when {
			player1Count <= 1 || !player1Emperor -> p2
			player2Count <= 1 || !player2Emperor -> p1
			else -> null
		}
		if (winner != null)
			displayMessage("${winner.name} Wins", winner.colour)
		return winner
	}

	private fun stealPiece(location: Square, piece: Piece, player: Player) {
		piece.player = player
		board.draw(false, location)
		displayMessage("Select location for stolen ${piece.name}", player.colour)
		var valid = false
		do {
			val newLocation = player.placePiece(board, piece) ?: continue
			if (newLocation.piece == null) {
				valid = nextTo(newLocation, location)
				if (valid)
					newLocation.piece = piece
				else
					displayMessage("Must be placed next to Thief")
			}
		} while (!valid)
	}

	private fun nextTo(newLocation: Square, oldLocation: Square): Boolean {
		val row = abs(oldLocation.row - newLocation.row)
		val col = abs(oldLocation.col - newLocation.col)
		return row <= 1 && col <= 1
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Keschet::class.java)

		@JvmStatic
		fun main(args: Array<String>) {
			checkLogLevels()
			LOGGER.info("Welcome to Keschet")

			val player1 = AutoPlayer(displayPrompt("Player 1 name"), P1_COLOUR)
			val player2 = AutoPlayer(displayPrompt("Player 2 name"), P2_COLOUR)
			Keschet(player1, player2)
		}

		private fun checkLogLevels() {
			Level.values().sorted().forEach {
				LOGGER.log(it, "{} is Visible", it.name())
			}
		}
	}

	init {
		placePieces()
		while (checkWinCondition() == null) {
			executeTurn(p1)
			if (checkWinCondition() == null)
				executeTurn(p2)
		}
	}
}