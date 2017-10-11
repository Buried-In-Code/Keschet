package macro303.keschet.pieces;

import macro303.keschet.Board;
import macro303.keschet.Console;
import macro303.keschet.Direction;
import macro303.keschet.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Piece {
	protected Console.Colour teamColour;
	private int maxDistance;
	private String symbol;
	private Direction[] validDirections;

	Piece(Console.Colour teamColour, int maxDistance, String symbol, Direction[] validDirections) {
		this.teamColour = teamColour;
		this.maxDistance = maxDistance;
		this.symbol = symbol;
		this.validDirections = validDirections;
	}

	public Console.Colour getTeamColour() {
		return teamColour;
	}

	public void setTeamColour(Console.Colour teamColour) {
		this.teamColour = teamColour;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public String getSymbol() {
		return symbol;
	}

	public ArrayList<Direction> getValidDirections() {
		return new ArrayList<>(Arrays.asList(validDirections));
	}

	public boolean validMovement(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Direction direction = Board.calculateDirection(start, end);
		int distance = Board.calculateDistance(start, end);
		if (getValidDirections().contains(direction) && maxDistance >= distance)
			return true;
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Piece)) return false;
		Piece piece = (Piece) o;
		return getMaxDistance() == piece.getMaxDistance() &&
				getTeamColour() == piece.getTeamColour() &&
				Objects.equals(getSymbol(), piece.getSymbol()) &&
				getValidDirections().equals(piece.getValidDirections());
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(getTeamColour(), getMaxDistance(), getSymbol());
		result = 31 * result + getValidDirections().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Piece{" +
				"maxDistance=" + maxDistance +
				", symbol='" + symbol + '\'' +
				", teamColour=" + teamColour +
				", validDirections=" + Arrays.toString(validDirections) +
				'}';
	}

}
