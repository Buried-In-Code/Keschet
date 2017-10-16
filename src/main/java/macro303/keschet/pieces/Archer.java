package macro303.keschet.pieces;

import macro303.keschet.Console;
import macro303.keschet.Direction;

public class Archer extends Piece {

	public Archer(Console.Colour teamColour) {
		super(teamColour, 6, "A", new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST});
	}

	@Override
	public String toString() {
		return "Archer{} " + super.toString();
	}
}