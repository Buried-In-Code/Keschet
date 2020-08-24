package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-12.
 */
class Spearman(player: Player) : Piece(
	player = player,
	distance = 2,
	symbol = "\u2694",
	directions = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
) {
	//	private static final String symbol = "\u2694";
	//	private static final String symbol = "P";
	override fun toString(): String = "Spearman() ${super.toString()}"
}