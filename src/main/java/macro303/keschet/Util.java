package macro303.keschet;

import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import static macro303.keschet.Direction.*;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Util {
	public static final int SIZE = 10;
	private static final Logger LOGGER = LogManager.getLogger(Util.class);

	@NotNull
	public static Direction calculateDirection(@NotNull Square start, @NotNull Square end) {
		LOGGER.debug("=====Calculate Direction=====");
		int horizontal = end.getLocation().getRow() - start.getLocation().getRow();
		int vertical = end.getLocation().getCol() - start.getLocation().getCol();
		boolean diagonal = Math.abs(horizontal) == Math.abs(vertical);
		LOGGER.debug("Horizontal: " + horizontal);
		LOGGER.debug("Vertical: " + vertical);
		LOGGER.debug("Diagonal: " + diagonal);
		if (horizontal == 0 && vertical < 0 && !diagonal)
			return NORTH;
		if (horizontal > 0 && vertical < 0 && diagonal)
			return NORTH_EAST;
		if (horizontal > 0 && vertical == 0 && !diagonal)
			return EAST;
		if (horizontal > 0 && vertical > 0 && diagonal)
			return SOUTH_EAST;
		if (horizontal == 0 && vertical > 0 && !diagonal)
			return SOUTH;
		if (horizontal < 0 && vertical > 0 && diagonal)
			return SOUTH_WEST;
		if (horizontal < 0 && vertical == 0 && !diagonal)
			return WEST;
		if (horizontal < 0 && vertical < 0 && diagonal)
			return NORTH_WEST;
		return INVALID;
	}

	private static boolean validDirection(@NotNull Piece piece, @NotNull Direction direction) {
		LOGGER.debug("=====Valid Direction=====");
		boolean contains = false;
		for (Direction validDirection : piece.getValidDirections())
			if (validDirection == direction)
				contains = true;
		return contains;
	}

	public static int calculateDistance(@NotNull Square start, @NotNull Square end, @NotNull Direction direction) {
		LOGGER.debug("=====Calculate Distance=====");
		switch (direction) {
			case EAST:
			case WEST:
				return Math.abs(start.getLocation().getRow() - end.getLocation().getRow());
			case NORTH:
			case SOUTH:
				return Math.abs(start.getLocation().getCol() - end.getLocation().getCol());
			case NORTH_EAST:
			case SOUTH_EAST:
			case SOUTH_WEST:
			case NORTH_WEST:
				return Math.abs(start.getLocation().getRow() - end.getLocation().getRow());
			default:
				return 0;
		}
	}

	private static boolean validDistance(@NotNull Piece piece, int distance) {
		LOGGER.debug("=====Valid Distance=====");
		return piece.getMaxDistance() >= distance && distance > 0;
	}

	public static boolean validMovement(@NotNull Board board, @NotNull Square start, @NotNull Square end) {
		LOGGER.debug("=====Valid Movement=====");
		Piece piece = start.getPiece();
		Direction direction = calculateDirection(start, end);
		int distance = calculateDistance(start, end, direction);
		boolean directionValid = piece != null && validDirection(piece, direction);
		boolean distanceValid = piece != null && validDistance(piece, distance);
		boolean selfTaking = piece != null && (end.getPiece() != null && end.getPiece().getTeamColour() == piece.getTeamColour());
		boolean blocked = piece != null && checkForBlocking(board, start, direction, distance);
		LOGGER.debug("Direction: " + directionValid);
		LOGGER.debug("Distance: " + distanceValid);
		LOGGER.debug("Self Taking: " + selfTaking);
		LOGGER.debug("Blocked: " + blocked);
		return directionValid && distanceValid && !selfTaking && !blocked;
	}

	private static boolean checkForBlocking(@NotNull Board board, @NotNull Square start, @NotNull Direction direction, int distance) {
		LOGGER.debug("=====Check For Blocking=====");
		int tempRow = start.getLocation().getRow();
		int tempCol = start.getLocation().getCol();
		boolean blocked = false;
		for (int i = 0; i < distance; i++) {
			if (direction == NORTH || direction == NORTH_EAST || direction == NORTH_WEST) {
				tempCol--;
			} else if (direction == SOUTH || direction == SOUTH_EAST || direction == SOUTH_WEST) {
				tempCol++;
			}
			if (direction == EAST || direction == NORTH_EAST || direction == SOUTH_EAST) {
				tempRow++;
			} else if (direction == WEST || direction == NORTH_WEST || direction == SOUTH_WEST) {
				tempRow--;
			}
			if (direction == INVALID)
				blocked = true;
			Square tempSquare = board.getSquare(new Coordinates(tempRow, tempCol));
			LOGGER.debug("Square: " + tempSquare);
			if (tempSquare != null && tempSquare.getPiece() != null && i != distance - 1)
				blocked = true;
		}
		return blocked;
	}
}