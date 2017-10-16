package macro303.keschet;

import macro303.keschet.pieces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Keschet {
	private static final Logger LOGGER = LogManager.getLogger(Keschet.class);
	private static Team team1 = new Team(Console.Colour.BLUE);
	private static Team team2 = new Team(Console.Colour.RED);
	private static Board board = new Board();
	private static Reader reader = new Reader();

	public static Board getBoard() {
		return board;
	}

	public static void main(String[] args) {
		Console.showRules();
		placePieces();
		boolean finished;
		do {
			executeTurn(team1);
			finished = checkWinCondition();
			if (!finished) {
				executeTurn(team2);
				finished = checkWinCondition();
			}
		} while (!finished);
	}

	private static void placePieces() {
		for (int i = 0; i < team1.getPieces().size(); i++) {
			placePiece(team1.getPieces().get(i));
			placePiece(team2.getPieces().get(i));
		}
	}

	private static void placePiece(Piece selectedPiece) {
		Console.showTeamTitle(selectedPiece.getTeamColour().name() + "'s Turn", selectedPiece.getTeamColour());
		boolean placed = false;
		do {
			Pair<Integer, Integer> selectedLocation = selectLocation("Place your " + selectedPiece.getClass().getSimpleName());
			if (selectedLocation != null && (selectedPiece.getTeamColour() == team1.getColour() && selectedLocation.getY() <= 3) || (selectedPiece.getTeamColour() == team2.getColour() && selectedLocation.getY() >= 8)) {
				if (board.getSquare(selectedLocation).getPiece() == null) {
					board.getSquare(selectedLocation).setPiece(selectedPiece);
					placed = true;
				} else
					Console.showError("Must be placed on empty square");
			} else
				Console.showError("Invalid placement. Place within 3 rows on your side");
		} while (!placed);
	}

	private static void executeTurn(Team team) {
		boolean success = false;
		do {
			Console.showTeamTitle(team.getColour().name() + "'s Turn", team.getColour());
			Pair<Integer, Integer> startLocation = selectLocation("Select Piece");
			if (startLocation != null && board.getSquare(startLocation).getPiece() == null)
				Console.showError("No Piece at that square");
			else {
				if (board.getSquare(startLocation).getPiece().getTeamColour() == team.getColour()) {
					Pair<Integer, Integer> endLocation = selectLocation("Select Destination");
					if (endLocation != null && board.getSquare(startLocation).getPiece().validMovement(startLocation, endLocation)) {
						if (board.getSquare(endLocation).getPiece() == null)
							success = movePiece(startLocation, endLocation);
						else
							success = takePiece(startLocation, endLocation);
					} else
						Console.showError("Invalid movement");
				} else
					Console.showError("That Piece isn't yours");
			}
		} while (!success);
	}

	private static boolean takePiece(Pair<Integer, Integer> startLocation, Pair<Integer, Integer> endLocation) {
		Piece selectedPiece = board.getSquare(startLocation).getPiece();
		Piece takenPiece = board.getSquare(endLocation).getPiece();
		boolean complete = false;
		if (takenPiece.getTeamColour() == selectedPiece.getTeamColour()) {
			Console.showError("You can't take your own Pieces");
		} else if (board.getAllSurroundingPieces(endLocation).stream().anyMatch(piece -> piece instanceof Scholar && piece.getTeamColour() == selectedPiece.getTeamColour())) {
			Console.showError("That piece is protected by a nearby Scholar");
		} else if (movePiece(startLocation, endLocation) && selectedPiece instanceof Thief) {
			boolean nearby = false;
			do {
				Pair<Integer, Integer> takenLocation = selectLocation("Place Stolen piece");
				if (takenLocation != null && board.getSquare(takenLocation).getPiece() == null) {
					if (board.getAllSurroundingPieces(takenLocation).contains(selectedPiece)) {
						takenPiece.setTeamColour(selectedPiece.getTeamColour());
						board.getSquare(takenLocation).setPiece(takenPiece);
						nearby = true;
					}
				} else
					Console.showError("Must be placed on empty square surrounding your Thief");
			} while (!nearby);
			complete = true;
		}
		LOGGER.trace("boolean takePiece(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + complete);
		return complete;
	}

	private static boolean movePiece(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Direction direction = Board.calculateDirection(start, end);
		int distance = Math.abs(Board.calculateDistance(start, end));
		int tempRow = start.getX();
		int tempColumn = start.getY();
		boolean notBlocked = true;
		for (int i = 0; i < distance; i++) {
			if (direction == Direction.NORTH) {
				tempColumn--;
			} else if (direction == Direction.NORTH_EAST) {
				tempRow++;
				tempColumn--;
			} else if (direction == Direction.EAST) {
				tempRow++;
			} else if (direction == Direction.SOUTH_EAST) {
				tempRow++;
				tempColumn++;
			} else if (direction == Direction.SOUTH) {
				tempColumn++;
			} else if (direction == Direction.SOUTH_WEST) {
				tempRow--;
				tempColumn++;
			} else if (direction == Direction.WEST) {
				tempRow--;
			} else if (direction == Direction.NORTH_WEST) {
				tempRow--;
				tempColumn--;
			}
			if (board.getSquare(new Pair<>(tempRow, tempColumn)).getPiece() != null && i != distance - 1)
				notBlocked = false;
		}
		if (notBlocked) {
			board.getSquare(end).setPiece(board.getSquare(start).getPiece());
			board.getSquare(start).setPiece(null);
		} else
			Console.showError("Invalid movement");
		LOGGER.trace("boolean movePiece(Pair<Integer, Integer>, Pair<Integer, Integer>) = " + notBlocked);
		return notBlocked;
	}

	private static Pair<Integer, Integer> selectLocation(String prompt) {
		Pair<Integer, Integer> locationPair = null;
		do {
			try {
				board.draw();
				Console.showMessage(prompt);
				String input = reader.readConsole("Square (x:y)");
				if (input.toLowerCase().contains("help")) {
					Console.helpMenu(input);
					return selectLocation(prompt);
				}
				String[] location = input.split(":");
				int row = Integer.valueOf(location[0]);
				int column = Integer.valueOf(location[1]);
				locationPair = new Pair<>(row, column);
				if (board.getSquare(new Pair<>(row, column)) == null) {
					Console.showError("That square isn't the board");
					locationPair = null;
				}
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
				Console.showError("Invalid selection");
			}
		} while (locationPair == null);
		LOGGER.trace("Pair<Integer, Integer> selectLocation(String) = " + locationPair);
		return locationPair;
	}

	private static boolean checkWinCondition() {
		int team1Count = board.countPieces(team1);
		boolean team1EmperorAlive = board.pieceStillOnBoard(Emperor.class, team1.getColour());
		int team2Count = board.countPieces(team2);
		boolean team2EmperorAlive = board.pieceStillOnBoard(Emperor.class, team2.getColour());
		if (team1Count <= 0 || !team1EmperorAlive)
			Console.showTeamTitle(team2.getColour().name() + " Team Wins", team2.getColour());
		if (team2Count <= 0 || !team2EmperorAlive)
			Console.showTeamTitle(team1.getColour().name() + " Team Wins", team1.getColour());
		boolean endGame = team1Count == 0 || !team1EmperorAlive || team2Count == 0 || !team2EmperorAlive;
		LOGGER.trace("boolean checkWinCondition() = " + endGame);
		return endGame;
	}
}