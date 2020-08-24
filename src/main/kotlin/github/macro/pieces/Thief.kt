package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-09.
 */
class Thief(player: Player) : Piece(
	player = player,
	distance = 1,
	symbol = "\uD83D\uDDE1",
	directions = arrayOf(
		Direction.NORTH,
		Direction.NORTH_EAST,
		Direction.EAST,
		Direction.SOUTH_EAST,
		Direction.SOUTH,
		Direction.SOUTH_WEST,
		Direction.WEST,
		Direction.NORTH_WEST
	)
) {
	//	private static final String symbol = "\uD83D\uDDE1";
	//	private static final String symbol = "T";
	override fun toString(): String = "Thief() ${super.toString()}"
}