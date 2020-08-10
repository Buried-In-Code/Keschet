package github.macro;

import github.macro.console.Colour;
import github.macro.pieces.General;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Direction_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static GameBoard board;
	private static Square start;
	private static Square end;

	@BeforeAll
	static void beforeAll() {
		board = new GameBoard();
		start = board.getSquare(2, 2);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new General(Colour.BLUE));
		if (end != null)
			end.setPiece(null);
	}

	@Test
	void test_northDirection() {
		end = board.getSquare(1, 2);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("North ==> " + direction);
		assertEquals(Direction.NORTH, direction);
	}

	@Test
	void test_northEastDirection() {
		end = board.getSquare(1, 3);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("North-East ==> " + direction);
		assertEquals(Direction.NORTH_EAST, direction);
	}

	@Test
	void test_eastDirection() {
		end = board.getSquare(2, 3);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("East ==> " + direction);
		assertEquals(Direction.EAST, direction);
	}

	@Test
	void test_southEastDirection() {
		end = board.getSquare(3, 3);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("South-East ==> " + direction);
		assertEquals(Direction.SOUTH_EAST, direction);
	}

	@Test
	void test_southDirection() {
		end = board.getSquare(3, 2);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("South ==> " + direction);
		assertEquals(Direction.SOUTH, direction);
	}

	@Test
	void test_southWestDirection() {
		end = board.getSquare(3, 1);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("South-West ==> " + direction);
		assertEquals(Direction.SOUTH_WEST, direction);
	}

	@Test
	void test_westDirection() {
		end = board.getSquare(2, 1);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("West ==> " + direction);
		assertEquals(Direction.WEST, direction);
	}

	@Test
	void test_northWestDirection() {
		end = board.getSquare(1, 1);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("North-West ==> " + direction);
		assertEquals(Direction.NORTH_WEST, direction);
	}

	@Test
	void test_invalidDirection() {
		end = board.getSquare(4, 3);
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("Invalid ==> " + direction);
		assertEquals(Direction.INVALID, direction);
	}

	@Test
	void test_noDirection() {
		Direction direction = Util.calculateDirection(start, start);
		LOGGER.info("None ==> " + direction);
		assertEquals(Direction.INVALID, direction);
	}
}