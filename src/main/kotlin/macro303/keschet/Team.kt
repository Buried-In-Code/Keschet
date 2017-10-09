package macro303.keschet

import macro303.keschet.pieces.*

internal data class Team(val colour: Colour) {
	var pieces: ArrayList<IPiece> = ArrayList()

	init {
		pieces.add(Emperor(teamColour = this.colour))
		pieces.add(General(teamColour = this.colour))
		pieces.add(Scholar(teamColour = this.colour))
		for (i in 0..1)
			pieces.add(Merchant(teamColour = this.colour))
		for (i in 0..2)
			pieces.add(Thief(teamColour = this.colour))
		for (i in 0..3)
			pieces.add(Lancer(teamColour = this.colour))
		for (i in 0..4)
			pieces.add(Archer(teamColour = this.colour))
		for (i in 0..7)
			pieces.add(Spearman(teamColour = this.colour))
	}

	override fun toString(): String {
		return "Team(colour=$colour, pieces=$pieces)"
	}

}