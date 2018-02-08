package macro303.keschet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Direction_Test {
	private static final Logger LOGGER = LogManager.getLogger(Direction_Test.class);

	@BeforeClass
	public static void setupClass() {
		Tester.getInstance().setTesting(true);
	}

	@Test
	public void test_northDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(2, 1);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North: " + direction);
		assert (direction == Direction.NORTH);
	}

	@Test
	public void test_northEastDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(3, 1);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North-East: " + direction);
		assert (direction == Direction.NORTH_EAST);
	}

	@Test
	public void test_eastDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(3, 2);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("East: " + direction);
		assert (direction == Direction.EAST);
	}

	@Test
	public void test_southEastDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(3, 3);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South-East: " + direction);
		assert (direction == Direction.SOUTH_EAST);
	}

	@Test
	public void test_southDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(2, 3);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South: " + direction);
		assert (direction == Direction.SOUTH);
	}

	@Test
	public void test_southWestDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(1, 3);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("South-West: " + direction);
		assert (direction == Direction.SOUTH_WEST);
	}

	@Test
	public void test_westDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(1, 2);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("West: " + direction);
		assert (direction == Direction.WEST);
	}

	@Test
	public void test_northWestDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(1, 1);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("North-West: " + direction);
		assert (direction == Direction.NORTH_WEST);
	}

	@Test
	public void test_invalidDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(4, 3);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("Invalid: " + direction);
		assert (direction == Direction.INVALID);
	}

	@Test
	public void test_noDirection() {
		Coordinates start = new Coordinates(2, 2);
		Coordinates end = new Coordinates(2, 2);
		Direction direction = Util.calculateDirection(start, end);
		LOGGER.debug("No: " + direction);
		assert (direction == Direction.INVALID);
	}
}