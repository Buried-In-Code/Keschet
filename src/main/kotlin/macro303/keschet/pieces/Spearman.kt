package macro303.keschet.pieces

import macro303.keschet.Console
import macro303.keschet.Direction

class Spearman(teamColour: Console.Colour) : Piece(teamColour = teamColour, maxDistance = 2, symbol = "P", validDirections = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {

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
}