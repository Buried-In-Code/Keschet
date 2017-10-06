package macro303.keschet.pieces

import macro303.keschet.Team

internal class Merchant(team: Team) : Piece(teamColour = team.colour, distance = 1, symbol = "M") {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Merchant) return false
		if (!super.equals(other)) return false
		return true
	}

	override fun hashCode(): Int {
		return super.hashCode()
	}

	override fun toString(): String {
		return "Merchant() ${super.toString()}"
	}
}