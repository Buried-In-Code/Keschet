package macro303.keschet;

import macro303.keschet.pieces.Piece;
import macro303.keschet.pieces.Thief;

import java.util.ArrayList;

public class Keschet {
	private static Team team1 = new Team(ConsoleColour.BLUE);
	private static Team team2 = new Team(ConsoleColour.RED);
	private static Board board = new Board();
	private static Reader reader = new Reader();

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

	private static void placePiece(Piece piece) {
		board.draw();
		Console.showTeamTitle(piece.getTeamColour().name() + "'s Turn", piece.getTeamColour());
		boolean placed = false;
		do {
			Pair<Integer, Integer> square = selectCoords("Place your " + piece.getClass().getSimpleName());
			if ((piece.getTeamColour() == team1.getColour() && square.getRight() <= 3) || (piece.getTeamColour() == team2.getColour() && square.getRight() >= 8)) {
				if (board.getSquare(square).getPiece() == null) {
					board.getSquare(square).setPiece(piece);
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
			board.draw();
			Console.showTeamTitle(team.getColour().name() + "'s Turn", team.getColour());
			Pair<Integer, Integer> start = selectCoords("Select Piece");
			if (board.getSquare(start).getPiece() == null)
				Console.showError("No Piece at those square");
			else {
				if (board.getSquare(start).getPiece().getTeamColour() == team.getColour()) {
					Pair<Integer, Integer> end = selectCoords("Select Destination");
					if (board.getSquare(start).getPiece().validMovement(start, end)) {
						if (board.getSquare(end).getPiece() == null)
							success = movePiece(start, end);
						else
							success = takePiece(start, end);
					} else
						Console.showError("Invalid movement");
				} else
					Console.showError("That Piece isn't yours");
			}
		} while (!success);
	}

	private static boolean takePiece(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Piece selected = board.getSquare(start).getPiece();
		Piece taken = board.getSquare(end).getPiece();
		if (taken.getTeamColour() == selected.getTeamColour()) {
			Console.showError("You can't take your own Pieces");
		} else if (containsScholar(board.getAllSurroundingPieces(end))) {
			Console.showError("That piece is protected by a nearby Scholar");
		} else if (movePiece(start, end) && selected instanceof Thief) {
			boolean nearby = false;
			do {
				Pair<Integer, Integer> newPieceLocation = selectCoords("Place Stolen piece");
				if (board.getSquare(newPieceLocation).getPiece() == null) {
					if (board.getAllSurroundingPieces(newPieceLocation).contains(selected)) {
						taken.setTeamColour(selected.getTeamColour());
						board.getSquare(newPieceLocation).setPiece(taken);
						nearby = true;
					}
				} else {
					Console.showError("Must be placed on empty square");
				}
			} while (!nearby);
			return true;
		}
		return false;
	}

	private static boolean containsScholar(ArrayList<Piece> pieces) {
		return false;
	}

	private static boolean movePiece(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		Direction direction = Board.calculateDirection(start, end);
		int distance = Math.abs(Board.calculateDistance(start, end));
		int tempRow = start.getLeft();
		int tempColumn = start.getRight();
		boolean invalid = false;
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
			} else {
				invalid = true;
			}
			if ((board.getSquare(new Pair<>(tempRow, tempColumn)) != null && i != distance - 1) || invalid) {
				invalid = true;
				break;
			}
		}
		if (invalid) {
			Piece temp = board.getSquare(start).getPiece();
			board.getSquare(start).setPiece(null);
			board.getSquare(end).setPiece(null);
			board.getSquare(start).setPiece(temp);
			return true;
		}
		Console.showError("Invalid movement");
		return false;
	}

	private static Pair<Integer, Integer> selectCoords(String prompt) {
		int row = -1;
		int column = -1;
		do {
			try {
				Console.showMessage(prompt);
				String input = reader.readConsole(prompt = "Square (x:y)");
				if (input.toLowerCase().contains("help")) {
					Console.helpMenu(input);
					return selectCoords(prompt);
				}
				String[] coords = input.split(":");
				row = Integer.valueOf(coords[0]);
				column = Integer.valueOf(coords[1]);
				if (row >= 1 && row <= 10 && column >= 1 && column <= 10)
					return new Pair<>(row, column);
				Console.showError("That square isn't the board");
			} catch (NumberFormatException | IndexOutOfBoundsException ignored) {
				Console.showError("Invalid selection");
			}
		} while (row >= 1 && row <= 10 && column >= 1 && column <= 10);
		return new Pair<>(1, 1);
	}

	private static boolean checkWinCondition() {
		if (board.countPieces(team1) == 0)
			Console.showTeamTitle(team2.getColour().name() + " Team Wins", team2.getColour());
		if (board.countPieces(team2) == 0)
			Console.showTeamTitle(team1.getColour().name() + " Team Wins", team1.getColour());
		return board.countPieces(team1) == 0 || board.countPieces(team2) == 0;
	}
}