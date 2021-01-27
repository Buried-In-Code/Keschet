package github.macro.players

import github.macro.Board
import github.macro.Square
import github.macro.console.Colour
import github.macro.console.Console.displayPrompt
import github.macro.pieces.Piece
import org.apache.logging.log4j.LogManager

/**
 * Created by Macro303 on 2018-02-12.
 */
open class ConsolePlayer(name: String, colour: Colour) : Player(name, colour) {
	override fun placePiece(board: Board, piece: Piece): Square {
		return requestLocation(board)
	}

	override fun placeStolenPiece(board: Board, piece: Piece, thiefSquare: Square): Square {
		return requestLocation(board)
	}

	override fun selectPiece(board: Board): Square {
		return requestLocation(board)
	}

	override fun movePieceTo(board: Board, piece: Piece): Square {
		return requestLocation(board)
	}

	protected fun requestLocation(board: Board): Square {
		var row: Int? = null
		var col: Int? = null
		do {
			val input = displayPrompt("Location (Row:Col)")
			val location = input.split(":".toRegex()).toTypedArray()
			try {
				row = location[0].toIntOrNull()
				col = location[1].toIntOrNull()
			} catch (e: NumberFormatException) {
				LOGGER.warn("Invalid Selection")
			} catch (e: ArrayIndexOutOfBoundsException) {
				LOGGER.warn("Invalid Selection")
			}
		} while (row == null || col == null)
		return board.getSquare(row, col) ?: requestLocation(board)
	}

	override fun toString(): String = "ConsolePlayer() ${super.toString()}"

	companion object {
		private val LOGGER = LogManager.getLogger()
	}
}