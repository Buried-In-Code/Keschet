package github.macro;

import github.macro.console.Colour;
import github.macro.pieces.Emperor;
import github.macro.pieces.Merchant;
import github.macro.pieces.Piece;
import github.macro.pieces.Scholar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Util {
	@NotNull
	public static final Colour player1Colour = Colour.RED;
	@NotNull
	public static final Colour player2Colour = Colour.GREEN;
	@NotNull
	private static final Logger LOGGER = LogManager.getLogger(Util.class);

	public static boolean validMovement(@NotNull GameBoard board, @NotNull Square start, @NotNull Square end) {
		Piece piece = start.getPiece();
		Direction direction = calculateDirection(start, end);
		int distance = calculateDistance(start, end, direction);
		boolean directionValid = piece != null && validDirection(piece, direction);
		if (piece instanceof Merchant) {
			boolean blocked = checkForBlocking(board, start, direction, distance);
			boolean selfTaking = false;
			boolean scholarBlocked = false;
			boolean merchantAbility = checkForEmperor(board, end, piece.getColour());
			if (!merchantAbility)
				merchantAbility = validDistance(piece, distance);
			if (end.getPiece() != null) {
				selfTaking = end.getPiece().getColour() == piece.getColour();
				scholarBlocked = checkForScholar(board, end);
			}
			return directionValid && merchantAbility && !blocked && !selfTaking && !scholarBlocked;
		} else if (piece != null) {
			boolean distanceValid = validDistance(piece, distance);
			boolean blocked = checkForBlocking(board, start, direction, distance);
			boolean selfTaking = false;
			boolean scholarBlocked = false;
			if (end.getPiece() != null) {
				selfTaking = end.getPiece().getColour() == piece.getColour();
				scholarBlocked = checkForScholar(board, end);
			}
			return directionValid && distanceValid && !blocked && !selfTaking && !scholarBlocked;
		}
		return false;
	}

	@NotNull
	public static Direction calculateDirection(@NotNull Square start, @NotNull Square end) {
		int horizontal = end.getCoord().getCol() - start.getCoord().getCol();
		int vertical = end.getCoord().getRow() - start.getCoord().getRow();
		boolean diagonal = Math.abs(horizontal) == Math.abs(vertical);
		if (horizontal == 0 && vertical < 0 && !diagonal)
			return Direction.NORTH;
		if (horizontal > 0 && vertical < 0 && diagonal)
			return Direction.NORTH_EAST;
		if (horizontal > 0 && vertical == 0 && !diagonal)
			return Direction.EAST;
		if (horizontal > 0 && vertical > 0 && diagonal)
			return Direction.SOUTH_EAST;
		if (horizontal == 0 && vertical > 0 && !diagonal)
			return Direction.SOUTH;
		if (horizontal < 0 && vertical > 0 && diagonal)
			return Direction.SOUTH_WEST;
		if (horizontal < 0 && vertical == 0 && !diagonal)
			return Direction.WEST;
		if (horizontal < 0 && vertical < 0 && diagonal)
			return Direction.NORTH_WEST;
		return Direction.INVALID;
	}

	public static int calculateDistance(@NotNull Square start, @NotNull Square end, @NotNull Direction direction) {
		switch (direction) {
			case EAST:
			case WEST:
			case NORTH_EAST:
			case SOUTH_EAST:
			case SOUTH_WEST:
			case NORTH_WEST:
				return Math.abs(start.getCoord().getCol() - end.getCoord().getCol());
			case NORTH:
			case SOUTH:
				return Math.abs(start.getCoord().getRow() - end.getCoord().getRow());
			default:
				return 0;
		}
	}

	private static boolean validDirection(@NotNull Piece piece, @NotNull Direction direction) {
		boolean contains = false;
		for (Direction validDirection : piece.getValidDirections())
			if (validDirection == direction) {
				contains = true;
				break;
			}
		return contains;
	}

	private static boolean checkForBlocking(@NotNull GameBoard board, @NotNull Square start, @NotNull Direction direction, int distance) {
		int tempRow = start.getCoord().getRow();
		int tempCol = start.getCoord().getCol();
		boolean blocked = false;
		for (int i = 0; i < distance; i++) {
			if (direction == Direction.NORTH || direction == Direction.NORTH_EAST || direction == Direction.NORTH_WEST) {
				tempRow--;
			} else if (direction == Direction.SOUTH || direction == Direction.SOUTH_EAST || direction == Direction.SOUTH_WEST) {
				tempRow++;
			}
			if (direction == Direction.EAST || direction == Direction.NORTH_EAST || direction == Direction.SOUTH_EAST) {
				tempCol++;
			} else if (direction == Direction.WEST || direction == Direction.NORTH_WEST || direction == Direction.SOUTH_WEST) {
				tempCol--;
			}
			if (direction == Direction.INVALID)
				blocked = true;
			Square tempSquare = board.getSquare(tempRow, tempCol);
			if (tempSquare != null && tempSquare.getPiece() != null && i != distance - 1)
				blocked = true;
		}
		return blocked;
	}

	public static boolean checkForEmperor(@NotNull GameBoard board, @NotNull Square location, @NotNull Colour teamColour) {
		boolean exists = false;
		Square tempSquare = board.getSquare(location.getCoord().getCol() - 1, location.getCoord().getRow());
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() - 1, location.getCoord().getRow() + 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol(), location.getCoord().getRow() + 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() + 1, location.getCoord().getRow() + 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() + 1, location.getCoord().getRow());
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() + 1, location.getCoord().getRow() - 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol(), location.getCoord().getRow() - 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() - 1, location.getCoord().getRow() - 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Emperor) && tempSquare.getPiece().getColour() == teamColour)
			exists = true;
		return exists;
	}

	private static boolean validDistance(@NotNull Piece piece, int distance) {
		return piece.getMaxDistance() >= distance && distance > 0;
	}

	public static boolean checkForScholar(@NotNull GameBoard board, @NotNull Square location) {
		boolean exists = false;
		Piece piece = location.getPiece();
		assert piece != null;
		Square tempSquare = board.getSquare(location.getCoord().getCol() - 1, location.getCoord().getRow());
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() - 1, location.getCoord().getRow() + 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol(), location.getCoord().getRow() + 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() + 1, location.getCoord().getRow() + 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() + 1, location.getCoord().getRow());
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() + 1, location.getCoord().getRow() - 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol(), location.getCoord().getRow() - 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		tempSquare = board.getSquare(location.getCoord().getCol() - 1, location.getCoord().getRow() - 1);
		if (tempSquare != null && tempSquare.getPiece() != null && (tempSquare.getPiece() instanceof Scholar) && tempSquare.getPiece().getColour() == piece.getColour())
			exists = true;
		return exists;
	}
}