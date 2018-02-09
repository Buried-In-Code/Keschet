package macro303.keschet.pieces;

import macro303.keschet.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-09.
 */
public class Lancer extends Piece {
	@NotNull
	private static final String symbol = "\uD83C\uDFC7";
	private static final int maxDistance = 10;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public Lancer(@NotNull Colour teamColour) {
		super(teamColour, maxDistance, symbol, validDirections);
	}

	public Lancer() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Lancer{}";
	}
}
