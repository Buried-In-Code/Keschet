package macro303.keschet.pieces

import macro303.keschet.Team

internal class Spearman(team: Team) : Piece(teamColour = team.colour, distance = 2, symbol = "P") {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Spearman) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Spearman() ${super.toString()}"
	}
}