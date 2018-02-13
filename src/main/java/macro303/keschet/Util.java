package macro303.keschet;

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
	public static Direction calculateDirection(@NotNull Coordinates start, @NotNull Coordinates end) {
		int horizontal = end.getRow() - start.getRow();
		int vertical = end.getCol() - start.getCol();
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

	public static int calculateDistance(@NotNull Coordinates start, @NotNull Coordinates end) {
		return calculateDistance(start, end, calculateDirection(start, end));
	}

	public static int calculateDistance(@NotNull Coordinates start, @NotNull Coordinates end, @NotNull Direction direction) {
		switch (direction) {
			case EAST:
			case WEST:
				return start.getRow() - end.getRow();
			case NORTH:
			case SOUTH:
				return start.getCol() - end.getCol();
			case NORTH_EAST:
			case SOUTH_EAST:
			case SOUTH_WEST:
			case NORTH_WEST:
				return start.getRow() - end.getRow();
			default:
				return 0;
		}
	}

	public static boolean validMovement(@NotNull Piece piece, @NotNull Direction direction, int distance) {
		boolean contains = false;
		for (Direction validDirection : piece.getValidDirections())
			if (validDirection == direction)
				contains = true;
		return piece.getMaxDistance() >= distance && contains;
	}
}