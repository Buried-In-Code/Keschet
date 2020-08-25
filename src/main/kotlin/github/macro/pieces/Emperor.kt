package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-08.
 */
class Emperor(player: Player) : Piece(
	player = player,
	distance = 4,
	symbol = "E",
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
//	private static final String symbol = "\u265A";
//	private static final String symbol = "♚";
//	private static final String symbol = "E";

	override fun toString(): String = "Emperor() ${super.toString()}"
}