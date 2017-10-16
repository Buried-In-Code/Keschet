package macro303.keschet.pieces;

import macro303.keschet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Merchant extends Piece {
	private static final Logger LOGGER = LogManager.getLogger(Merchant.class);

	public Merchant(Console.Colour teamColour) {
		super(teamColour, 1, "M", new Direction[]{Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST});
	}

	@Override
	public boolean validMovement(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Board board = Keschet.getBoard();
		Direction movingDirection = Board.calculateDirection(start, end);
		boolean validDirection = Arrays.stream(validDirections).anyMatch(direction -> direction == movingDirection);
		boolean valid = (board.getAllSurroundingPieces(end).stream().anyMatch(piece -> piece instanceof Emperor && piece.getTeamColour() == getTeamColour()) && validDirection) || super.validMovement(start, end);
		LOGGER.trace("boolean validMovement(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + valid);
		return valid;
	}

	@Override
	public String toString() {
		return "Merchant{} " + super.toString();
	}
}