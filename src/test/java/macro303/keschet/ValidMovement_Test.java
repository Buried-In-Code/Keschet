package macro303.keschet;

import macro303.board_game.*;
import macro303.keschet.pieces.Emperor;
import macro303.keschet.pieces.Merchant;
import macro303.keschet.pieces.Piece;
import macro303.keschet.pieces.Scholar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

/**
 * Created by Macro303 on 2018-02-14.
 */
public class ValidMovement_Test {
	private static final Logger LOGGER = LogManager.getLogger(ValidMovement_Test.class);
	private static Piece allyEmperor;
	private static Piece enemyEmperor;
	private static Piece enemyScholar;
	private static Piece allyScholar;
	private static Piece allyMerchant;
	private static Board board;
	private static Square start;
	private static Square end;
	private static Square blocking;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		allyEmperor = new Emperor(Colour.BLUE);
		enemyScholar = new Scholar(Colour.RED);
		allyScholar = new Scholar(Colour.BLUE);
		enemyEmperor = new Emperor(Colour.RED);
		allyMerchant = new Merchant(Colour.BLUE);
		board = new Board(10);
		start = board.getSquare(new Coordinates(2, 2));
	}

	@Before
	public void before() {
		start.setItem(allyEmperor);
	}

	@After
	public void after() {
		start.setItem(null);
		if (end != null)
			end.setItem(null);
		end = null;
		if (blocking != null)
			blocking.setItem(null);
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
		end.setItem(allyScholar);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Self Taking Move: " + valid);
		Assert.assertFalse(valid);
		end.setItem(null);
	}

	@Test
	public void test_blockedMove() {
		end = board.getSquare(new Coordinates(5, 5));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 4));
		assert blocking != null;
		blocking.setItem(enemyScholar);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Blocked Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_takingMove() {
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setItem(enemyScholar);
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
		end.setItem(enemyScholar);
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(enemyScholar);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Enemy Scholar Block Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_allyScholarBlockMove() {
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setItem(enemyScholar);
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(allyScholar);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Ally Scholar Block Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_allyMerchantAbilityMove() {
		start.setItem(allyMerchant);
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(allyEmperor);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Ally Merchant Ability Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_enemyMerchantAbilityMove() {
		start.setItem(allyMerchant);
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(enemyEmperor);
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.debug("Enemy Merchant Ability Move: " + valid);
		Assert.assertFalse(valid);
	}
}