package macro303.keschet.pieces

import macro303.keschet.Team

internal class Lancer(team: Team) : Piece(teamColour = team.colour, distance = 10, symbol = "L") {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Lancer) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Lancer() ${super.toString()}"
	}
}