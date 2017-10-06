package macro303.keschet

import macro303.keschet.pieces.Piece
import org.slf4j.LoggerFactory

internal object Keschet {
	private val LOGGER = LoggerFactory.getLogger(Keschet.javaClass)
	private val team1 = Team(Colour.BLUE)
	private val team2 = Team(Colour.RED)
	private var board = Board()

	@JvmStatic
	fun main(args: Array<String>) {
		board.draw()
		placePieces()
		var finished: Boolean
		do {
			executeTurn(team = team1)
			finished = checkWinCondition()
			if (!finished) {
				executeTurn(team = team2)
				finished = checkWinCondition()
			}
		} while (!finished)
	}

	private fun placePieces() {
		for (i in 0 until team1.pieces.size) {
			placePiece(team1.pieces[i])
			board.draw()
			placePiece(team2.pieces[i])
			board.draw()
		}
	}

	private fun placePiece(piece: Piece) {
		var placed = false
		do {
			val coords = selectCoords()
			if ((piece.teamColour == team1.colour && coords.second <= 3) || (piece.teamColour == team2.colour && coords.second >= 7)) {
				val cell = board.getCell(coords = coords)
				if (cell.piece == null) {
					board.setPiece(coords = coords, piece = piece)
					placed = true
				} else
					Console.error(message = "Cell is Occupied")
			} else
				Console.error(message = "Invalid placement. Place within 3 rows on your side")
		} while (!placed)
	}

	private fun executeTurn(team: Team) {
		Console.info(message = "${team.colour.name}'s Turn")
		val source = selectCoords()
		val sourcePiece = board.getCell(coords = source).piece
		if (sourcePiece != null) {
			val dest = selectCoords()
			val destPiece = board.getCell(coords = dest).piece
			if (destPiece != null) {
				if (sourcePiece.teamColour == destPiece.teamColour) {
					Console.error(message = "You can't take your own piece")
					executeTurn(team = team)
				} else {
					board.removePiece(coords = source)
					board.removePiece(coords = dest)
					board.setPiece(coords = dest, piece = sourcePiece)
				}
			} else {
				board.removePiece(coords = source)
				board.setPiece(coords = dest, piece = sourcePiece)
			}
		} else {
			Console.error(message = "No pieces in that cell")
			executeTurn(team = team)
		}
	}

	fun selectCoords(): Pair<Int, Int> {
		do {
			val coords = Reader.readConsole(prompt = "Coordinates? (x,y)").split(",")
			val x = coords[0].toInt()
			val y = coords[1].toInt()
			if (x in 1..9 && y in 1..9)
				return Pair(x, y)
			else
				Console.error(message = "Coordinate isn't on the board")
		} while (x !in 1..9 || y !in 1..9)
		return Pair(1, 1)
	}

	private fun checkWinCondition(): Boolean {
		var team1Count = 0
		var team2Count = 0
		for (i in 0..9)
			(0..9).mapNotNull { board.getCell(coords = Pair(i, it)).piece }
					.forEach {
						if (it.teamColour == team1.colour)
							team1Count++
						else if (it.teamColour == team2.colour)
							team2Count++
					}
		return team1Count == 0 || team2Count == 0
	}
}