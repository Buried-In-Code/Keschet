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
	private static KeschetDisplay display;
	private static Square start;

	@BeforeClass
	public static void beforeClass() {
		Tester.getInstance().setTesting(true);
		board = new Board(10);
		display = new KeschetDisplay(board);
		start = board.getSquare(new Coordinates(2, 2));
		assert start != null;
	}

	@Test
	public void test_northDirection() {
		display.showMessage("=====North Direction=====");
		Square end = board.getSquare(new Coordinates(2, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("North Direction: " + direction);
		Assert.assertEquals(Direction.NORTH, direction);
	}

	@Test
	public void test_northEastDirection() {
		display.showMessage("=====North-East Direction=====");
		Square end = board.getSquare(new Coordinates(3, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("North-East Direction: " + direction);
		Assert.assertEquals(Direction.NORTH_EAST, direction);
	}

	@Test
	public void test_eastDirection() {
		display.showMessage("=====East Direction=====");
		Square end = board.getSquare(new Coordinates(3, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("East Direction: " + direction);
		Assert.assertEquals(Direction.EAST, direction);
	}

	@Test
	public void test_southEastDirection() {
		display.showMessage("=====South-East Direction=====");
		Square end = board.getSquare(new Coordinates(3, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("South-East Direction: " + direction);
		Assert.assertEquals(Direction.SOUTH_EAST, direction);
	}

	@Test
	public void test_southDirection() {
		display.showMessage("=====South Direction=====");
		Square end = board.getSquare(new Coordinates(2, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("South Direction: " + direction);
		Assert.assertEquals(Direction.SOUTH, direction);
	}

	@Test
	public void test_southWestDirection() {
		display.showMessage("=====South-West Direction=====");
		Square end = board.getSquare(new Coordinates(1, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("South-West Direction: " + direction);
		Assert.assertEquals(Direction.SOUTH_WEST, direction);
	}

	@Test
	public void test_westDirection() {
		display.showMessage("=====West Direction=====");
		Square end = board.getSquare(new Coordinates(1, 2));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("West Direction: " + direction);
		Assert.assertEquals(Direction.WEST, direction);
	}

	@Test
	public void test_northWestDirection() {
		display.showMessage("=====North-West Direction=====");
		Square end = board.getSquare(new Coordinates(1, 1));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("North-West Direction: " + direction);
		Assert.assertEquals(Direction.NORTH_WEST, direction);
	}

	@Test
	public void test_invalidDirection() {
		display.showMessage("=====Invalid Direction=====");
		Square end = board.getSquare(new Coordinates(4, 3));
		assert end != null;
		Direction direction = Util.calculateDirection(start, end);
		display.showMessage("Invalid Direction: " + direction);
		Assert.assertEquals(Direction.INVALID, direction);
	}

	@Test
	public void test_noDirection() {
		display.showMessage("=====No Direction=====");
		Direction direction = Util.calculateDirection(start, start);
		display.showMessage("No Direction: " + direction);
		Assert.assertEquals(Direction.INVALID, direction);
	}
}