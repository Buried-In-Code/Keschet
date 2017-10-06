package macro303.keschet

import macro303.keschet.pieces.*

internal data class Team(val colour: Colour) {
	var pieces: ArrayList<Piece> = ArrayList()

	init {
		pieces.add(Emperor(team = this))
		pieces.add(General(team = this))
		pieces.add(Scholar(team = this))
		for (i in 0..1)
			pieces.add(Merchant(team = this))
		for (i in 0..2)
			pieces.add(Thief(team = this))
		for (i in 0..3)
			pieces.add(Lancer(team = this))
		for (i in 0..4)
			pieces.add(Archer(team = this))
		for (i in 0..7)
			pieces.add(Spearman(team = this))
	}

	override fun toString(): String {
		return "Team(colour=$colour, pieces=$pieces)"
	}

}