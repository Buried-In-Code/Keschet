package macro303.base

import org.junit.Test

/**
 * Created by Macro303 on 2018-02-07.
 */
class Direction_Test {

	@Test
	fun test_northDirection() {
		val north = IBoard.calculateDirection(Pair(2, 2), Pair(2, 1))
		assert(north == Direction.NORTH)
	}

	@Test
	fun test_northEastDirection() {
		val northEast = IBoard.calculateDirection(Pair(2, 2), Pair(3, 1))
		assert(northEast == Direction.NORTH_EAST)
	}

	@Test
	fun test_eastDirection() {
		val east = IBoard.calculateDirection(Pair(2, 2), Pair(3, 2))
		assert(east == Direction.EAST)
	}

	@Test
	fun test_southEastDirection() {
		val southEast = IBoard.calculateDirection(Pair(2, 2), Pair(3, 3))
		assert(southEast == Direction.SOUTH_EAST)
	}

	@Test
	fun test_southDirection() {
		val south = IBoard.calculateDirection(Pair(2, 2), Pair(2, 3))
		assert(south == Direction.SOUTH)
	}

	@Test
	fun test_southWestDirection() {
		val southWest = IBoard.calculateDirection(Pair(2, 2), Pair(1, 3))
		assert(southWest == Direction.SOUTH_WEST)
	}

	@Test
	fun test_westDirection() {
		val west = IBoard.calculateDirection(Pair(2, 2), Pair(1, 2))
		assert(west == Direction.WEST)
	}

	@Test
	fun test_northWestDirection() {
		val northWest = IBoard.calculateDirection(Pair(2, 2), Pair(1, 1))
		assert(northWest == Direction.NORTH_WEST)
	}

	@Test
	fun test_invalidDirection() {
		val invalid = IBoard.calculateDirection(Pair(10, 10), Pair(9, 1))
		assert(invalid == Direction.INVALID)
	}
}