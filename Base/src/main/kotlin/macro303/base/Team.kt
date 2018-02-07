package macro303.base

import macro303.base.pieces.*
import java.util.*

/**
 * Created by Macro303 on 2017-10-20.
 */
class Team(val colour: Colour) {
	/**
	 * 1 Emperor
	 * 1 General
	 * 1 Scholar
	 * 2 Merchants
	 * 3 Thieves
	 * 4 Lancers
	 * 5 Archers
	 * 8 Spearman
	 */
	val pieces = ArrayList<Piece>()

	init {
		pieces.add(Emperor(teamColour = colour))
		pieces.add(General(teamColour = colour))
		pieces.add(Scholar(teamColour = colour))
		(0 until 2).forEach { pieces.add(Merchant(teamColour = colour)) }
		(0 until 3).forEach { pieces.add(Thief(teamColour = colour)) }
		(0 until 4).forEach { pieces.add(Lancer(teamColour = colour)) }
		(0 until 5).forEach { pieces.add(Archer(teamColour = colour)) }
		(0 until 8).forEach { pieces.add(Spearman(teamColour = colour)) }
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Team) return false

		if (colour != other.colour) return false
		if (pieces != other.pieces) return false

		return true
	}

	override fun hashCode(): Int {
		var result = colour.hashCode()
		result = 31 * result + pieces.hashCode()
		return result
	}

	override fun toString(): String {
		return "Team(colour=$colour, pieces=$pieces)"
	}
}
