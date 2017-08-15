package macro303.keschet

class Team(val colour: Colour){
  private val pieces = arrayOf(Emperor(colour), General(colour), Scholar(colour), Merchant(colour), Merchant(colour), Thief(colour), Thief(colour), Thief(colour), Lancer(colour), Lancer(colour), Lancer(colour), Lancer(colour), Archer(colour), Archer(colour), Archer(colour), Archer(colour), Archer(colour), Spearman(colour), Spearman(colour), Spearman(colour), Spearman(colour), Spearman(colour), Spearman(colour), Spearman(colour), Spearman(colour))

  enum class Colour{
    BLACK,
    WHITE
  }
}
