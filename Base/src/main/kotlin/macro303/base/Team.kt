package macro303.base

import macro303.base.pieces.*
import org.apache.logging.log4j.LogManager
import java.util.*

class Team(val colour: Colour) {
	val pieces: ArrayList<Piece> = ArrayList()

	init {
		pieces.add(Emperor(teamColour = colour))
		pieces.add(General(teamColour = colour))
		pieces.add(Scholar(teamColour = colour))
		(0..1).forEach { pieces.add(Merchant(teamColour = colour)) }
		(0..2).forEach { pieces.add(Thief(teamColour = colour)) }
		(0..3).forEach { pieces.add(Lancer(teamColour = colour)) }
		(0..4).forEach { pieces.add(Archer(teamColour = colour)) }
		(0..7).forEach { pieces.add(Spearman(teamColour = colour)) }
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

	companion object {
		private val LOGGER = LogManager.getLogger(Team::class.java)
	}
}
