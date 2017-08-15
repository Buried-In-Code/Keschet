package macro303.keschet

import macro303.keschet.pieces.*
import macro303.keschet.pieces.Piece.Direction.*
import macro303.keschet.pieces.Piece.Team.BLACK
import macro303.keschet.pieces.Piece.Team.WHITE
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
object Keschet {
	private val reader: Reader = Reader()
	private val blackPieces = arrayOf(Emperor(Piece.Team.BLACK), General(BLACK), Scholar(BLACK), Merchant(BLACK), Merchant(BLACK), Thief(BLACK), Thief(BLACK), Thief(BLACK), Lancer(BLACK), Lancer(BLACK), Lancer(BLACK), Lancer(BLACK), Archer(BLACK), Archer(BLACK), Archer(BLACK), Archer(BLACK), Archer(BLACK), Spearman(BLACK), Spearman(BLACK), Spearman(BLACK), Spearman(BLACK), Spearman(BLACK), Spearman(BLACK), Spearman(BLACK), Spearman(BLACK))
	private var blackBoard: ArrayList<Piece> = ArrayList()
	private val whitePieces = arrayOf(Emperor(WHITE), General(WHITE), Scholar(WHITE), Merchant(WHITE), Merchant(WHITE), Thief(WHITE), Thief(WHITE), Thief(WHITE), Lancer(WHITE), Lancer(WHITE), Lancer(WHITE), Lancer(WHITE), Archer(WHITE), Archer(WHITE), Archer(WHITE), Archer(WHITE), Archer(WHITE), Spearman(WHITE), Spearman(WHITE), Spearman(WHITE), Spearman(WHITE), Spearman(WHITE), Spearman(WHITE), Spearman(WHITE), Spearman(WHITE))
	private var whiteBoard: ArrayList<Piece> = ArrayList()
	private var board: Board = Board()

	private var end = false

	@JvmStatic fun main(args: Array<String>) {
		setup()
		placePieces()
		playGame()
	}

	private fun setup() {
	}

	private fun placePieces() {
		for (i in 0..25) {
			board.print()

			println("Black's turn")
			val pieceB = blackPieces[i]
			val tempB = placePiece(pieceB, BLACK)
			val squareB = board.getLocation(tempB[0] - 1, tempB[1] - 1)
			pieceB.setLocation(squareB)
			blackBoard.add(pieceB)
			squareB.piece = pieceB

			board.print()

			println("White's turn")
			val pieceW = whitePieces[i]
			val tempW = placePiece(pieceW, WHITE)
			val squareW = board.getLocation(tempW[0] - 1, tempW[1] - 1)
			pieceW.setLocation(squareW)
			whiteBoard.add(pieceW)
			squareW.piece = pieceW
		}
	}

	private fun placePiece(piece: Piece, team: Piece.Team): IntArray {
		var temp = getCoords("Select Placement -> " + piece.javaClass.simpleName)
		if (team == BLACK && temp[1] >= 4) {
			println("Invalid Start Location")
			temp = placePiece(piece, BLACK)
		}
		if (team == WHITE && temp[1] <= 7) {
			println("Invalid Start Location")
			temp = placePiece(piece, WHITE)
		}
		val selected = board.getLocation(temp[0] - 1, temp[1] - 1)
		if (selected.piece != null) {
			println((selected.piece as Piece).javaClass.simpleName + " is already in that location")
			temp = placePiece(piece, team)
		}
		return intArrayOf(temp[0], temp[1])
	}

	private fun playGame() {
		while (!end) {
			for (team in Piece.Team.values()) {
				board.print()
				println(team.toString() + "'s Turn")
				turn(team)
				if (end)
					break
			}
		}
	}

	private fun turn(team: Piece.Team) {
		var invalid = true
		while (invalid) {
			invalid = false
			val selected = selectPiece(team)
			val start = board.getLocation(selected.x, selected.y)
			val temp = getCoords("Select Destination")
			val destination = board.getLocation(temp[0] - 1, temp[1] - 1)
			if (destination.piece != null) {
				if ((destination.piece as Piece).team == team) {
					println("You can't take your own piece")
					invalid = true
					continue
				} else if (board.adjacentScholar(destination, team)) {
					println((destination.piece as Piece).team.toString() + "'s " + (destination.piece as Piece).javaClass.simpleName + " is protected")
					invalid = true
					continue
				}
			}
			if (destination.piece == null && selected is Merchant) {
				if (board.adjacentEmperor(destination, team)) {
					board.movePiece(start, destination)
					continue
				}
			}
			val direction = getDirection(start, destination)
			if (!selected.validDirections.contains(direction)) {
				println("Invalid Movement: " + direction + " != " + selected.validDirections)
				invalid = true
				continue
			}
			val distance = getDistance(start, destination, direction)
			if (selected.movement < distance) {
				println("Invalid Movement: " + distance + " > " + selected.movement)
				invalid = true
				continue
			}
			val removedPiece = board.movePiece(start, destination)
			if (removedPiece != null) {
				if (team == BLACK) {
					whiteBoard.remove(removedPiece)
					if (whiteBoard.size <= 1) {
						end = true
						println(BLACK.name + " Wins")
					}
				} else if (team == WHITE) {
					blackBoard.remove(removedPiece)
					if (blackBoard.size <= 1) {
						end = true
						println(WHITE.name + " Wins")
					}
				}
				if (removedPiece is Emperor) {
					end = true
					println(if (team == BLACK) BLACK.name + " Wins" else WHITE.name + " Wins")
				}
			}
			selected.setLocation(destination)
		}
	}

	private fun selectPiece(team: Piece.Team): Piece {
		var selected: Piece? = null
		while (selected == null) {
			val temp = getCoords("Select Piece")
			selected = board.getLocation(temp[0] - 1, temp[1] - 1).piece
			if (selected == null) {
				println("No piece in that location")
			} else if (selected.team != team) {
				println("Not your piece")
				selected = null
			}
		}
		return selected
	}

	private fun getDirection(start: Square, finish: Square): Piece.Direction {
		when {
			Math.abs(finish.x - start.x) == Math.abs(finish.y - start.y) -> return DIAGONAL
			start.x == finish.x && start.y != finish.y -> return VERTICAL
			start.x != finish.x && start.y == finish.y -> return HORIZONTAL
			else -> return DIAGONAL
		}
	}

	private fun getDistance(start: Square, finish: Square, direction: Piece.Direction): Int {
		when (direction) {
			DIAGONAL -> return Math.abs(finish.x - start.x)
			VERTICAL -> return Math.abs(start.y - finish.y)
			HORIZONTAL -> return Math.abs(start.x - finish.x)
			else -> return 0
		}
	}

	private fun getCoords(prompt: String): IntArray {
		val temp: IntArray
		val input = reader.getInput(prompt + " -> X:Y").split(":")
		try {
			temp = intArrayOf(Integer.parseInt(input[0].trim()), Integer.parseInt(input[1].trim()))
		} catch (e: ArrayIndexOutOfBoundsException) {
			println("Invalid Location")
			return getCoords(prompt)
		} catch (e: NumberFormatException) {
			println("Invalid Location")
			return getCoords(prompt)
		}

		if (temp[1] > 10 || temp[0] > 10 || temp[1] < 1 || temp[0] < 1) {
			println("Invalid Location")
			return getCoords(prompt)
		}
		return temp
	}
}
