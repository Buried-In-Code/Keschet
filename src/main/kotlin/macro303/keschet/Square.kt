package macro303.keschet

import macro303.keschet.pieces.Piece

/**
 * Created by Macro303 on 27/06/2017.
 */
class Square(val x: Int, val y: Int) {
	var piece: Piece? = null

	override fun toString(): String {
		return "Square(x=$x, y=$y, piece=$piece)"
	}
}