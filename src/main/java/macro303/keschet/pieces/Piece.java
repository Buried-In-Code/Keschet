package macro303.keschet.pieces;

import macro303.keschet.Board;
import macro303.keschet.Console;
import macro303.keschet.Direction;
import macro303.keschet.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {
	private static final Logger LOGGER = LogManager.getLogger(Piece.class);

	protected Console.Colour teamColour;
	protected int maxDistance;
	protected String symbol;
	protected Direction[] validDirections;

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

	public boolean validMovement(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Direction direction = Board.calculateDirection(start, end);
		int distance = Board.calculateDistance(start, end);
		if (getValidDirections().contains(direction) && maxDistance >= distance) {
			LOGGER.trace("boolean validMovement(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + true);
			return true;
		}
		LOGGER.trace("boolean validMovement(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + false);
		return false;
	}

	public ArrayList<Direction> getValidDirections() {
		return new ArrayList<>(Arrays.asList(validDirections));
	}

	@Override
	public int hashCode() {
		int result = teamColour.hashCode();
		result = 31 * result + maxDistance;
		result = 31 * result + symbol.hashCode();
		result = 31 * result + Arrays.hashCode(validDirections);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Piece)) return false;

		Piece piece = (Piece) o;

		if (maxDistance != piece.maxDistance) return false;
		if (teamColour != piece.teamColour) return false;
		if (!symbol.equals(piece.symbol)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(validDirections, piece.validDirections);
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
