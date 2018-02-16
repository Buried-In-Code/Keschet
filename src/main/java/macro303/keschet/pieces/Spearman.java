package macro303.keschet.pieces;

import macro303.keschet.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-12.
 */
public class Spearman extends Piece {
	@NotNull
//	private static final String symbol = "\u2694";
	private static final String symbol = "P";
	private static final int maxDistance = 2;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, EAST, SOUTH, WEST};

	public Spearman(@NotNull Colour teamColour) {
		super(teamColour, maxDistance, symbol, validDirections);
	}

	public Spearman() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Spearman{}";
	}
}
