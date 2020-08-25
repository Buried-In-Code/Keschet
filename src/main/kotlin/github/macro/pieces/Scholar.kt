package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-09.
 */
class Scholar(player: Player) : Piece(
	player = player,
	distance = 2,
	symbol = "C",
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
//	private static final String symbol = "\uD83D\uDCDA";
//	private static final String symbol = "📚";
//	private static final String symbol = "C";

	override fun toString(): String = "Scholar() ${super.toString()}"
}