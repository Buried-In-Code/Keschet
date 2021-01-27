package github.macro

import github.macro.console.Colour
import github.macro.pieces.Piece

data class Square(val row: Int, val col: Int, var piece: Piece? = null) {
	fun display(): String = " ${piece?.player?.colour ?: Colour.BLACK}${piece?.symbol ?: '~'}${Colour.RESET} "
}