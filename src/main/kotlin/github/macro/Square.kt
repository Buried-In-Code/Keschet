package github.macro

import github.macro.console.Colour
import github.macro.pieces.Piece

class Square(val row: Int, val col: Int) {
	var piece: Piece? = null

	fun display(): String = " ${piece?.player?.colour ?: Colour.WHITE}${piece?.symbol ?: '~'}${Colour.RESET} "

	override fun toString(): String {
		return "Square(row=$row, col=$col, piece=$piece)"
	}
}