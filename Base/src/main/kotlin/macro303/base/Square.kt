package macro303.base

import macro303.base.pieces.Piece

class Square {
	var piece: Piece? = null

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Square) return false

		if (piece != other.piece) return false

		return true
	}

	override fun hashCode(): Int {
		return piece?.hashCode() ?: 0
	}

	override fun toString(): String {
		return "Square(piece=$piece)"
	}
}