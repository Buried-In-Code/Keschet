package github.macro;

import github.macro.pieces.Emperor;
import github.macro.pieces.Merchant;
import github.macro.pieces.Scholar;
import github.macro.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2021-Jun-01.
 */
public abstract class Utils {
	private static final Logger LOGGER = LogManager.getLogger();

	private static boolean checkForBlocking(@NotNull Board board, @NotNull Square start, @NotNull Square end) {
		var row = start.getRow();
		var col = start.getCol();
		var direction = calculateDirection(start, end);
		if (direction == null)
			return false;
		var distance = calculateDistance(start, end, direction);
		var blocked = false;
		for (var x = 0; x < distance; x++) {
			switch (direction) {
				case NORTH:
					row--;
					break;
				case NORTH_EAST:
					row--;
					col++;
					break;
				case EAST:
					col++;
					break;
				case SOUTH_EAST:
					row++;
					col++;
					break;
				case SOUTH:
					row++;
					break;
				case SOUTH_WEST:
					row++;
					col--;
					break;
				case WEST:
					col--;
					break;
				case NORTH_WEST:
					row--;
					col--;
					break;
			}
			var temp = board.getSquare(row, col);
			if (temp == null)
				continue;
			if (temp.getPiece() != null && x != distance - 1)
				blocked = true;
		}
		return blocked;
	}

	public static boolean validMovement(@NotNull Board board, @NotNull Square start, @NotNull Square end) {
		var piece = start.getPiece();
		if (piece == null)
			return false;

		var blocked = checkForBlocking(board, start, end);
		var selfTaking = false;
		var scholarBlocked = false;
		if (end.getPiece() != null) {
			selfTaking = piece.getPlayer() == end.getPiece().getPlayer();
			scholarBlocked = checkForScholar(board, end, piece.getPlayer());
		}

		if (piece instanceof Merchant && checkForEmperor(board, end, piece.getPlayer()))
			return !blocked && !selfTaking && !scholarBlocked;
		var direction = calculateDirection(start, end);
		if (direction == null)
			return false;
		if (!piece.getDirections().contains(direction))
			return false;
		var distance = calculateDistance(start, end, direction);
		if (distance == 0)
			return false;
		if (piece.getDistance() < distance && distance > 0)
			return false;
		return !blocked && !selfTaking && !scholarBlocked;
	}

	@Nullable
	public static Direction calculateDirection(@NotNull Square start, @NotNull Square end) {
		var horizontal = end.getCol() - start.getCol();
		var vertical = end.getRow() - start.getRow();
		var diagonal = Math.abs(horizontal) == Math.abs(vertical);
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
		return null;
	}

	public static int calculateDistance(@NotNull Square start, @NotNull Square end, @NotNull Direction direction) {
		switch (direction) {
			case NORTH:
			case SOUTH:
				return Math.abs(start.getRow() - end.getRow());
			case NORTH_EAST:
			case EAST:
			case SOUTH_EAST:
			case NORTH_WEST:
			case WEST:
			case SOUTH_WEST:
				return Math.abs(start.getCol() - end.getCol());
		}
		return 0;
	}

	public static boolean checkForEmperor(@NotNull Board board, @NotNull Square location, @NotNull Player player) {
		var exists = false;
		for (var row = -1; row < 2; row++) {
			for (var col = -1; col < 2; col++) {
				var temp = board.getSquare(location.getRow() + row, location.getCol() + col);
				if (temp == null || temp.getPiece() == null)
					continue;
				if (temp.getPiece() instanceof Emperor && temp.getPiece().getPlayer() == player)
					exists = true;
			}
		}
		return exists;
	}

	public static boolean checkForScholar(@NotNull Board board, @NotNull Square location, @NotNull Player player) {
		var exists = false;
		for (var row = -1; row < 2; row++) {
			for (var col = -1; col < 2; col++) {
				var temp = board.getSquare(location.getRow() + row, location.getCol() + col);
				if (temp == null || temp.getPiece() == null)
					continue;
				if (temp.getPiece() instanceof Scholar && temp.getPiece().getPlayer() != player)
					exists = true;
			}
		}
		return exists;
	}

	public static boolean movablePiece(@NotNull Board board, @NotNull Square location) {
		var movable = false;
		for (var row = 0; row < Board.HEIGHT; row++) {
			for (var col = 0; col < Board.WIDTH; col++) {
				var temp = board.getSquare(row, col);
				if (temp == null)
					continue;
				if (validMovement(board, location, temp))
					movable = true;
			}
		}
		return movable;
	}
}