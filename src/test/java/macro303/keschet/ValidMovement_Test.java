package macro303.keschet;

import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import macro303.keschet.pieces.Emperor;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidMovement_Test {
	private static final Logger LOGGER = LogManager.getLogger(ValidMovement_Test.class);
	private static Piece piece;
	private static Board board;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		board = new Board();
		piece = new Emperor();
		Square square = board.getSquare(new Coordinates(2, 2));
		assert square != null;
		square.setPiece(piece);
	}

	@Test
	public void test_northMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("North Move: " + valid);
		assert valid;
	}

	@Test
	public void test_northEastMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("North-East Move: " + valid);
		assert valid;
	}

	@Test
	public void test_eastMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("East Move: " + valid);
		assert valid;
	}

	@Test
	public void test_southEastMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("South-East Move: " + valid);
		assert valid;
	}

	@Test
	public void test_southMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("South Move: " + valid);
		assert valid;
	}

	@Test
	public void test_southWestMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("South-West Move: " + valid);
		assert valid;
	}

	@Test
	public void test_westMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("West Move: " + valid);
		assert valid;
	}

	@Test
	public void test_northWestMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("North-West Move: " + valid);
		assert valid;
	}

	@Test
	public void test_invalidMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(4, 3));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("Invalid Move: " + valid);
		assert !valid;
	}

	@Test
	public void test_noMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(2, 2));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("No Move: " + valid);
		assert !valid;
	}

	@Test
	public void test_tooFarMove() {
		Square start = board.getSquare(new Coordinates(0, 0));
		assert start != null;
		Square end = board.getSquare(new Coordinates(9, 9));
		assert end != null;
		start.setPiece(piece);
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("Too Far Move: " + valid);
		assert !valid;
	}

	@Test
	public void test_boundaryInMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(6, 6));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("Boundary In Move: " + valid);
		assert valid;
	}

	@Test
	public void test_boundaryOutMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(7, 7));
		assert end != null;
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("Boundary Out Move: " + valid);
		assert !valid;
	}

	@Test
	public void test_selfTakingMove() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		end.setPiece(piece);
		boolean valid = Util.validMovement(piece, start, end);
		LOGGER.debug("Self Taking Move: " + valid);
		assert !valid;
		end.setPiece(null);
	}
}