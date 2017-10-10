package macro303.keschet

import macro303.keschet.pieces.IPiece

internal data class Square(val colour: Colour) {
	var piece: IPiece? = null

	override fun toString(): String {
		return "Square(colour=$colour, piece=$piece)"
	}
}