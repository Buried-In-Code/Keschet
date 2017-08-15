package macro303.keschet.pieces

import macro303.keschet.pieces.Piece.Direction.*
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
class Emperor(team: Piece.Team) : Piece(ArrayList(Arrays.asList(HORIZONTAL, VERTICAL, DIAGONAL)), 4, team) {
	override fun toString(): String {
		return "Emperor() ${super.toString()}"
	}
}