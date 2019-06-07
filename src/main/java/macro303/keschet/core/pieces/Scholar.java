package macro303.keschet.core.pieces;

import macro303.board_game.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-09.
 */
public class Scholar extends Piece {
	@NotNull
//	private static final String symbol = "\uD83D\uDCDA";
	private static final String symbol = "C";
	private static final int maxDistance = 2;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public Scholar(@NotNull Colour colour) {
		super(colour, maxDistance, symbol, validDirections);
	}

	public Scholar() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Scholar{} " + super.toString();
	}
}
