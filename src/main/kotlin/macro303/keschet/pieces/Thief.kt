package macro303.keschet.pieces

import macro303.keschet.pieces.Piece.Direction.*
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
class Thief(team: Piece.Team) : Piece(ArrayList(Arrays.asList<Direction>(HORIZONTAL, VERTICAL, DIAGONAL)), 1, team) {
	override fun toString(): String {
		return "Thief() ${super.toString()}"
	}
}