package macro303.keschet

import macro303.keschet.pieces.IPiece
import macro303.keschet.pieces.Scholar
import macro303.keschet.pieces.Thief
import org.slf4j.LoggerFactory

internal object Keschet {
	private val LOGGER = LoggerFactory.getLogger(Keschet.javaClass)
	private val team1 = Team(Colour.BLUE)
	private val team2 = Team(Colour.RED)
	private var board = Board()

	@JvmStatic
	fun main(args: Array<String>) {
		Console.showRules()
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
			placePiece(team2.pieces[i])
		}
	}

	private fun placePiece(piece: IPiece) {
		board.draw()
		Console.showTitle(title = "${piece.teamColour.name}'s Turn", colour = piece.teamColour)
		var placed = false
		do {
			val coords = selectCoords(prompt = "Place your ${piece.javaClass.simpleName}\n")
			if ((piece.teamColour == team1.colour && coords.second <= 3) || (piece.teamColour == team2.colour && coords.second >= 8)) {
				val cell = board.getCell(coords = coords)
				if (cell.piece == null) {
					board.setPiece(coords = coords, piece = piece)
					placed = true
				} else
					Console.error(message = "Must be placed on empty square")
			} else
				Console.error(message = "Invalid placement. Place within 3 rows on your side")
		} while (!placed)
	}

	//Select Piece
	//Check if Piece exists
	//+Check if Piece is yours
	// +Select Destination
	//  Check if Destination is filled by a Piece
	//  +Check if Destination is filled by enemy Piece
	//   +Check if Destination is nearby to enemy Scholar
	//    -Check if Each Movement is possible
	//     +Move Piece
	//  -Check if Each Movement is possible
	//   +Move Piece

	private fun executeTurn(team: Team) {
		do {
			var successful = false
			board.draw()
			Console.showTitle(title = "${team.colour.name}'s Turn", colour = team.colour)
			val source = selectCoords(prompt = "Select Piece\n")
			val sourcePiece = board.getCell(coords = source).piece
			if (sourcePiece == null)
				Console.error(message = "No Piece at those square")
			else {
				if (sourcePiece.teamColour == team.colour) {
					val destination = selectCoords(prompt = "Select Destination\n")
					val destinationPiece = board.getCell(coords = destination).piece
					if (sourcePiece.validMovement(source = source, destination = destination, board = board)) {
						successful = if (destinationPiece == null)
							movePiece(source = source, destination = destination, piece = sourcePiece)
						else
							takePiece(source = source, destination = destination)
					} else
						Console.error(message = "Invalid movement")
				} else
					Console.error(message = "That Piece isn't yours")
			}
		} while (!successful)
	}

	private fun takePiece(source: Pair<Int, Int>, destination: Pair<Int, Int>): Boolean {
		val sourceSquare = board.getCell(coords = source)
		val sourcePiece = sourceSquare.piece!!
		val destinationSquare = board.getCell(coords = destination)
		val destinationPiece = destinationSquare.piece!!
		when {
			destinationPiece.teamColour == sourcePiece.teamColour -> Console.error(message = "You can't take your own Pieces")
			board.getAllSurroundingPieces(coords = destination).any { it.teamColour != sourcePiece.teamColour && it is Scholar } -> Console.error(message = "That Piece is protected by an nearby Scholar")
			movePiece(source = source, destination = destination, piece = sourcePiece) && sourcePiece is Thief -> {
				do {
					var nearby = false
					val newPieceLocation = selectCoords(prompt = "Place Stolen piece\n")
					val newPieceCell = board.getCell(coords = newPieceLocation)
					if (newPieceCell.piece == null) {
						val surrounding = board.getAllSurroundingPieces(coords = newPieceLocation)
						if (surrounding.contains(sourceSquare.piece!!)) {
							destinationPiece.teamColour = sourcePiece.teamColour
							board.setPiece(newPieceLocation, piece = destinationPiece)
							nearby = true
						}
					} else {
						Console.error(message = "Must be placed on empty square")
					}
				} while (!nearby)
				return true
			}
		}
		return false
	}

	private fun movePiece(source: Pair<Int, Int>, destination: Pair<Int, Int>, piece: IPiece): Boolean {
		val direction = Board.calculateDirection(source = source, destination = destination)
		val distance = Math.abs(Board.calculateDistance(source = source, destination = destination, direction = direction))
		var x = source.first
		var y = source.second
		var invalid = false
		loop@ for (i in 0 until distance) {
			when (direction) {
				Direction.NORTH -> y--
				Direction.NORTH_EAST -> {
					x++
					y--
				}
				Direction.EAST -> x++
				Direction.SOUTH_EAST -> {
					x++
					y++
				}
				Direction.SOUTH -> y++
				Direction.SOUTH_WEST -> {
					x--
					y++
				}
				Direction.WEST -> x--
				Direction.NORTH_WEST -> {
					x--
					y--
				}
				else -> invalid = true
			}
			val tempCell = board.getCell(coords = Pair(x, y))
			if ((tempCell.piece != null && i != distance - 1) || invalid) {
				invalid = true
				break
			}
		}
		if (invalid) {
			board.removePiece(coords = source)
			board.removePiece(coords = destination)
			board.setPiece(coords = destination, piece = piece)
			return true
		}
		Console.error(message = "Invalid movement")
		return false
	}

	private fun selectCoords(prompt: String): Pair<Int, Int> {
		do {
			var x = -1
			var y = -1
			try {
				Console.info(message = prompt)
				val input = Reader.readConsole(prompt = "Square (x:y)")
				if (input.contains("Help", ignoreCase = true)) {
					Console.helpMenu(input = input)
					return selectCoords(prompt = prompt)
				}
				val coords = input.split(":")
				x = coords[0].toInt()
				y = coords[1].toInt()
				if (x in 1..10 && y in 1..10)
					return Pair(x, y)
				Console.error(message = "That square isn't the board")
			} catch (_: NumberFormatException) {
				Console.error(message = "Invalid selection")
			} catch (_: IndexOutOfBoundsException) {
				Console.error(message = "Invalid selection")
			}
		} while (x !in 1..10 || y !in 1..10)
		return Pair(1, 1)
	}

	private fun checkWinCondition(): Boolean {
		if (board.teamCount(team = team1) == 0)
			Console.showTitle(title = "${team2.colour.name} Team Wins", colour = team2.colour)
		if (board.teamCount(team = team2) == 0)
			Console.showTitle(title = "${team1.colour.name} Team Wins", colour = team1.colour)
		return board.teamCount(team = team1) == 0 || board.teamCount(team = team2) == 0
	}
}