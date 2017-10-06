package macro303.keschet.pieces

import macro303.keschet.Colour

internal abstract class Piece(val teamColour: Colour, val distance: Int, val symbol: String) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Piece) return false

		if (teamColour != other.teamColour) return false
		if (distance != other.distance) return false
		if (symbol != other.symbol) return false

		return true
	}

	override fun hashCode(): Int {
		var result = teamColour.hashCode()
		result = 31 * result + distance
		result = 31 * result + symbol.hashCode()
		return result
	}

	override fun toString(): String {
		return "Piece(teamColour=$teamColour, distance=$distance, symbol='$symbol')"
	}
}