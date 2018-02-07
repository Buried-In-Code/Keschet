package macro303.base.pieces

import macro303.base.Colour
import macro303.base.Direction

/**
 * Created by Macro303 on 2017-10-20.
 */
class Scholar(teamColour: Colour) : Piece(
	teamColour = teamColour,
	maxDistance = 2,
	validDirections = arrayOf(
		Direction.NORTH,
		Direction.NORTH_EAST,
		Direction.EAST,
		Direction.SOUTH_EAST,
		Direction.SOUTH,
		Direction.SOUTH_WEST,
		Direction.WEST,
		Direction.NORTH_WEST
	)
) {
	override val symbol: String = Scholar.symbol

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Scholar) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Scholar() ${super.toString()}"
	}

	companion object {
		const val symbol = "\uD83D\uDCDA"
	}
}