package macro303.keschet.pieces;

import macro303.board_game.Colour;
import macro303.board_game.item.Item;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Piece implements Item {
	private final int maxDistance;
	@NotNull
	private final String symbol;
	@NotNull
	private final Direction[] validDirections;
	@NotNull
	private Colour teamColour;

	protected Piece(@NotNull Colour teamColour, int maxDistance, @NotNull String symbol, @NotNull Direction[] validDirections) {
		this.teamColour = teamColour;
		this.maxDistance = maxDistance;
		this.symbol = symbol;
		this.validDirections = validDirections;
	}

	protected Piece(int maxDistance, @NotNull String symbol, @NotNull Direction[] validDirections) {
		this(Colour.RESET, maxDistance, symbol, validDirections);
	}

	@NotNull
	public Colour getTeamColour() {
		return teamColour;
	}

	public void setTeamColour(@NotNull Colour teamColour) {
		this.teamColour = teamColour;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	@NotNull
	@Override
	public String getSymbol() {
		return teamColour.getColourCode() + symbol;
	}

	@NotNull
	public Direction[] getValidDirections() {
		return validDirections;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Piece)) return false;

		Piece piece = (Piece) o;

		if (maxDistance != piece.maxDistance) return false;
		if (!symbol.equals(piece.symbol)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(validDirections, piece.validDirections)) return false;
		return teamColour == piece.teamColour;
	}

	@Override
	public int hashCode() {
		int result = maxDistance;
		result = 31 * result + symbol.hashCode();
		result = 31 * result + Arrays.hashCode(validDirections);
		result = 31 * result + teamColour.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Piece{" +
				"maxDistance=" + maxDistance +
				", symbol='" + symbol + '\'' +
				", validDirections=" + Arrays.toString(validDirections) +
				", teamColour=" + teamColour +
				'}';
	}
}