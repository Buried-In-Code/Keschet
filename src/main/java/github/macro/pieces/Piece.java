package github.macro.pieces;

import github.macro.Direction;
import github.macro.console.Colour;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Piece {
	private final int maxDistance;
	@NotNull
	private final String symbol;
	@NotNull
	private final Direction[] validDirections;
	@NotNull
	private Colour colour;

	protected Piece(int maxDistance, @NotNull String symbol, @NotNull Direction[] validDirections) {
		this(Colour.RESET, maxDistance, symbol, validDirections);
	}

	protected Piece(@NotNull Colour colour, int maxDistance, @NotNull String symbol, @NotNull Direction[] validDirections) {
		this.colour = colour;
		this.maxDistance = maxDistance;
		this.symbol = symbol;
		this.validDirections = validDirections;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	@NotNull
	public String getColouredSymbol() {
		return colour.getANSICode() + symbol;
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	public void setColour(@NotNull Colour colour) {
		this.colour = colour;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return getClass().getSimpleName();
	}

	@NotNull
	public Direction[] getValidDirections() {
		return validDirections;
	}

	@Override
	public int hashCode() {
		int result = maxDistance;
		result = 31 * result + colour.hashCode();
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
		if (colour != piece.colour) return false;
		if (!symbol.equals(piece.symbol)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(validDirections, piece.validDirections);
	}

	@Override
	public String toString() {
		return "Piece{" +
				"maxDistance=" + maxDistance +
				", colour=" + colour +
				", symbol='" + symbol + '\'' +
				", validDirections=" + Arrays.toString(validDirections) +
				'}';
	}
}