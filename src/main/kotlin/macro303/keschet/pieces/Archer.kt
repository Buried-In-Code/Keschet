package macro303.keschet.pieces

import macro303.keschet.Colour
import macro303.keschet.Direction

internal data class Archer(override var teamColour: Colour) : IPiece {
	override val maxDistance = 6
	override val symbol = "A"
	override val validDirections = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)

	override fun toString(): String {
		return "Archer(teamColour=$teamColour, distance=$maxDistance, symbol='$symbol')"
	}
}