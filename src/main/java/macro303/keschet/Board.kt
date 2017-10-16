package macro303.keschet

import macro303.keschet.pieces.Emperor
import macro303.keschet.pieces.Piece
import org.apache.logging.log4j.LogManager
import java.util.*

class Board {

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

	fun getSquare(square: Pair<Int, Int>): Square? {
		return if (square.first - 1 < 0 || square.first > SIZE || square.second - 1 < 0 || square.second > SIZE) null else board[square.second - 1][square.first - 1]
	}

	fun countPieces(team: Team): Int {
		var counter = 0
		board.forEach { it -> counter += it.count { it.piece != null && it.piece?.teamColour == team.colour && it.piece !is Emperor } }
		LOGGER.trace("int countPieces(Team) = $counter")
		return counter
	}

	fun pieceStillOnBoard(clazz: Class<*>, teamColour: Console.Colour): Boolean {
		val stillAvailable = board.any { it.any { it.piece != null && it.piece?.teamColour == teamColour && it.piece?.javaClass == clazz } }
		LOGGER.trace("boolean pieceStillOnBoard(Class, Colour) = $stillAvailable")
		return stillAvailable
	}

	fun getAllSurroundingPieces(squareLocation: Pair<Int, Int>): ArrayList<Piece> {
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
		LOGGER.trace("ArrayList<Piece> getAllSurroundingPieces(Pair<Integer, Integer>) = $pieces")
		return pieces
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Board::class.java)
		private val SIZE = 10

		fun calculateDistance(start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
			val distance = when (calculateDirection(start, end)) {
				Direction.NORTH, Direction.SOUTH -> start.second - end.second
				Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST -> start.first - end.first
				else -> 0
			}
			LOGGER.trace("int calculateDistance(Pair<Integer, Integer>, Pair<Integer, Integer>) = $distance")
			return distance
		}

		fun calculateDirection(start: Pair<Int, Int>, end: Pair<Int, Int>): Direction {
			var direction = Direction.INVALID
			when {
				end.first == start.first && end.second - start.second < 0 -> direction = Direction.NORTH
				end.first - start.first > 0 && end.second - start.second < 0 && Math.abs(end.first - start.first) - Math.abs(end.second - start.second) == 0 -> direction = Direction.NORTH_EAST
				end.first - start.first > 0 && end.second == start.second -> direction = Direction.EAST
				end.first - start.first > 0 && end.second - start.second > 0 && Math.abs(end.first - start.first) - Math.abs(end.second - start.second) == 0 -> direction = Direction.SOUTH_EAST
				end.first == start.first && end.second - start.second > 0 -> direction = Direction.SOUTH
				end.first - start.first < 0 && end.second - start.second > 0 && Math.abs(end.first - start.first) - Math.abs(end.second - start.second) == 0 -> direction = Direction.SOUTH_WEST
				end.first - start.first < 0 && end.second == start.second -> direction = Direction.WEST
				end.first - start.first < 0 && end.second - start.second < 0 && Math.abs(end.first - start.first) - Math.abs(end.second - start.second) == 0 -> direction = Direction.NORTH_WEST
			}
			LOGGER.trace("Direction calculateDirection(Pair<Integer, Integer>, Pair<Integer, Integer>) = $direction")
			return direction
		}
	}
}