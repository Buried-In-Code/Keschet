package macro303.keschet.pieces

import macro303.keschet.Board
import macro303.keschet.Console
import macro303.keschet.Direction
import org.apache.logging.log4j.LogManager
import java.util.*

abstract class Piece(var teamColour: Console.Colour, val maxDistance: Int, val symbol: String, val validDirections: Array<Direction>) {

	open fun validMovement(start: Pair<Int, Int>, end: Pair<Int, Int>): Boolean {
		val valid = validDirections.contains(Board.calculateDirection(start = start, end = end)) && maxDistance >= Board.calculateDistance(start = start, end = end)
		LOGGER.trace("boolean validMovement(Pair<Integer, Integer>, Pair<Integer, Integer>) = $valid")
		return valid
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Piece) return false

		if (teamColour != other.teamColour) return false
		if (maxDistance != other.maxDistance) return false
		if (symbol != other.symbol) return false
		if (!Arrays.equals(validDirections, other.validDirections)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = teamColour.hashCode()
		result = 31 * result + maxDistance
		result = 31 * result + symbol.hashCode()
		result = 31 * result + Arrays.hashCode(validDirections)
		return result
	}

	override fun toString(): String {
		return "Piece(teamColour=$teamColour, maxDistance=$maxDistance, symbol='$symbol', validDirections=${Arrays.toString(validDirections)})"
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Piece::class.java)
	}
}