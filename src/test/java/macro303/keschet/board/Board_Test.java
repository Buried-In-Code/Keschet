package macro303.keschet.board;

import macro303.keschet.Coordinates;
import macro303.keschet.Tester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Board_Test {
	private static final Logger LOGGER = LogManager.getLogger(Board_Test.class);
	private static Board board;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		board = new Board();
	}

	@Test
	public void test_invalidBothMinimumGetSquare() {
		Square square = board.getSquare(new Coordinates(-1, -1));
		LOGGER.debug("Invalid Both Minimum: " + square);
		Assert.assertNull(square);
	}

	@Test
	public void test_edgeMinimumGetSquare() {
		Square square = board.getSquare(new Coordinates(0, 0));
		LOGGER.debug("Edge Minimum: " + square);
		Assert.assertNotNull(square);
	}

	@Test
	public void test_invalidRowMinimumGetSquare() {
		Square square = board.getSquare(new Coordinates(-1, 0));
		LOGGER.debug("Invalid Row Minimum: " + square);
		Assert.assertNull(square);
	}

	@Test
	public void test_invalidColMinimumGetSquare() {
		Square square = board.getSquare(new Coordinates(0, -1));
		LOGGER.debug("Invalid Col Minimum: " + square);
		Assert.assertNull(square);
	}

	@Test
	public void test_invalidBothMaximumGetSquare() {
		Square square = board.getSquare(new Coordinates(10, 10));
		LOGGER.debug("Invalid Both Maximum: " + square);
		Assert.assertNull(square);
	}

	@Test
	public void test_edgeMaximumGetSquare() {
		Square square = board.getSquare(new Coordinates(9, 9));
		LOGGER.debug("Edge Maximum: " + square);
		Assert.assertNotNull(square);
	}

	@Test
	public void test_invalidRowMaximumGetSquare() {
		Square square = board.getSquare(new Coordinates(10, 9));
		LOGGER.debug("Invalid Row Maximum: " + square);
		Assert.assertNull(square);
	}

	@Test
	public void test_invalidColMaximumGetSquare() {
		Square square = board.getSquare(new Coordinates(9, 10));
		LOGGER.debug("Invalid Col Maximum: " + square);
		Assert.assertNull(square);
	}
}
