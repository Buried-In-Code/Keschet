package macro303.base

import macro303.base.pieces.Emperor
import macro303.base.pieces.Piece
import org.apache.logging.log4j.LogManager
import java.util.*

/**
 * Created by Macro303 on 2017-10-20.
 */
class Board : IBoard {
	private var board = Array(SIZE, { Array(SIZE, { Square() }) })

	fun draw() {
		(0..SIZE).forEach { column ->
			(0..SIZE).forEach {
				when {
					column == 0 -> print("${if (it == 0) "" else " "}$it${if (it == SIZE) "" else " "}")
					it == 0 -> print("$column${if (column == SIZE) "" else " "}")
					else -> Console.showSquare(getSquare(Pair(it, column))!!.piece)
				}
			}
			println()
		}
	}

	override fun getSquare(square: Pair<Int, Int>): Square? {
		return if (square.first - 1 < 0 || square.first > SIZE || square.second - 1 < 0 || square.second > SIZE) null else board[square.second - 1][square.first - 1]
	}

	override fun countPieces(team: Team): Int {
		var counter = 0
		board.forEach { it -> counter += it.count { it.piece != null && it.piece?.teamColour == team.colour && it.piece !is Emperor } }
		LOGGER.trace("int countPieces(Player) = $counter")
		return counter
	}

	override fun pieceStillOnBoard(clazz: Class<*>, teamColour: Colour): Boolean {
		val stillAvailable =
			board.any { it.any { it.piece != null && it.piece?.teamColour == teamColour && it.piece?.javaClass == clazz } }
		LOGGER.trace("boolean pieceStillOnBoard(Class, Colour) = $stillAvailable")
		return stillAvailable
	}

	override fun getAllSurroundingPieces(squareLocation: Pair<Int, Int>): ArrayList<Piece> {
		val pieces = ArrayList<Piece>()
		var square = getSquare(square = Pair(squareLocation.first, squareLocation.second + 1))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first + 1, squareLocation.second + 1))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first + 1, squareLocation.second))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first + 1, squareLocation.second - 1))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first, squareLocation.second - 1))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first - 1, squareLocation.second - 1))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first - 1, squareLocation.second))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		square = getSquare(square = Pair(squareLocation.first - 1, squareLocation.second + 1))
		if (square != null && square.piece != null)
			pieces.add(square.piece!!)
		LOGGER.trace("ArrayList<Piece> getAllSurroundingPieces(Pair<Int, Int>) = $pieces")
		return pieces
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Board::class.java)
		private const val SIZE = 10
	}
}