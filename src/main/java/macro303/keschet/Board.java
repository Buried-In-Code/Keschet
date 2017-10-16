package macro303.keschet;

import macro303.keschet.pieces.Emperor;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board {
	private static final Logger LOGGER = LogManager.getLogger(Board.class);
	private static final int SIZE = 10;

	private Square[][] board = new Square[SIZE][SIZE];

	Board() {
		Arrays.stream(board).forEach(column -> Arrays.setAll(column, row -> new Square()));
	}

	public static int calculateDistance(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Direction direction = calculateDirection(start, end);
		int distance = 0;
		if (direction == Direction.NORTH || direction == Direction.SOUTH)
			distance = start.getY() - end.getY();
		else if (direction == Direction.NORTH_EAST || direction == Direction.EAST || direction == Direction.SOUTH_EAST || direction == Direction.SOUTH_WEST || direction == Direction.WEST || direction == Direction.NORTH_WEST)
			distance = start.getX() - end.getX();
		LOGGER.trace("int calculateDistance(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + distance);
		return distance;
	}

	public static Direction calculateDirection(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Direction direction = Direction.INVALID;
		if (Objects.equals(end.getX(), start.getX()) && end.getY() - start.getY() < 0)
			direction = Direction.NORTH;
		else if (end.getX() - start.getX() > 0 && end.getY() - start.getY() < 0 && Math.abs((end.getX() - start.getX())) - Math.abs((end.getY() - start.getY())) == 0)
			direction = Direction.NORTH_EAST;
		else if (end.getX() - start.getX() > 0 && Objects.equals(end.getY(), start.getY()))
			direction = Direction.EAST;
		else if (end.getX() - start.getX() > 0 && end.getY() - start.getY() > 0 && Math.abs((end.getX() - start.getX())) - Math.abs((end.getY() - start.getY())) == 0)
			direction = Direction.SOUTH_EAST;
		else if (Objects.equals(end.getX(), start.getX()) && end.getY() - start.getY() > 0)
			direction = Direction.SOUTH;
		else if (end.getX() - start.getX() < 0 && end.getY() - start.getY() > 0 && Math.abs((end.getX() - start.getX())) - Math.abs((end.getY() - start.getY())) == 0)
			direction = Direction.SOUTH_WEST;
		else if (end.getX() - start.getX() < 0 && Objects.equals(end.getY(), start.getY()))
			direction = Direction.WEST;
		else if (end.getX() - start.getX() < 0 && end.getY() - start.getY() < 0 && Math.abs((end.getX() - start.getX())) - Math.abs((end.getY() - start.getY())) == 0)
			direction = Direction.NORTH_WEST;
		LOGGER.trace("Direction calculateDirection(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + direction);
		return direction;
	}

	public void draw() {
		for (int column = 0; column <= SIZE; column++) {
			for (int row = 0; row <= SIZE; row++)
				if (column == 0)
					System.out.print((row == 0 ? "" : " ") + row + (row == SIZE ? "" : " "));
				else if (row == 0)
					System.out.print(column + (column == SIZE ? "" : " "));
				else
					Console.showSquare(getSquare(new Pair<>(row, column)).getPiece());
			System.out.println();
		}
	}

	public Square getSquare(Pair<Integer, Integer> square) {
		return square.getX() - 1 < 0 || square.getX() > SIZE || square.getY() - 1 < 0 || square.getY() > SIZE ? null : board[square.getY() - 1][square.getX() - 1];
	}

	public int countPieces(Team team) {
		int counter = 0;
		for (int column = 1; column <= SIZE; column++)
			for (int row = 1; row <= SIZE; row++) {
				Square square = getSquare(new Pair<>(row, column));
				if (square.getPiece() != null && square.getPiece().getTeamColour() == team.getColour() && !(square.getPiece() instanceof Emperor))
					counter++;
			}
		LOGGER.trace("int countPieces(Team) = " + counter);
		return counter;
	}

	public boolean pieceStillOnBoard(Class clazz, Console.Colour teamColour) {
		boolean stillAvailable = false;
		for (int column = 1; column <= SIZE; column++)
			for (int row = 1; row <= SIZE; row++) {
				Square square = getSquare(new Pair<>(row, column));
				if (square.getPiece() != null && square.getPiece().getTeamColour() == teamColour && square.getPiece().getClass() == clazz)
					stillAvailable = true;
			}
		LOGGER.trace("boolean pieceStillOnBoard(Class, Colour) = " + stillAvailable);
		return stillAvailable;
	}

	public ArrayList<Piece> getAllSurroundingPieces(Pair<Integer, Integer> squareLocation) {
		ArrayList<Piece> pieces = new ArrayList<>();
		Square square = getSquare(new Pair<>(squareLocation.getX(), squareLocation.getY() + 1));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX() + 1, squareLocation.getY() + 1));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX() + 1, squareLocation.getY()));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX() + 1, squareLocation.getY() - 1));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX(), squareLocation.getY() - 1));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX() - 1, squareLocation.getY() - 1));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX() - 1, squareLocation.getY()));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		square = getSquare(new Pair<>(squareLocation.getX() - 1, squareLocation.getY() + 1));
		if (square != null && square.getPiece() != null)
			pieces.add(square.getPiece());
		LOGGER.trace("ArrayList<Piece> getAllSurroundingPieces(Pair<Integer, Integer>) = " + pieces);
		return pieces;
	}
}
