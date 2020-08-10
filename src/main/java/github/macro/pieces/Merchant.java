package github.macro.pieces;

import github.macro.Direction;
import github.macro.console.Colour;
import org.jetbrains.annotations.NotNull;

import static github.macro.Direction.*;

/**
 * Created by Macro303 on 2018-02-09.
 */
public class Merchant extends Piece {
	@NotNull
//	private static final String symbol = "\uD83D\uDCB0";
	private static final String symbol = "M";
	private static final int maxDistance = 1;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public Merchant(@NotNull Colour colour) {
		super(colour, maxDistance, symbol, validDirections);
	}

	public Merchant() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Merchant{} " + super.toString();
	}
}
