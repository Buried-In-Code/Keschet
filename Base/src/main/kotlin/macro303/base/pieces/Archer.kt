package macro303.base.pieces

import macro303.base.Colour
import macro303.base.Direction

/**
 * Created by Macro303 on 2017-10-20.
 */
class Archer(teamColour: Colour) : Piece(
	teamColour = teamColour,
	maxDistance = 6,
	validDirections = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Archer) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Archer() ${super.toString()}"
	}
}