package github.macro.pieces;

import github.macro.Direction;
import github.macro.console.Colour;
import org.jetbrains.annotations.NotNull;

import static github.macro.Direction.*;

/**
 * Created by Macro303 on 2018-02-12.
 */
public class Archer extends Piece {
	@NotNull
//	private static final String symbol = "\uD83C\uDFF9";
	private static final String symbol = "A";
	private static final int maxDistance = 6;
	@NotNull
	private static final Direction[] validDirections = new Direction[]{NORTH, EAST, SOUTH, WEST};

	public Archer(@NotNull Colour colour) {
		super(colour, maxDistance, symbol, validDirections);
	}

	public Archer() {
		super(maxDistance, symbol, validDirections);
	}

	@Override
	public String toString() {
		return "Archer{} " + super.toString();
	}
}