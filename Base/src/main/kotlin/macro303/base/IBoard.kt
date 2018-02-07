package macro303.base

import macro303.base.pieces.Piece
import org.apache.logging.log4j.LogManager
import java.util.*

/**
 * Created by Macro303 on 2017-10-20.
 */
interface IBoard {
	fun getSquare(square: Pair<Int, Int>): Square?
	fun countPieces(team: Team): Int
	fun pieceStillOnBoard(clazz: Class<*>, teamColour: Colour): Boolean
	fun getAllSurroundingPieces(squareLocation: Pair<Int, Int>): ArrayList<Piece>

	companion object {
		private val LOGGER = LogManager.getLogger(Board::class.java)

		fun calculateDistance(start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
			val distance = when (calculateDirection(start, end)) {
				Direction.NORTH, Direction.SOUTH -> start.second - end.second
				Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST -> start.first - end.first
				else -> 0
			}
			LOGGER.trace("int calculateDistance(Pair<Int, Int>, Pair<Int, Int>) = $distance")
			return distance
		}

		fun calculateDirection(start: Pair<Int, Int>, end: Pair<Int, Int>): Direction {
			val direction: Direction = when {
				end.first == start.first && end.second - start.second < 0 -> {
					Direction.NORTH
				}
				end.first - start.first > 0 && end.second - start.second < 0 && Math.abs(end.second - start.second) - Math.abs(
					end.first - start.first
				) == 0 -> {
					Direction.NORTH_EAST
				}
				end.first - start.first > 0 && end.second == start.second -> {
					Direction.EAST
				}
				end.first - start.first > 0 && end.second - start.second > 0 && Math.abs(end.first - start.first) - Math.abs(
					end.second - start.second
				) == 0 -> {
					Direction.SOUTH_EAST
				}
				end.first == start.first && end.second - start.second > 0 -> {
					Direction.SOUTH
				}
				end.first - start.first < 0 && end.second - start.second > 0 && Math.abs(end.first - start.first) - Math.abs(
					end.second - start.second
				) == 0 -> {
					Direction.SOUTH_WEST
				}
				end.first - start.first < 0 && end.second == start.second -> {
					Direction.WEST
				}
				end.first - start.first < 0 && end.second - start.second < 0 && Math.abs(end.first - start.first) - Math.abs(
					end.second - start.second
				) == 0 -> {
					Direction.NORTH_WEST
				}
				else -> Direction.INVALID
			}
			LOGGER.trace("Direction calculateDirection(Pair<Int, Int>, Pair<Int, Int>) = $direction")
			return direction
		}
	}
}