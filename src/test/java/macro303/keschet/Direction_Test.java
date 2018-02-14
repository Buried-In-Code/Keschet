package macro303.keschet;

import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Direction_Test {
	private static final Logger LOGGER = LogManager.getLogger(Direction_Test.class);
	private static Board board;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		board = new Board();
	}

	@Test
	public void test_northDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(2, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North Direction: " + direction);
		assert direction == Direction.NORTH;
	}

	@Test
	public void test_northEastDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North-East Direction: " + direction);
		assert direction == Direction.NORTH_EAST;
	}

	@Test
	public void test_eastDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("East Direction: " + direction);
		assert direction == Direction.EAST;
	}

	@Test
	public void test_southEastDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South-East Direction: " + direction);
		assert direction == Direction.SOUTH_EAST;
	}

	@Test
	public void test_southDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South Direction: " + direction);
		assert direction == Direction.SOUTH;
	}

	@Test
	public void test_southWestDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South-West Direction: " + direction);
		assert direction == Direction.SOUTH_WEST;
	}

	@Test
	public void test_westDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("West Direction: " + direction);
		assert direction == Direction.WEST;
	}

	@Test
	public void test_northWestDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North-West Direction: " + direction);
		assert direction == Direction.NORTH_WEST;
	}

	@Test
	public void test_invalidDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(4, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("Invalid Direction: " + direction);
		assert direction == Direction.INVALID;
	}

	@Test
	public void test_noDirection() {
		Square start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
		Square end = board.getSquare(new Coordinates(2, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("No Direction: " + direction);
		assert direction == Direction.INVALID;
	}
}