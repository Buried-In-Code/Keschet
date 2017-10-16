package macro303.keschet.pieces

import macro303.keschet.Board
import macro303.keschet.Console
import macro303.keschet.Direction
import macro303.keschet.Keschet
import org.apache.logging.log4j.LogManager

class Merchant(teamColour: Console.Colour) : Piece(teamColour = teamColour, maxDistance = 1, symbol = "M", validDirections = arrayOf(Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST)) {

	override fun validMovement(start: Pair<Int, Int>, end: Pair<Int, Int>): Boolean {
		val valid = Keschet.board.getAllSurroundingPieces(end).any { it is Emperor && it.teamColour === teamColour } && validDirections.contains(Board.calculateDirection(start = start, end = end)) || super.validMovement(start = start, end = end)
		LOGGER.trace("boolean validMovement(Pair<Integer, Integer>, Pair<Integer, Integer>) = $valid")
		return valid
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Merchant) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Merchant() ${super.toString()}"
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Merchant::class.java)
	}
}