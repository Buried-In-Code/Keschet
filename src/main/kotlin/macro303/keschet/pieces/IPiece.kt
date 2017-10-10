package macro303.keschet.pieces

import macro303.keschet.Board
import macro303.keschet.Colour
import macro303.keschet.Direction

internal interface IPiece {
	var teamColour: Colour
	val maxDistance: Int
	val symbol: String
	val validDirections: Array<Direction>

	fun validMovement(source: Pair<Int, Int>, destination: Pair<Int, Int>, board: Board): Boolean {
		val direction = Board.calculateDirection(source = source, destination = destination)
		val distance = Board.calculateDistance(source = source, destination = destination, direction = direction)
		if (validDirections.contains(direction) && maxDistance >= distance)
			return true
		return false
	}
}