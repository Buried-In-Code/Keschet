package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.pieces.General;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Direction_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static Board board;
	private static Square start;
	private static Square end;

	@BeforeAll
	static void beforeAll() {
		board = new Board(10);
		start = board.getSquare(new Coordinates(2, 2));
	}

	@BeforeEach
	void beforeEach(){
		start.setItem(new General(Colour.BLUE));
		if (end != null)
			end.setItem(null);
	}

	@Test
	void test_northDirection() {
		end = board.getSquare(new Coordinates(2, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("North ==> " + direction);
		assertEquals(Direction.NORTH, direction);
	}

	@Test
	void test_northEastDirection() {
		end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("North-East ==> " + direction);
		assertEquals(Direction.NORTH_EAST, direction);
	}

	@Test
	void test_eastDirection() {
		end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("East ==> " + direction);
		assertEquals(Direction.EAST, direction);
	}

	@Test
	void test_southEastDirection() {
		end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("South-East ==> " + direction);
		assertEquals(Direction.SOUTH_EAST, direction);
	}

	@Test
	void test_southDirection() {
		end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("South ==> " + direction);
		assertEquals(Direction.SOUTH, direction);
	}

	@Test
	void test_southWestDirection() {
		end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("South-West ==> " + direction);
		assertEquals(Direction.SOUTH_WEST, direction);
	}

	@Test
	void test_westDirection() {
		end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("West ==> " + direction);
		assertEquals(Direction.WEST, direction);
	}

	@Test
	void test_northWestDirection() {
		end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.info("North-West ==> " + direction);
		assertEquals(Direction.NORTH_WEST, direction);
	}

	@Test
	void test_invalidDirection() {
		end = board.getSquare(new Coordinates(4, 3));
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