package macro303.keschet.pieces;

import macro303.keschet.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-09.
 */
public class Merchant extends Piece {
	@NotNull
	private static final String symbol = "\uD83D\uDCB0";
	private static final int maxDistance = 1;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public Merchant(@NotNull Colour teamColour) {
		super(teamColour, maxDistance, symbol, validDirections);
	}

	public Merchant() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Merchant{}";
	}
}
