package macro303.keschet.pieces

import macro303.keschet.pieces.Piece.Direction.HORIZONTAL
import macro303.keschet.pieces.Piece.Direction.VERTICAL
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
class Archer(team: Piece.Team) : Piece(ArrayList(Arrays.asList<Direction>(HORIZONTAL, VERTICAL)), 6, team) {
	override fun toString(): String {
		return "Archer() ${super.toString()}"
	}
}