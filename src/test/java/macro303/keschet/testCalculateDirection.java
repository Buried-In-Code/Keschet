package macro303.keschet;

import org.junit.Test;

public class testCalculateDirection {

	@Test
	public void testNorthDirection(){
		Direction north = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(2, 1));
		assert north == Direction.NORTH;
	}

	@Test
	public void testNorthEastDirection(){
		Direction north_east = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(3, 1));
		assert north_east == Direction.NORTH_EAST;
	}

	@Test
	public void testEastDirection(){
		Direction east = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(3, 2));
		assert east == Direction.EAST;
	}

	@Test
	public void testSouthEastDirection(){
		Direction south_east = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(3, 3));
		assert south_east == Direction.SOUTH_EAST;
	}

	@Test
	public void testSouthDirection(){
		Direction south = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(2, 3));
		assert south == Direction.SOUTH;
	}

	@Test
	public void testSouthWestDirection(){
		Direction south_west = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(1, 3));
		assert south_west == Direction.SOUTH_WEST;
	}

	@Test
	public void testWestDirection(){
		Direction west = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(1, 2));
		assert west == Direction.WEST;
	}

	@Test
	public void testNorthWestDirection(){
		Direction north_west = Board.calculateDirection(new Pair<>(2, 2), new Pair<>(1, 1));
		assert north_west == Direction.NORTH_WEST;
	}

	@Test
	public void testInvalidDirection(){
		Direction invalid = Board.calculateDirection(new Pair<>(10, 10), new Pair<>(9, 1));
		assert invalid == Direction.INVALID;
	}
}