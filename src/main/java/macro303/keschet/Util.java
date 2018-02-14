package macro303.keschet;

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
		int horizontal = end.getLocation().getRow() - start.getLocation().getRow();
		int vertical = end.getLocation().getCol() - start.getLocation().getCol();
		boolean diagonal = Math.abs(horizontal) == Math.abs(vertical);
		if (Tester.getInstance().isTesting()) {
			LOGGER.debug("Horizontal: " + horizontal);
			LOGGER.debug("Vertical: " + vertical);
			LOGGER.debug("Diagonal: " + diagonal);
		}
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

	public static int calculateDistance(@NotNull Square start, @NotNull Square end) {
		return calculateDistance(start, end, calculateDirection(start, end));
	}

	public static int calculateDistance(@NotNull Square start, @NotNull Square end, @NotNull Direction direction) {
		switch (direction) {
			case EAST:
			case WEST:
				return start.getLocation().getRow() - end.getLocation().getRow();
			case NORTH:
			case SOUTH:
				return start.getLocation().getCol() - end.getLocation().getCol();
			case NORTH_EAST:
			case SOUTH_EAST:
			case SOUTH_WEST:
			case NORTH_WEST:
				return start.getLocation().getRow() - end.getLocation().getRow();
			default:
				return 0;
		}
	}

	public static boolean validMovement(@NotNull Piece piece, @NotNull Square start, @NotNull Square end) {
		Direction direction = calculateDirection(start, end);
		int distance = calculateDistance(start, end, direction);
		boolean exists = start.getPiece() != null && start.getPiece().equals(piece);
		boolean selfTaking = end.getPiece() != null && end.getPiece().getTeamColour() == piece.getTeamColour();
		boolean contains = false;
		for (Direction validDirection : piece.getValidDirections())
			if (validDirection == direction)
				contains = true;
		if (Tester.getInstance().isTesting()) {
			LOGGER.debug("Direction: " + direction);
			LOGGER.debug("Distance: " + distance);
			LOGGER.debug("Exists: " + exists);
			LOGGER.debug("Self Taking: " + selfTaking);
			LOGGER.debug("Contains: " + contains);
		}
		return exists && piece.getMaxDistance() >= Math.abs(distance) && Math.abs(distance) > 0 && contains && !selfTaking;
	}
}