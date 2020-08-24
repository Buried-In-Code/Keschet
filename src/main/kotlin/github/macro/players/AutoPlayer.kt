package github.macro.players

import github.macro.Board
import github.macro.Square
import github.macro.Util
import github.macro.console.Colour
import github.macro.pieces.Piece

/**
 * Created by Macro303 on 2020-Aug-24
 */
class AutoPlayer(name: String, colour: Colour) : ConsolePlayer(name, colour) {
	override fun placePiece(board: Board, piece: Piece): Square? {
		val range = if(colour == Util.P1_COLOUR) 0..3 else Board.HEIGHT - 1 downTo 7
		for (row in range) {
			for (col in 0 until Board.WIDTH) {
				val square = board.getSquare(row, col)!!
				square.piece ?: return square
			}
		}
		return null
	}
}