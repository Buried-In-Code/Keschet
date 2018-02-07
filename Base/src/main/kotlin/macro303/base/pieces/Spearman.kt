package macro303.base.pieces

import macro303.base.Colour
import macro303.base.Direction

/**
 * Created by Macro303 on 2017-10-20.
 */
class Spearman(teamColour: Colour) : Piece(
	teamColour = teamColour,
	maxDistance = 2,
	validDirections = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
) {
	override val symbol: String = Spearman.symbol

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Spearman) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Spearman() ${super.toString()}"
	}

	companion object {
		const val symbol = "\u2694"
	}
}