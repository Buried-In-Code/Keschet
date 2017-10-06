package macro303.keschet.pieces

import macro303.keschet.Team

internal class Emperor(team: Team) : Piece(teamColour = team.colour, distance = 4, symbol = "E") {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Emperor) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Emperor() ${super.toString()}"
	}
}