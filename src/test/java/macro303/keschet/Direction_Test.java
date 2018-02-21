package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.board_game.Tester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Direction_Test {
	private static final Logger LOGGER = LogManager.getLogger(Direction_Test.class);
	private static Board board;
	private static Square start;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		board = new Board(10);
		start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
	}

	@Test
	public void test_northDirection() {
		Square end = board.getSquare(new Coordinates(2, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North Direction: " + direction);
		Assert.assertEquals(Direction.NORTH, direction);
	}

	@Test
	public void test_northEastDirection() {
		Square end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North-East Direction: " + direction);
		Assert.assertEquals(Direction.NORTH_EAST, direction);
	}

	@Test
	public void test_eastDirection() {
		Square end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("East Direction: " + direction);
		Assert.assertEquals(Direction.EAST, direction);
	}

	@Test
	public void test_southEastDirection() {
		Square end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South-East Direction: " + direction);
		Assert.assertEquals(Direction.SOUTH_EAST, direction);
	}

	@Test
	public void test_southDirection() {
		Square end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South Direction: " + direction);
		Assert.assertEquals(Direction.SOUTH, direction);
	}

	@Test
	public void test_southWestDirection() {
		Square end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South-West Direction: " + direction);
		Assert.assertEquals(Direction.SOUTH_WEST, direction);
	}

	@Test
	public void test_westDirection() {
		Square end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("West Direction: " + direction);
		Assert.assertEquals(Direction.WEST, direction);
	}

	@Test
	public void test_northWestDirection() {
		Square end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North-West Direction: " + direction);
		Assert.assertEquals(Direction.NORTH_WEST, direction);
	}

	@Test
	public void test_invalidDirection() {
		Square end = board.getSquare(new Coordinates(4, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("Invalid Direction: " + direction);
		Assert.assertEquals(Direction.INVALID, direction);
	}

	@Test
	public void test_noDirection() {
		Direction direction = Util.calculateDirection(start, start);
		LOGGER.debug("No Direction: " + direction);
		Assert.assertEquals(Direction.INVALID, direction);
	}
}