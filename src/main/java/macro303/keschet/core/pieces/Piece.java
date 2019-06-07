package macro303.keschet.core.pieces;

import macro303.board_game.Colour;
import macro303.board_game.IPiece;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Piece implements IPiece {
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
	@Override
	public String getSymbol() {
		return colour.getColourCode() + symbol;
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	public void setColour(@NotNull Colour colour) {
		this.colour = colour;
	}

	public String getSymbol2() {
		return symbol;
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