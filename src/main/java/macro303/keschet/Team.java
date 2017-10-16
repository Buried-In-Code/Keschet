package macro303.keschet;

import macro303.keschet.pieces.*;

import java.util.ArrayList;

public class Team {
	private Console.Colour colour;
	private ArrayList<Piece> pieces;

	public Team(Console.Colour colour) {
		this.colour = colour;
		pieces = new ArrayList<>();
		pieces.add(new Emperor(this.colour));
		pieces.add(new General(this.colour));
		pieces.add(new Scholar(this.colour));
		for (int i = 0; i < 2; i++)
			pieces.add(new Merchant(this.colour));
		for (int i = 0; i < 3; i++)
			pieces.add(new Thief(this.colour));
		for (int i = 0; i < 4; i++)
			pieces.add(new Lancer(this.colour));
		for (int i = 0; i < 5; i++)
			pieces.add(new Archer(this.colour));
		for (int i = 0; i < 8; i++)
			pieces.add(new Spearman(this.colour));
	}

	public Console.Colour getColour() {
		return colour;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	@Override
	public int hashCode() {
		int result = colour.hashCode();
		result = 31 * result + pieces.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Team)) return false;

		Team team = (Team) o;

		if (colour != team.colour) return false;
		return pieces.equals(team.pieces);
	}

	@Override
	public String toString() {
		return "Team{" +
				"colour=" + colour +
				", pieces=" + pieces +
				'}';
	}

}
