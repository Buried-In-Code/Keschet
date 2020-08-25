package github.macro

import github.macro.console.Colour
import github.macro.players.Player
import org.apache.logging.log4j.LogManager

/**
 * Created by Macro303 on 2018-02-08.
 */
class Board {
	private val board by lazy {
		val tempBoard = arrayOfNulls<Array<Square>>(HEIGHT)
		for (row in 0 until HEIGHT) {
			val temp = arrayOfNulls<Square>(WIDTH)
			for (col in 0 until WIDTH)
				temp[col] = Square(row, col)
			tempBoard[row] = temp.filterNotNull().toTypedArray()
		}
		tempBoard.filterNotNull().toTypedArray()
	}

	fun countPieces(player: Player): Int {
		var counter = 0
		for (row in 0 until HEIGHT) {
			for (col in 0 until WIDTH) {
				val location = getSquare(row, col)!!
				if (location.piece != null && location.piece!!.player === player) counter++
			}
		}
		return counter
	}

	fun findPiece(clazz: Class<*>, player: Player): Square? {
		var temp: Square? = null
		for (row in 0 until HEIGHT) {
			for (col in 0 until WIDTH) {
				val location = getSquare(row, col) ?: continue
				location.piece ?: continue
				if (location.piece!!.javaClass == clazz && location.piece!!.player === player)
					temp = location
			}
		}
		return temp
	}

	fun getSquare(row: Int, col: Int): Square? {
		return try {
			board[row][col]
		} catch (aioobe: ArrayIndexOutOfBoundsException) {
			null
		}
	}

	@JvmOverloads
	fun draw(highlight: Boolean = false, location: Square? = null) {
		val piece = location?.piece
		for (row in -1 until HEIGHT) {
			loop@ for (col in -1 until WIDTH) {
				when {
					row == -1 && col == -1 -> print("  ")
					col == -1 -> print("$HEADER_COLOUR$row${Colour.RESET} ")
					row == -1 -> print(" $HEADER_COLOUR$col${Colour.RESET} ")
					else -> {
						val square = getSquare(row, col) ?: continue@loop
						if (location != null && Util.validMovement(this, location, square))
							print(" ${Colour.GREEN}${square.piece?.symbol ?: '~'}${Colour.RESET} ")
						else if (highlight && row < 3)
							print(" ${Util.P1_COLOUR}${square.piece?.symbol ?: '~'}${Colour.RESET} ")
						else if (highlight && row > 6)
							print(" ${Util.P2_COLOUR}${square.piece?.symbol ?: '~'}${Colour.RESET} ")
						else
							print(square.display())
					}
				}
			}
			println()
		}
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Board::class.java)
		private val HEADER_COLOUR = Colour.BLUE
		const val HEIGHT = 10
		const val WIDTH = 10
	}
}