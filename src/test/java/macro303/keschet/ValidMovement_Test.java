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
	private static KeschetDisplay display;
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
		display = new KeschetDisplay(board);
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
		display.showMessage("=====North Move=====");
		end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("North Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_northEastMove() {
		display.showMessage("=====North-East Move=====");
		end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("North-East Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_eastMove() {
		display.showMessage("=====East Move=====");
		end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("East Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_southEastMove() {
		display.showMessage("=====South-East Move=====");
		end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("South-East Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_southMove() {
		display.showMessage("=====South Move=====");
		end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("South Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_southWestMove() {
		display.showMessage("=====South-West Move=====");
		end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("South-West Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_westMove() {
		display.showMessage("=====West Move=====");
		end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("West Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_northWestMove() {
		display.showMessage("=====North-West Move=====");
		end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("North-West Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_invalidMove() {
		display.showMessage("=====Invalid Move=====");
		end = board.getSquare(new Coordinates(4, 3));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Invalid Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_noMove() {
		display.showMessage("=====No Move=====");
		boolean valid = Util.validMovement(board, start, start);
		display.showMessage("No Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_boundaryInMove() {
		display.showMessage("=====Boundry In Move=====");
		end = board.getSquare(new Coordinates(6, 6));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Boundary In Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_boundaryOutMove() {
		display.showMessage("=====Boundry Out Move=====");
		end = board.getSquare(new Coordinates(7, 7));
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Boundary Out Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_selfTakingMove() {
		display.showMessage("=====Self-Taking Move=====");
		end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		end.setItem(allyScholar);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Self-Taking Move: " + valid);
		Assert.assertFalse(valid);
		end.setItem(null);
	}

	@Test
	public void test_blockedMove() {
		display.showMessage("=====Blocked Move=====");
		end = board.getSquare(new Coordinates(5, 5));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 4));
		assert blocking != null;
		blocking.setItem(enemyScholar);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Blocked Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_takingMove() {
		display.showMessage("=====Taking Move=====");
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setItem(enemyScholar);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Taking Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_noPieceMove() {
		display.showMessage("=====No Piece Move=====");
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		boolean valid = Util.validMovement(board, end, start);
		display.showMessage("No Piece Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_enemyScholarBlockMove() {
		display.showMessage("=====Enemy Scholar Block Move=====");
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setItem(enemyScholar);
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(enemyScholar);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Enemy Scholar Block Move: " + valid);
		Assert.assertFalse(valid);
	}

	@Test
	public void test_allyScholarBlockMove() {
		display.showMessage("=====Ally Scholar Block Move=====");
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		end.setItem(enemyScholar);
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(allyScholar);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Ally Scholar Block Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_allyMerchantMove() {
		display.showMessage("=====Ally Merchant Move=====");
		start.setItem(allyMerchant);
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(allyEmperor);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Ally Merchant Move: " + valid);
		Assert.assertTrue(valid);
	}

	@Test
	public void test_enemyMerchantMove() {
		display.showMessage("=====Enemy Merchant Move=====");
		start.setItem(allyMerchant);
		end = board.getSquare(new Coordinates(4, 4));
		assert end != null;
		blocking = board.getSquare(new Coordinates(4, 5));
		assert blocking != null;
		blocking.setItem(enemyEmperor);
		boolean valid = Util.validMovement(board, start, end);
		display.showMessage("Enemy Merchant Move: " + valid);
		Assert.assertFalse(valid);
	}
}