package macro303.keschet.pieces

import macro303.keschet.Team

internal class General(team: Team) : Piece(teamColour = team.colour, distance = 10, symbol = "G") {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is General) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "General() ${super.toString()}"
	}
}