package macro303.keschet.pieces

import macro303.keschet.Team

internal class Thief(team: Team) : Piece(teamColour = team.colour, distance = 1, symbol = "T") {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Thief) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Thief() ${super.toString()}"
	}
}