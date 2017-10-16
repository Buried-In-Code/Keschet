package macro303.keschet.pieces;

import macro303.keschet.Console;
import macro303.keschet.Direction;

public class General extends Piece {

	public General(Console.Colour teamColour) {
		super(teamColour, 10, "G", new Direction[]{Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST});
	}

	@Override
	public String toString() {
		return "General{} " + super.toString();
	}
}