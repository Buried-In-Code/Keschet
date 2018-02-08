package macro303.keschet;

import macro303.keschet.pieces.Emperor;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Team {
	@NotNull
	private final Colour colour;
	@NotNull
	private final ArrayList<Piece> pieces;

	public Team(@NotNull Colour colour) {
		this.colour = colour;
		this.pieces = new ArrayList<>();
		addPieces();
	}

	/**
	 * 1 Emperor
	 * 1 General
	 * 1 Scholar
	 * 2 Merchants
	 * 3 Thieves
	 * 4 Lancers
	 * 5 Archers
	 * 8 Spearman
	 */
	private void addPieces() {
		pieces.add(new Emperor(colour));
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	@NotNull
	public ArrayList<Piece> getPieces() {
		return pieces;
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
	public int hashCode() {
		int result = colour.hashCode();
		result = 31 * result + pieces.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Team{" +
				"colour=" + colour +
				", pieces=" + pieces +
				'}';
	}
}
