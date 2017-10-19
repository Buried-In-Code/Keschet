package macro303.base

import macro303.base.pieces.*
import org.apache.logging.log4j.LogManager

object Keschet {
	private val LOGGER = LogManager.getLogger(Keschet::class.java)
	private val team1 = Team(Colour.BLUE)
	private val team2 = Team(Colour.RED)
	private val board = Board()
	private val reader = Reader()

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
		(0 until team1.pieces.size).forEach {
			placePiece(team1.pieces[it])
			placePiece(team2.pieces[it])
		}
	}

	private fun placePiece(selectedPiece: Piece) {
		Console.showTitle(title = "${selectedPiece.teamColour.name}'s Turn", colour = selectedPiece.teamColour)
		var placed = false
		do {
			val selectedLocation = selectLocation(prompt = "Place your ${selectedPiece.javaClass.simpleName}")
			if (selectedLocation != null && selectedPiece.teamColour == team1.colour && selectedLocation.second <= 3 || selectedPiece.teamColour == team2.colour && selectedLocation!!.second >= 8) {
				if (board.getSquare(square = selectedLocation)!!.piece == null) {
					board.getSquare(square = selectedLocation)!!.piece = selectedPiece
					placed = true
				} else
					Console.showError(message = "Must be placed on empty square")
			} else
				Console.showError(message = "Invalid placement. Place within 3 rows on your side")
		} while (!placed)
	}

	private fun executeTurn(team: Team) {
		var success = false
		do {
			Console.showTitle(title = "${team.colour.name}'s Turn", colour = team.colour)
			val startLocation = selectLocation(prompt = "Select Piece")
			if (startLocation != null && board.getSquare(square = startLocation)!!.piece == null)
				Console.showError("No Piece at that square")
			else if (startLocation != null) {
				if (board.getSquare(square = startLocation)!!.piece!!.teamColour == team.colour) {
					val endLocation = selectLocation(prompt = "Select Destination")
					if (endLocation != null && ((board.getSquare(square = startLocation)!!.piece is Merchant && (board.getSquare(square = startLocation)!!.piece as Merchant).validMovement(start = startLocation, end = endLocation, board = board)) || board.getSquare(square = startLocation)!!.piece!!.validMovement(start = startLocation, end = endLocation))) {
						success = if (board.getSquare(square = endLocation)!!.piece == null)
							movePiece(start = startLocation, end = endLocation)
						else
							takePiece(startLocation = startLocation, endLocation = endLocation)
					} else
						Console.showError(message = "Invalid movement")
				} else
					Console.showError(message = "That Piece isn't yours")
			}
		} while (!success)
	}

	private fun takePiece(startLocation: Pair<Int, Int>, endLocation: Pair<Int, Int>): Boolean {
		val selectedPiece = board.getSquare(square = startLocation)!!.piece
		val takenPiece = board.getSquare(square = endLocation)!!.piece
		var complete = false
		when {
			takenPiece!!.teamColour == selectedPiece!!.teamColour -> Console.showError(message = "You can't take your own Pieces")
			board.getAllSurroundingPieces(endLocation).any { piece -> piece is Scholar && piece.teamColour == selectedPiece.teamColour } -> Console.showError(message = "That piece is protected by a nearby Scholar")
			movePiece(start = startLocation, end = endLocation) && selectedPiece is Thief -> {
				var nearby = false
				do {
					val takenLocation = selectLocation(prompt = "Place Stolen piece")
					if (takenLocation != null && board.getSquare(square = takenLocation)!!.piece == null) {
						if (board.getAllSurroundingPieces(squareLocation = takenLocation).contains(selectedPiece)) {
							takenPiece.teamColour = selectedPiece.teamColour
							board.getSquare(square = takenLocation)!!.piece = takenPiece
							nearby = true
						}
					} else
						Console.showError(message = "Must be placed on empty square surrounding your Thief")
				} while (!nearby)
				complete = true
			}
		}
		LOGGER.trace("boolean takePiece(Pair<Int, Int>, Pair<Int, Int>) = $complete")
		return complete
	}

	private fun movePiece(start: Pair<Int, Int>, end: Pair<Int, Int>): Boolean {
		val direction = IBoard.calculateDirection(start, end)
		val distance = Math.abs(IBoard.calculateDistance(start, end))
		var tempRow = start.first
		var tempColumn = start.second
		var notBlocked = true
		for (i in 0 until distance) {
			when (direction) {
				Direction.NORTH -> tempColumn--
				Direction.NORTH_EAST -> {
					tempRow++
					tempColumn--
				}
				Direction.EAST -> tempRow++
				Direction.SOUTH_EAST -> {
					tempRow++
					tempColumn++
				}
				Direction.SOUTH -> tempColumn++
				Direction.SOUTH_WEST -> {
					tempRow--
					tempColumn++
				}
				Direction.WEST -> tempRow--
				Direction.NORTH_WEST -> {
					tempRow--
					tempColumn--
				}
				Direction.INVALID -> notBlocked = false
			}
			if (board.getSquare(square = Pair(tempRow, tempColumn))!!.piece != null && i != distance - 1)
				notBlocked = false
		}
		if (notBlocked) {
			board.getSquare(square = end)!!.piece = board.getSquare(square = start)!!.piece
			board.getSquare(square = start)!!.piece = null
		} else
			Console.showError("Invalid movement")
		LOGGER.trace("boolean movePiece(Pair<Int, Int>, Pair<Int, Int>) = $notBlocked")
		return notBlocked
	}

	private fun selectLocation(prompt: String): Pair<Int, Int>? {
		var locationPair: Pair<Int, Int>? = null
		do {
			try {
				board.draw()
				Console.showMessage(message = prompt)
				val input = reader.readConsole(prompt = "Square (x:y)")
				if (input.contains("help", ignoreCase = true)) {
					Console.helpMenu(input = input)
					return selectLocation(prompt = prompt)
				}
				val location = input.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
				val row = location[0].toInt()
				val column = location[1].toInt()
				locationPair = Pair(row, column)
				if (board.getSquare(square = Pair(row, column)) == null) {
					Console.showError("That square isn't the board")
					locationPair = null
				}
			} catch (ignored: NumberFormatException) {
				Console.showError("Invalid selection")
			} catch (ignored: ArrayIndexOutOfBoundsException) {
				Console.showError("Invalid selection")
			}

		} while (locationPair == null)
		LOGGER.trace("Pair<Int, Int> selectLocation(String) = $locationPair")
		return locationPair
	}

	private fun checkWinCondition(): Boolean {
		val team1Count = board.countPieces(team = team1)
		val team1EmperorAlive = board.pieceStillOnBoard(clazz = Emperor::class.java, teamColour = team1.colour)
		val team2Count = board.countPieces(team = team2)
		val team2EmperorAlive = board.pieceStillOnBoard(clazz = Emperor::class.java, teamColour = team2.colour)
		if (team1Count <= 0 || !team1EmperorAlive)
			Console.showTitle(title = "${team2.colour.name} Team Wins", colour = team2.colour)
		if (team2Count <= 0 || !team2EmperorAlive)
			Console.showTitle(title = "${team1.colour.name} Team Wins", colour = team1.colour)
		val endGame = team1Count == 0 || !team1EmperorAlive || team2Count == 0 || !team2EmperorAlive
		LOGGER.trace("boolean checkWinCondition() = $endGame")
		return endGame
	}
}