package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-08.
 */
abstract class Piece protected constructor(
	var player: Player,
	val distance: Int,
	val symbol: String,
	val directions: Array<Direction>
) {
	val name: String = javaClass.simpleName
}