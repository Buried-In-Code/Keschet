package macro303.keschet

import macro303.keschet.pieces.*
import macro303.keschet.pieces.Piece.Team.BLACK
import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
class Board {
	private val LOGGER: Logger = LoggerFactory.getLogger(Board::class.java)

	private val board = ArrayList<ArrayList<Square>>()
	private var black: Team = Team(Colour.BLACK)

	init {
		(0..9).mapTo(board) { i -> (0..9).mapTo(ArrayList<Square>()) { Square(i, it) } }
	}

	internal fun getLocation(x: Int, y: Int): Square {
		return board[x][y]
	}

	internal fun print() {
		(0..board.size + 1 - 1).forEach { i ->
			(0..board.size + 1 - 1).forEach { j ->
				when {
					i == 0 -> print((if (j == 0) "" else " ") + j + " ")
					j == 0 -> print(i.toString() + if (i == 10) "" else " ")
					else -> {
						val piece = getLocation(j - 1, i - 1).piece
						print(if (piece == null) " ~ " else (if (piece.team === BLACK) " B" else " W") + piece.javaClass.simpleName.substring(0, 1))
					}
				}
			}
			println()
		}
	}

	internal fun movePiece(start: Square, finish: Square): Piece? {
		val movingPiece = start.piece
		val finishPiece = finish.piece
		start.piece = null
		finish.piece = movingPiece
		return finishPiece
	}

	internal fun adjacentScholar(destination: Square, team: Piece.Team): Boolean {
		when {
			destination.x > 0 && getLocation(destination.x - 1, destination.y).piece is Scholar && getLocation(destination.x - 1, destination.y).piece?.team != team -> return true
			destination.x < 9 && getLocation(destination.x + 1, destination.y).piece is Scholar && getLocation(destination.x + 1, destination.y).piece?.team != team -> return true
			destination.y > 0 && getLocation(destination.x, destination.y - 1).piece is Scholar && getLocation(destination.x, destination.y - 1).piece?.team != team -> return true
			destination.y < 9 && getLocation(destination.x, destination.y + 1).piece is Scholar && getLocation(destination.x, destination.y + 1).piece?.team != team -> return true
			else -> return false
		}
	}

	internal fun adjacentEmperor(destination: Square, team: Piece.Team): Boolean {
		when {
			destination.x > 0 && getLocation(destination.x - 1, destination.y).piece is Emperor && getLocation(destination.x - 1, destination.y).piece?.team == team -> return true
			destination.x > 0 && destination.y > 0 && getLocation(destination.x - 1, destination.y - 1).piece is Emperor && getLocation(destination.x - 1, destination.y - 1).piece?.team == team -> return true
			destination.y > 0 && getLocation(destination.x, destination.y - 1).piece is Emperor && getLocation(destination.x, destination.y - 1).piece?.team == team -> return true
			destination.x < 9 && destination.y > 0 && getLocation(destination.x + 1, destination.y - 1).piece is Emperor && getLocation(destination.x + 1, destination.y - 1).piece?.team == team -> return true
			destination.x < 9 && getLocation(destination.x + 1, destination.y).piece is Emperor && getLocation(destination.x + 1, destination.y).piece?.team == team -> return true
			destination.x < 9 && destination.y < 9 && getLocation(destination.x + 1, destination.y + 1).piece is Emperor && getLocation(destination.x + 1, destination.y + 1).piece?.team == team -> return true
			destination.y < 9 && getLocation(destination.x, destination.y + 1).piece is Emperor && getLocation(destination.x, destination.y + 1).piece?.team == team -> return true
			destination.x > 0 && destination.y < 9 && getLocation(destination.x - 1, destination.y + 1).piece is Emperor && getLocation(destination.x - 1, destination.y + 1).piece?.team == team -> return true
			else -> return false
		}
	}

	override fun toString(): String {
		return "Board(board=$board)"
	}
}
