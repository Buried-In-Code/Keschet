package macro303.keschet

import macro303.keschet.pieces.Piece

internal class Cell(val colour: Colour) {
	var piece: Piece? = null

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Cell) return false

		if (colour != other.colour) return false
		if (piece != other.piece) return false

		return true
	}

	override fun hashCode(): Int {
		var result = colour.hashCode()
		result = 31 * result + (piece?.hashCode() ?: 0)
		return result
	}

	override fun toString(): String {
		return "Cell(colour=$colour, piece=$piece)"
	}
}