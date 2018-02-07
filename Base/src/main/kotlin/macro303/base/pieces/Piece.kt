package macro303.base.pieces

import macro303.base.Board
import macro303.base.Colour
import macro303.base.Direction
import macro303.base.IBoard
import org.apache.logging.log4j.LogManager
import java.util.*

/**
 * Created by Macro303 on 2017-10-20.
 */
abstract class Piece(
	var teamColour: Colour,
	val maxDistance: Int,
	val validDirections: Array<Direction>
) {
	abstract val symbol: String

	open fun validMovement(start: Pair<Int, Int>, end: Pair<Int, Int>, board: Board): Boolean {
		val valid = validDirections.contains(
			IBoard.calculateDirection(
				start = start,
				end = end
			)
		) && maxDistance >= IBoard.calculateDistance(start = start, end = end)
		LOGGER.trace("boolean validMovement(Pair<Int, Int>, Pair<Int, Int>, Board) = $valid")
		return valid
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Piece) return false

		if (teamColour != other.teamColour) return false
		if (maxDistance != other.maxDistance) return false
		if (!Arrays.equals(validDirections, other.validDirections)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = teamColour.hashCode()
		result = 31 * result + maxDistance
		result = 31 * result + Arrays.hashCode(validDirections)
		return result
	}

	override fun toString(): String {
		return "Piece(teamColour=$teamColour, maxDistance=$maxDistance, validDirections=${Arrays.toString(
			validDirections
		)})"
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Piece::class.java)
	}
}