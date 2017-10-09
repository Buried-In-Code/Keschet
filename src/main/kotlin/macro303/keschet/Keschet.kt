package macro303.keschet

import macro303.keschet.pieces.*
import org.slf4j.LoggerFactory
import java.util.*

internal object Keschet {
	private val LOGGER = LoggerFactory.getLogger(Keschet.javaClass)
	private val team1 = Team(Colour.BLUE)
	private val team2 = Team(Colour.RED)
	private var board = Board()

	@JvmStatic
	fun main(args: Array<String>) {
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

	//Select Piece
	//Check if Piece exists
	//+Check if Piece is yours
	// +Select Destination
	//  Check if Destination is filled by a Piece
	//  +Check if Destination is filled by enemy Piece
	//   +Check if Destination is adjacent to enemy Scholar
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
			if (sourcePiece == null) {
				Console.error(message = "No Piece at those coordinates")
			} else {
				if (sourcePiece.teamColour == team.colour) {
					val destination = selectCoords(prompt = "Select Destination\n")
					val destinationPiece = board.getCell(coords = destination).piece
					if (destinationPiece == null) {
						successful = movePiece(sourceCoords = source, destinationCoords = destination, piece = sourcePiece)
					} else {
						if (destinationPiece.teamColour == team.colour) {
							Console.error(message = "You can't take your own Pieces")
						} else {
							if (board.getAllAdjacent(coords = destination).any { it.teamColour != team.colour && it is Scholar }) {
								Console.error(message = "That Piece is protected by an adjacent Scholar")
							} else {
								successful = movePiece(sourceCoords = source, destinationCoords = destination, piece = sourcePiece)
							}
						}
					}
				} else {
					Console.error(message = "That Piece isn't yours")
				}
			}
		} while (!successful)
	}

	private fun movePiece(sourceCoords: Pair<Int, Int>, destinationCoords: Pair<Int, Int>, piece: IPiece): Boolean {
		var successful = true
		if (piece.validMovement(oldCoords = sourceCoords, newCoords = destinationCoords, board = board)) {
			val direction = Board.calculateDirection(oldCoords = sourceCoords, newCoords = destinationCoords)
			val distance = Board.calculateDistance(oldCoords = sourceCoords, newCoords = destinationCoords, direction = direction)
			var x = sourceCoords.first
			var y = sourceCoords.second
			var count = 0
			do {
				when (direction) {
					Direction.NORTH -> y++
					Direction.NORTH_EAST -> {
						x++
						y++
					}
					Direction.EAST -> x++
					Direction.SOUTH_EAST -> {
						x++
						y--
					}
					Direction.SOUTH -> y--
					Direction.SOUTH_WEST -> {
						x--
						y--
					}
					Direction.WEST -> x--
					Direction.NORTH_WEST -> {
						x--
						y++
					}
					else -> successful = false
				}
				val tempCell = board.getCell(coords = Pair(x, y))
				if (tempCell.piece != null) {
					Console.error(message = "There is a piece in your way preventing you from moving to your destination")
					successful = false
				}
				count++
				if (!successful)
					break
			} while (count < distance)
			if (successful) {
				board.removePiece(coords = sourceCoords)
				board.removePiece(coords = destinationCoords)
				board.setPiece(coords = destinationCoords, piece = piece)
			}
		} else {
			return false
		}
		return successful
	}

	private fun selectCoords(prompt: String): Pair<Int, Int> {
		do {
			var x = -1
			var y = -1
			try {
				Console.info(message = prompt)
				val input = Reader.readConsole(prompt = "Coordinates (x:y)")
				if (input.contains("Help", ignoreCase = true)) {
					helpMenu(input = input)
					return selectCoords(prompt = prompt)
				}
				val coords = input.split(":")
				x = coords[0].toInt()
				y = coords[1].toInt()
				if (x in 1..9 && y in 1..9)
					return Pair(x, y)
				else
					Console.error(message = "Coordinates aren't on the board")
			} catch (_: NumberFormatException) {
				Console.error(message = "Invalid Coordinates")
			} catch (_: IndexOutOfBoundsException) {
				Console.error(message = "Invalid Coordinates")
			}
		} while (x !in 1..9 || y !in 1..9)
		return Pair(1, 1)
	}

	private fun checkWinCondition(): Boolean {
		if (board.teamCount(team = team1) == 0)
			Console.showTitle(title = "${team2.colour.name} Team Wins", colour = team2.colour)
		if (board.teamCount(team = team2) == 0)
			Console.showTitle(title = "${team1.colour.name} Team Wins", colour = team1.colour)
		return board.teamCount(team = team1) == 0 || board.teamCount(team = team2) == 0
	}

	private fun helpMenu(input: String) {
		val temp: IPiece? = when {
			input.equals("Help", ignoreCase = true) -> {
				Console.info(message = "'Help <Symbol>' OR 'Help <Piece>' for information about piece")
				println()
				Console.info(title = "Example", value = "Help <A>")
				Console.info(title = "Example", value = "Help <Archer>")
				null
			}
			input.contains("<A>", ignoreCase = true) || input.contains("<Archer>", ignoreCase = true) -> Archer(teamColour = Colour.RESET)
			input.contains("<E>", ignoreCase = true) || input.contains("<Emperor>", ignoreCase = true) -> Emperor(teamColour = Colour.RESET)
			input.contains("<G>", ignoreCase = true) || input.contains("<General>", ignoreCase = true) -> General(teamColour = Colour.RESET)
			input.contains("<L>", ignoreCase = true) || input.contains("<Lancer>", ignoreCase = true) -> Lancer(teamColour = Colour.RESET)
			input.contains("<M>", ignoreCase = true) || input.contains("<Merchant>", ignoreCase = true) -> Merchant(teamColour = Colour.RESET)
			input.contains("<C>", ignoreCase = true) || input.contains("<Scholar>", ignoreCase = true) -> Scholar(teamColour = Colour.RESET)
			input.contains("<P>", ignoreCase = true) || input.contains("<Spearman>", ignoreCase = true) -> Spearman(teamColour = Colour.RESET)
			input.contains("<T>", ignoreCase = true) || input.contains("<Thief>", ignoreCase = true) -> Thief(teamColour = Colour.RESET)
			else -> null
		}
		if (temp != null) {
			Console.info(title = "Piece", value = temp.javaClass.simpleName)
			Console.info(title = "Symbol", value = temp.symbol)
			Console.info(title = "Max Distance", value = temp.maxDistance.toString())
			Console.info(title = "Valid Directions", value = Arrays.toString(temp.validDirections))
		}
	}
}