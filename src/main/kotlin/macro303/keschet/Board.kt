package macro303.keschet

import macro303.keschet.pieces.Emperor
import macro303.keschet.pieces.IPiece

internal class Board {
	private var cells = ArrayList<ArrayList<Square>>()

	init {
		(0..10).mapTo(cells) { i -> (0..10).mapTo(ArrayList()) { Square(colour = if (it % 2 == 0) if (i % 2 == 0) Colour.GREEN else Colour.MAGENTA else if (i % 2 != 0) Colour.GREEN else Colour.MAGENTA) } }
	}

	fun draw() {
		Console.showTitle(title = "Board", colour = Colour.YELLOW)
		cells.forEachIndexed { rowIndex, columnCells ->
			columnCells.forEachIndexed { columnIndex, _ ->
				when {
					rowIndex == 0 -> print("${if (columnIndex == 0) "" else " "}$columnIndex${if (columnIndex == 10) "" else " "}")
					columnIndex == 0 -> print("$rowIndex${if (rowIndex == 10) "" else " "}")
					else -> Console.cell(cell = getCell(coords = Pair(columnIndex, rowIndex)))
				}
			}
			println()
		}
	}

	fun teamCount(team: Team): Int {
		var counter = 0
		cells.forEachIndexed { rowIndex, columnCells ->
			columnCells.forEachIndexed { columnIndex, _ ->
				if (rowIndex != 0 && columnIndex != 0) {
					val cell = getCell(Pair(columnIndex, rowIndex))
					if (cell.piece != null && cell.piece?.teamColour == team.colour && cell.piece !is Emperor)
						counter++
				}
			}
		}
		return counter
	}

	fun getCell(coords: Pair<Int, Int>): Square {
		return cells[coords.first - 1][coords.second - 1]
	}

	fun setPiece(coords: Pair<Int, Int>, piece: IPiece) {
		getCell(coords = coords).piece = piece
	}

	fun removePiece(coords: Pair<Int, Int>) {
		getCell(coords = coords).piece = null
	}

	companion object {
		fun calculateDirection(source: Pair<Int, Int>, destination: Pair<Int, Int>): Direction {
			return when {
				source.first == destination.first && source.second - destination.second < 0 -> Direction.SOUTH
				source.first == destination.first && source.second - destination.second > 0 -> Direction.NORTH
				source.second == destination.second && source.first - destination.first < 0 -> Direction.WEST
				source.second == destination.second && source.first - destination.first > 0 -> Direction.EAST
				source.first - destination.first > 0 && source.second - destination.second > 0 -> Direction.NORTH_EAST
				source.first - destination.first < 0 && source.second - destination.second < 0 -> Direction.SOUTH_WEST
				source.first - destination.first > 0 && source.second - destination.second < 0 -> Direction.SOUTH_EAST
				source.first - destination.first < 0 && source.second - destination.second > 0 -> Direction.NORTH_WEST
				else -> Direction.INVALID
			}
		}

		fun calculateDistance(source: Pair<Int, Int>, destination: Pair<Int, Int>, direction: Direction): Int {
			if (direction == Direction.NORTH || direction == Direction.SOUTH)
				return source.second - destination.second
			return source.first - destination.first
		}
	}

	fun getAllSurroundingPieces(coords: Pair<Int, Int>): ArrayList<IPiece> {
		val pieces = ArrayList<IPiece>()
		if (coords.first in 2..8 && coords.second in 2..8) {
			if (getCell(coords = Pair(coords.first - 1, coords.second)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first - 1, coords.second)).piece!!)
			if (getCell(coords = Pair(coords.first - 1, coords.second - 1)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first - 1, coords.second - 1)).piece!!)
			if (getCell(coords = Pair(coords.first, coords.second - 1)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first, coords.second - 1)).piece!!)
			if (getCell(coords = Pair(coords.first + 1, coords.second - 1)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first + 1, coords.second - 1)).piece!!)
			if (getCell(coords = Pair(coords.first + 1, coords.second)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first + 1, coords.second)).piece!!)
			if (getCell(coords = Pair(coords.first + 1, coords.second + 1)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first + 1, coords.second + 1)).piece!!)
			if (getCell(coords = Pair(coords.first, coords.second + 1)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first, coords.second + 1)).piece!!)
			if (getCell(coords = Pair(coords.first - 1, coords.second + 1)).piece != null)
				pieces.add(getCell(coords = Pair(coords.first - 1, coords.second + 1)).piece!!)
		}
		return pieces
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Board) return false

		if (cells != other.cells) return false

		return true
	}

	override fun hashCode(): Int {
		return cells.hashCode()
	}

	override fun toString(): String {
		return "Board(cells=$cells)"
	}
}