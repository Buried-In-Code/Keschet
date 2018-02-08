package macro303.keschet.pieces;

import macro303.keschet.Colour;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Emperor extends Piece {
	private static String symbol = "\u265A";

	public Emperor(@NotNull Colour teamColour) {
		super(teamColour, 4, "E", NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST);
	}

	@Override
	public String toString() {
		return "Emperor{}";
	}
}
