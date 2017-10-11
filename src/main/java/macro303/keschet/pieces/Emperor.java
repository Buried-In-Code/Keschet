package macro303.keschet.pieces;

import macro303.keschet.ConsoleColour;
import macro303.keschet.Direction;

public class Emperor extends Piece {

	public Emperor(ConsoleColour teamColour) {
		super(teamColour, 4, "E", new Direction[]{Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST});
	}

	@Override
	public String toString() {
		return "Emperor{} " + super.toString();
	}
}