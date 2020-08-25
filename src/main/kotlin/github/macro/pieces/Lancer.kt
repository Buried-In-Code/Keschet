package github.macro.pieces

import github.macro.Direction
import github.macro.players.Player

/**
 * Created by Macro303 on 2018-02-09.
 */
class Lancer(player: Player) : Piece(
	player = player,
	distance = 10,
	symbol = "L",
	directions = arrayOf(Direction.NORTH_EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_WEST)
) {
//	private static final String symbol = "\uD83C\uDFC7";
//	private static final String symbol = "🏇";
//	private static final String symbol = "L";

	override fun toString(): String = "Lancer() ${super.toString()}"
}