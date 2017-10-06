package macro303.keschet

import macro303.keschet.pieces.Piece
import java.util.*

internal class Board {
	private var cells = ArrayList<ArrayList<Cell>>()

	init {
		(0..9).mapTo(cells) { i -> (0..9).mapTo(ArrayList()) { Cell(colour = if (it % 2 == 0) if (i % 2 == 0) Colour.GREEN else Colour.MAGENTA else if (i % 2 != 0) Colour.GREEN else Colour.MAGENTA) } }
	}

	fun draw() {
		Thread.sleep(10)
		cells.forEachIndexed { rowIndex, columnCells ->
			columnCells.forEachIndexed { columnIndex, _ ->
				when {
					rowIndex == 0 -> Console.info(message = "${if (columnIndex == 0) "" else " "}$columnIndex${if (columnIndex == 9) "" else " "}")
					columnIndex == 0 -> Console.info(message = "$rowIndex ")
					else -> Console.cell(cell = getCell(coords = Pair(columnIndex, rowIndex)))
				}
			}
			println()
		}
	}

	fun getCell(coords: Pair<Int, Int>): Cell {
		return cells[coords.first - 1][coords.second - 1]
	}

	fun setPiece(coords: Pair<Int, Int>, piece: Piece) {
		getCell(coords = coords).piece = piece
	}

	fun removePiece(coords: Pair<Int, Int>) {
		getCell(coords = coords).piece = null
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