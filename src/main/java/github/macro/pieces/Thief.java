package github.macro.pieces;

import github.macro.Direction;
import github.macro.console.Colour;
import org.jetbrains.annotations.NotNull;

import static github.macro.Direction.*;

/**
 * Created by Macro303 on 2018-02-09.
 */
public class Thief extends Piece {
	@NotNull
//	private static final String symbol = "\uD83D\uDDE1";
	private static final String symbol = "T";
	private static final int maxDistance = 1;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public Thief(@NotNull Colour colour) {
		super(colour, maxDistance, symbol, validDirections);
	}

	public Thief() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Thief{} " + super.toString();
	}
}
