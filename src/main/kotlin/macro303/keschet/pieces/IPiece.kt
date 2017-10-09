package macro303.keschet.pieces

import macro303.keschet.Board
import macro303.keschet.Colour
import macro303.keschet.Direction

internal interface IPiece {
	val teamColour: Colour
	val maxDistance: Int
	val symbol: String
	val validDirections: Array<Direction>

	fun validMovement(oldCoords: Pair<Int, Int>, newCoords: Pair<Int, Int>, board: Board): Boolean {
		val direction = Board.calculateDirection(oldCoords = oldCoords, newCoords = newCoords)
		val distance = Board.calculateDistance(oldCoords = oldCoords, newCoords = newCoords, direction = direction)
		if (validDirections.contains(direction) && maxDistance >= distance)
			return true
		return false
	}
}