package macro303.keschet

import macro303.keschet.pieces.IPiece

internal data class Cell(val colour: Colour) {
	var piece: IPiece? = null

	override fun toString(): String {
		return "Cell(colour=$colour, piece=$piece)"
	}
}