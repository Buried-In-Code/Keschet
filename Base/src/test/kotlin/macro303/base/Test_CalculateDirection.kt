package macro303.base

import org.junit.Test

class Test_CalculateDirection {

	@Test
	fun test_northDirection() {
		val north = IBoard.calculateDirection(Pair(2, 2), Pair(2, 1))
		assert(north == Direction.NORTH)
	}

	@Test
	fun test_northEastDirection() {
		val north_east = IBoard.calculateDirection(Pair(2, 2), Pair(3, 1))
		assert(north_east == Direction.NORTH_EAST)
	}

	@Test
	fun test_eastDirection() {
		val east = IBoard.calculateDirection(Pair(2, 2), Pair(3, 2))
		assert(east == Direction.EAST)
	}

	@Test
	fun test_southEastDirection() {
		val south_east = IBoard.calculateDirection(Pair(2, 2), Pair(3, 3))
		assert(south_east == Direction.SOUTH_EAST)
	}

	@Test
	fun test_southDirection() {
		val south = IBoard.calculateDirection(Pair(2, 2), Pair(2, 3))
		assert(south == Direction.SOUTH)
	}

	@Test
	fun test_southWestDirection() {
		val south_west = IBoard.calculateDirection(Pair(2, 2), Pair(1, 3))
		assert(south_west == Direction.SOUTH_WEST)
	}

	@Test
	fun test_westDirection() {
		val west = IBoard.calculateDirection(Pair(2, 2), Pair(1, 2))
		assert(west == Direction.WEST)
	}

	@Test
	fun test_northWestDirection() {
		val north_west = IBoard.calculateDirection(Pair(2, 2), Pair(1, 1))
		assert(north_west == Direction.NORTH_WEST)
	}

	@Test
	fun test_invalidDirection() {
		val invalid = IBoard.calculateDirection(Pair(10, 10), Pair(9, 1))
		assert(invalid == Direction.INVALID)
	}
}