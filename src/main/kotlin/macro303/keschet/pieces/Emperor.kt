package macro303.keschet.pieces

import macro303.keschet.Colour
import macro303.keschet.Direction
import java.util.*

internal data class Emperor(override var teamColour: Colour) : IPiece {
	override val maxDistance = 4
	override val symbol = "E"
	override val validDirections = arrayOf(Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST)

	override fun toString(): String {
		return "Emperor(teamColour=$teamColour, maxDistance=$maxDistance, symbol='$symbol', validDirections=${Arrays.toString(validDirections)})"
	}
}