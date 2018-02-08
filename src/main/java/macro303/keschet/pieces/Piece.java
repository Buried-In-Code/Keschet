package macro303.keschet.pieces;

import macro303.keschet.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Piece {
	@NotNull
	private final Colour teamColour;
	private final int maxDistance;
	@NotNull
	private final String symbol;
	@NotNull
	private final ArrayList<Direction> validDirections;

	protected Piece(@NotNull Colour teamColour, int maxDistance, @NotNull String symbol, @NotNull Direction... validDirections) {
		this.teamColour = teamColour;
		this.maxDistance = maxDistance;
		this.symbol = symbol;
		this.validDirections = new ArrayList<>(Arrays.asList(validDirections));
	}

	@NotNull
	public Colour getTeamColour() {
		return teamColour;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	@NotNull
	public String getSymbol() {
		return symbol;
	}

	@NotNull
	public ArrayList<Direction> getValidDirections() {
		return validDirections;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Piece)) return false;

		Piece piece = (Piece) o;

		if (maxDistance != piece.maxDistance) return false;
		if (teamColour != piece.teamColour) return false;
		if (!symbol.equals(piece.symbol)) return false;
		return validDirections.equals(piece.validDirections);
	}

	@Override
	public int hashCode() {
		int result = teamColour.hashCode();
		result = 31 * result + maxDistance;
		result = 31 * result + symbol.hashCode();
		result = 31 * result + validDirections.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Piece{" +
				"teamColour=" + teamColour +
				", maxDistance=" + maxDistance +
				", symbol='" + symbol + '\'' +
				", validDirections=" + validDirections +
				'}';
	}
}