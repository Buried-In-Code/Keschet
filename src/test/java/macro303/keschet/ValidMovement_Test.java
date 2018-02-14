package macro303.keschet;

import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import macro303.keschet.pieces.Emperor;
import macro303.keschet.pieces.Piece;
import macro303.keschet.pieces.Scholar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidMovement_Test {
	private static final Logger LOGGER = LogManager.getLogger(ValidMovement_Test.class);
	private static Piece enemyPiece;
	private static Piece allyPiece;
	private static Board board;
	private static Square start;
	private static Square end;
	private static Square blocking;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		Piece piece = new Emperor(Colour.BLUE);
		enemyPiece = new Scholar(Colour.RED);
		allyPiece = new Scholar(Colour.BLUE);
		board = new Board();
		start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		start.setPiece(piece);
	}

	@After
	public void after() {
		if (end != null)
			end.setPiece(null);
		end = null;
		if (blocking != null)
			blocking.setPiece(null);
		blocking = null;
	}

	@Test
	public void test_northMove() {
		end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("North Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_northEastMove() {
		end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("North-East Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_eastMove() {
		end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("East Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_southEastMove() {
		end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("South-East Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_southMove() {
		end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("South Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_southWestMove() {
		end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("South-West Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_westMove() {
		end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("West Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_northWestMove() {
		end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("North-West Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_invalidMove() {
		end = board.getSquare(new Coordinates(4, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Invalid Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_noMove() {
		boolean valid = Util.validMovement(board, start, start);
		LOGGER.debug("No Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_boundaryInMove() {
		end = board.getSquare(new Coordinates(6, 6));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Boundary In Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_boundaryOutMove() {
		end = board.getSquare(new Coordinates(7, 7));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Boundary Out Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_selfTakingMove() {
		end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		end.setPiece(allyPiece);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Self Taking Move: " + valid);
		Assert.assertFalse(valid);
		end.setPiece(null);
	}

	@Test
	public void test_blockedMove() {
		end = board.getSquare(new Coordinates(5, 5));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 4));
		assert blocking != null;
		blocking.setPiece(enemyPiece);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Blocked Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_takingMove() {
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setPiece(enemyPiece);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Taking Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_noPieceMove() {
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		boolean valid = Util.validMovement(board, end, start);
		LOGGER.debug("No Piece Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_enemyScholarBlockMove() {
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setPiece(enemyPiece);
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setPiece(enemyPiece);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Enemy Scholar Block Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_allyScholarBlockMove() {
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setPiece(enemyPiece);
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setPiece(allyPiece);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Ally Scholar Block Move: " + valid);
		Assert.assertTrue(valid);
	}
}