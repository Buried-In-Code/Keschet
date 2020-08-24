package github.macro.players

import github.macro.Board
import github.macro.Square
import github.macro.console.Colour
import github.macro.pieces.Piece

/**
 * Created by Macro303 on 2018-02-08.
 */
abstract class Player protected constructor(val name: String, val colour: Colour) {
	abstract fun placePiece(board: Board, piece: Piece): Square?
	abstract fun selectPiece(board: Board): Square?
	abstract fun movePieceTo(board: Board, piece: Piece): Square?

	override fun toString(): String {
		return "Player(name='$name', colour=$colour)"
	}
}