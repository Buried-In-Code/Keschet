package macro303.keschet.board;

import macro303.keschet.Coordinates;
import macro303.keschet.Tester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Board_Test {
	private static final Logger LOGGER = LogManager.getLogger(Board_Test.class);
	private Board board;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
	}

	@Before
	public void before() {
		board = new Board();
	}

	@After
	public void after() {
		board = null;
	}

	@Test
	public void test_invalidBothMinimumGetSquare() {
		Coordinates location = new Coordinates(-1, -1);
		Square square = board.getSquare(location);
		LOGGER.debug("Invalid Both Minimum: " + square);
		assert square == null;
	}

	@Test
	public void test_edgeMinimumGetSquare() {
		Coordinates location = new Coordinates(0, 0);
		Square square = board.getSquare(location);
		LOGGER.debug("Edge Minimum: " + square);
		assert square != null;
	}

	@Test
	public void test_invalidRowMinimumGetSquare() {
		Coordinates location = new Coordinates(-1, 0);
		Square square = board.getSquare(location);
		LOGGER.debug("Invalid Row Minimum: " + square);
		assert square == null;
	}

	@Test
	public void test_invalidColMinimumGetSquare() {
		Coordinates location = new Coordinates(0, -1);
		Square square = board.getSquare(location);
		LOGGER.debug("Invalid Col Minimum: " + square);
		assert square == null;
	}

	@Test
	public void test_invalidBothMaximumGetSquare() {
		Coordinates location = new Coordinates(10, 10);
		Square square = board.getSquare(location);
		LOGGER.debug("Invalid Both Maximum: " + square);
		assert square == null;
	}

	@Test
	public void test_edgeMaximumGetSquare() {
		Coordinates location = new Coordinates(9, 9);
		Square square = board.getSquare(location);
		LOGGER.debug("Edge Maximum: " + square);
		assert square != null;
	}

	@Test
	public void test_invalidRowMaximumGetSquare() {
		Coordinates location = new Coordinates(10, 9);
		Square square = board.getSquare(location);
		LOGGER.debug("Invalid Row Maximum: " + square);
		assert square == null;
	}

	@Test
	public void test_invalidColMaximumGetSquare() {
		Coordinates location = new Coordinates(9, 10);
		Square square = board.getSquare(location);
		LOGGER.debug("Invalid Col Maximum: " + square);
		assert square == null;
	}
}
