package macro303.keschet.pieces

import macro303.keschet.Colour
import macro303.keschet.Direction
import java.util.*

internal data class Lancer(override var teamColour: Colour) : IPiece {
	override val maxDistance = 10
	override val symbol = "L"
	override val validDirections = arrayOf(Direction.NORTH_EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_WEST)

	override fun toString(): String {
		return "Lancer(teamColour=$teamColour, maxDistance=$maxDistance, symbol='$symbol', validDirections=${Arrays.toString(validDirections)})"
	}
}