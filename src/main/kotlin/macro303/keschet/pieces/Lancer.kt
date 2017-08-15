package macro303.keschet.pieces

import macro303.keschet.pieces.Piece.Direction.DIAGONAL
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
class Lancer(team: Piece.Team) : Piece(ArrayList(Arrays.asList<Direction>(DIAGONAL)), 10, team) {
	override fun toString(): String {
		return "Lancer() ${super.toString()}"
	}
}