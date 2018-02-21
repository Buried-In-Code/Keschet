package macro303.keschet.pieces;

import macro303.board_game.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-09.
 */
public class General extends Piece {
	@NotNull
//	private static final String symbol = "\uD83C\uDF96";
	private static final String symbol = "G";
	private static final int maxDistance = 10;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public General(@NotNull Colour teamColour) {
		super(teamColour, maxDistance, symbol, validDirections);
	}

	public General() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "General{} " + super.toString();
	}
}
