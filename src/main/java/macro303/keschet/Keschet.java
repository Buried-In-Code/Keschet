package macro303.keschet;

import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import macro303.keschet.pieces.*;
import macro303.keschet.players.Player;
import macro303.keschet.players.console.ConsolePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Keschet {
	private static final Logger LOGGER = LogManager.getLogger(Keschet.class);
	private static Player player1;
	private static Player player2;
	private static Board board;

	public static void main(String... args) {
		board = new Board();
		player1 = new ConsolePlayer(Colour.BLUE);
		player2 = new ConsolePlayer(Colour.RED);
		placePieces();
		boolean finished;
		do {
			executeTurn(player1);
			finished = checkWinCondition();
			if (!finished) {
				executeTurn(player2);
				finished = checkWinCondition();
			}
		} while (!finished);
	}

	private static void placePieces() {
//		1 Emperor
		for (int i = 0; i < 1; i++) {
			placePiece(player1, new Emperor(player1.getColour()));
			placePiece(player2, new Emperor(player2.getColour()));
		}
//		1 General
		for (int i = 0; i < 1; i++) {
			placePiece(player1, new General(player1.getColour()));
			placePiece(player2, new General(player2.getColour()));
		}
//		1 Scholar
		for (int i = 0; i < 1; i++) {
			placePiece(player1, new Scholar(player1.getColour()));
			placePiece(player2, new Scholar(player2.getColour()));
		}
//		2 Merchant
		for (int i = 0; i < 2; i++) {
			placePiece(player1, new Merchant(player1.getColour()));
			placePiece(player2, new Merchant(player2.getColour()));
		}
//		3 Thief
		for (int i = 0; i < 3; i++) {
			placePiece(player1, new Thief(player1.getColour()));
			placePiece(player2, new Thief(player2.getColour()));
		}
//		4 Lancer
		for (int i = 0; i < 4; i++) {
			placePiece(player1, new Lancer(player1.getColour()));
			placePiece(player2, new Lancer(player2.getColour()));
		}
//		5 Archer
		for (int i = 0; i < 5; i++) {
			placePiece(player1, new Archer(player1.getColour()));
			placePiece(player2, new Archer(player2.getColour()));
		}
//		8 Spearman
		for (int i = 0; i < 8; i++) {
			placePiece(player1, new Spearman(player1.getColour()));
			placePiece(player2, new Spearman(player2.getColour()));
		}
	}

	private static void placePiece(@NotNull Player player, @NotNull Piece piece) {
		player.drawBoard(board, true);
		boolean placed = false;
		do {
			Coordinates selected = player.placePiece(board, piece);
			Square location = board.getSquare(selected);
			if (location != null && location.getPiece() == null && ((player == player1 && selected.getRow() < 3) || (player == player2 && selected.getRow() > 6))) {
				location.setPiece(piece);
				placed = true;
			} else if (location == null) {
				player.showInfo("Must be placed on the board (0-9)");
			} else if (location.getPiece() != null) {
				player.showInfo("Must be placed on an empty square");
			} else if ((player != player1 || selected.getRow() >= 3) && (player != player2 || selected.getRow() <= 6)) {
				player.showInfo("Must be placed within 3 rows on your side");
			} else {
				player.showWarning("You did something wrong. Call the Wizard!");
			}
		} while (!placed);
	}

	private static void executeTurn(@NotNull Player player) {
		player.drawBoard(board, false);
		boolean selected = false;
		do {
			Coordinates moveFrom = player.selectPiece(board);
			Square location = board.getSquare(moveFrom);
			if (location != null && location.getPiece() != null && location.getPiece().getTeamColour() == player.getColour()) {
				Coordinates moveTo = player.movePieceTo(board, location.getPiece());
				boolean validMovement = Util.validMovement(location.getPiece(), Util.calculateDirection(moveFrom, moveTo), Util.calculateDistance(moveFrom, moveTo));
				if (validMovement) {
					selected = true;
				} else {
					player.showInfo("That is an invalid move try again");
				}
			} else if (location == null) {
				player.showInfo("Must be placed on the board (0-9)");
			} else if (location.getPiece() == null) {
				player.showInfo("No Piece at that location");
			} else if (location.getPiece().getTeamColour() != player.getColour()) {
				player.showInfo("That's not your piece, put it back");
			} else {
				player.showWarning("You did something wrong. Call the Wizard!");
			}
		} while (!selected);
	}

	private static boolean checkWinCondition() {
		int player1Count = board.countPieces(player1);
		boolean player1Emperor = board.findPiece(Emperor.class, player1) != null;
		int player2Count = board.countPieces(player2);
		boolean player2Emperor = board.findPiece(Emperor.class, player2) != null;
		if (player1Count <= 1 || !player1Emperor)
			player2.showInfo("Player 2 Wins");
		if (player2Count <= 1 || !player2Emperor)
			player2.showInfo("Player 1 Wins");
		return player1Count <= 1 || !player1Emperor || player2Count <= 1 || !player2Emperor;
	}
}
