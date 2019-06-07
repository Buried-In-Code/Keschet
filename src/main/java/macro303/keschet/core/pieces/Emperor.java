package macro303.keschet.core.pieces;

import macro303.board_game.Colour;
import macro303.keschet.Direction;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Emperor extends Piece {
	@NotNull
//	private static final String symbol = "\u265A";
	private static final String symbol = "E";
	private static final int maxDistance = 4;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	public Emperor(@NotNull Colour colour) {
		super(colour, maxDistance, symbol, validDirections);
	}

	public Emperor() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Emperor{} " + super.toString();
	}
}
