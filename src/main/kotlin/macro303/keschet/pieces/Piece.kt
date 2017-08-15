package macro303.keschet.pieces

import macro303.keschet.Square
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
abstract class Piece internal constructor(val validDirections: ArrayList<Direction>, val movement: Int, val team: Team) {
	var x: Int = 0
		private set
	var y: Int = 0
		private set

	fun setLocation(location: Square) {
		x = location.x
		y = location.y
	}

	override fun toString(): String {
		return "Piece(validDirections=$validDirections, movement=$movement, team=$team, x=$x, y=$y)"
	}

	enum class Direction {
		VERTICAL,
		HORIZONTAL,
		DIAGONAL
	}

	enum class Team {
		BLACK,
		WHITE
	}

}