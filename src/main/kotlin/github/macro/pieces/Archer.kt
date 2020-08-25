package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-12.
 */
class Archer(player: Player) : Piece(
	player = player,
	distance = 6,
	symbol = "A",
	directions = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
) {
//	private static final String symbol = "\uD83C\uDFF9";
//	private static final String symbol = "🏹";
//	private static final String symbol = "A";

	override fun toString(): String = "Archer() ${super.toString()}"
}