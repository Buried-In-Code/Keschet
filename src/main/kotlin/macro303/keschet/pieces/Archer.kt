package macro303.keschet.pieces

import macro303.keschet.Team

internal class Archer(team: Team) : Piece(teamColour = team.colour, distance = 6, symbol = "A") {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Archer) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Archer() ${super.toString()}"
	}
}