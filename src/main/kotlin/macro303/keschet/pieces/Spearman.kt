package macro303.keschet.pieces

import macro303.keschet.Colour
import macro303.keschet.Direction
import java.util.*

internal data class Spearman(override val teamColour: Colour) : IPiece {
	override val maxDistance = 2
	override val symbol = "P"
	override val validDirections = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)

	override fun toString(): String {
		return "Spearman(teamColour=$teamColour, maxDistance=$maxDistance, symbol='$symbol', validDirections=${Arrays.toString(validDirections)})"
	}
}