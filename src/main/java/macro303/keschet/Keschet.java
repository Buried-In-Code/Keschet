package macro303.keschet;

import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.pieces.*;
import macro303.keschet.players.Player;
import macro303.keschet.players.auto.AutoPlayer;
import macro303.keschet.players.console.ConsolePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Keschet {
	private static final Logger LOGGER = LogManager.getLogger(Keschet.class);
	private static Player player1;
	private static Player player2;
	private static KeschetBoard board;
	private static KeschetDisplay display;

	public static void main(@Nullable String... args) {
		board = new KeschetBoard(10);
		display = new KeschetDisplay(board);
		setPlayers();
		placePieces();
		playGame();
		display.draw(true, true);
	}

	private static void setPlayers() {
		if (new Random().nextInt(2) == 0) {
			player1 = new AutoPlayer("Jonah", Util.player1Colour);
			player2 = new AutoPlayer("Hanoj", Util.player2Colour);
		} else {
			player1 = new AutoPlayer("Hanoj", Util.player1Colour);
			player2 = new AutoPlayer("Jonah", Util.player2Colour);
		}
		LOGGER.info(player1.getName() + " is Player 1: " + player1.getTeamColour().name());
		LOGGER.info(player2.getName() + " is Player 2: " + player2.getTeamColour().name());
	}

	private static void placePieces() {
//		1 Emperor
		for (int i = 0; i < 1; i++) {
			placePiece(player1, new Emperor(player1.getTeamColour()));
			placePiece(player2, new Emperor(player2.getTeamColour()));
		}
//		1 General
		for (int i = 0; i < 1; i++) {
			placePiece(player1, new General(player1.getTeamColour()));
			placePiece(player2, new General(player2.getTeamColour()));
		}
//		1 Scholar
		for (int i = 0; i < 1; i++) {
			placePiece(player1, new Scholar(player1.getTeamColour()));
			placePiece(player2, new Scholar(player2.getTeamColour()));
		}
//		2 Merchant
		for (int i = 0; i < 2; i++) {
			placePiece(player1, new Merchant(player1.getTeamColour()));
			placePiece(player2, new Merchant(player2.getTeamColour()));
		}
//		3 Thief
		for (int i = 0; i < 3; i++) {
			placePiece(player1, new Thief(player1.getTeamColour()));
			placePiece(player2, new Thief(player2.getTeamColour()));
		}
//		4 Lancer
		for (int i = 0; i < 4; i++) {
			placePiece(player1, new Lancer(player1.getTeamColour()));
			placePiece(player2, new Lancer(player2.getTeamColour()));
		}
//		5 Archer
		for (int i = 0; i < 5; i++) {
			placePiece(player1, new Archer(player1.getTeamColour()));
			placePiece(player2, new Archer(player2.getTeamColour()));
		}
//		8 Spearman
		for (int i = 0; i < 8; i++) {
			placePiece(player1, new Spearman(player1.getTeamColour()));
			placePiece(player2, new Spearman(player2.getTeamColour()));
		}
	}

	private static void placePiece(@NotNull Player player, @NotNull Piece piece) {
		display.draw(true);
		display.showTitle("Place " + piece.getClass().getSimpleName(), player.getTeamColour());
		boolean placed = false;
		do {
			Coordinates selected = player.placePiece(board, piece);
			Square location = board.getSquare(selected);
			if (location != null && location.getItem() == null && ((player == player1 && selected.getRow() < 3) || (player == player2 && selected.getRow() > 6))) {
				location.setItem(piece);
				placed = true;
			} else if (location == null) {
				display.showWarning("Must be placed on the board (0-9)");
			} else if (location.getItem() != null) {
				display.showWarning("Must be placed on an empty square");
			} else if ((player != player1 || selected.getRow() >= 3) && (player != player2 || selected.getRow() <= 6)) {
				display.showWarning("Must be placed within 3 rows on your side");
			} else {
				display.showError("You did something wrong. Call the Wizard!");
			}
		} while (!placed);
	}

	private static void playGame() {
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

	private static void executeTurn(@NotNull Player player) {
		display.draw(true, true);
		display.showTitle("Select a Piece", player.getTeamColour());
		boolean valid = false;
		do {
			Coordinates moveFrom = player.selectPiece(board);
			Square fromLocation = board.getSquare(moveFrom);
			if (fromLocation != null && fromLocation.getItem() != null && ((Piece) fromLocation.getItem()).getTeamColour() == player.getTeamColour()) {
				display.draw(fromLocation);
				display.showTitle("Move " + ((Piece) fromLocation.getItem()).getClass().getSimpleName(), player.getTeamColour());
				Coordinates moveTo = player.movePieceTo(board, (Piece) fromLocation.getItem());
				Square toLocation = board.getSquare(moveTo);
				if (toLocation != null && (toLocation.getItem() == null || ((Piece) toLocation.getItem()).getTeamColour() != player.getTeamColour())) {
					boolean validMovement = Util.validMovement(board, fromLocation, toLocation);
					if (validMovement) {
						Piece taken = null;
						if (toLocation.getItem() != null && fromLocation.getItem().getClass() == Thief.class) {
							taken = (Piece) toLocation.getItem();
						}
						toLocation.setItem(fromLocation.getItem());
						fromLocation.setItem(null);
						valid = true;
						if (taken != null) {
							stealPiece(toLocation, taken, player);
						}
					} else {
						display.showWarning("That is an invalid move try again");
					}
				} else if (toLocation == null) {
					display.showWarning("Must be placed on the board (0-9)");
				} else if (toLocation.getItem() != null && ((Piece) toLocation.getItem()).getTeamColour() == player.getTeamColour()) {
					display.showWarning("That's your piece, you can't take your own piece");
				} else {
					display.showError("You did something wrong. Call the Wizard!");
				}
			} else if (fromLocation == null) {
				display.showWarning("Must be placed on the board (0-9)");
			} else if (fromLocation.getItem() == null) {
				display.showWarning("No Piece at that location");
			} else if (((Piece) fromLocation.getItem()).getTeamColour() != player.getTeamColour()) {
				display.showWarning("That's not your piece, put it back");
			} else {
				display.showError("You did something wrong. Call the Wizard!");
			}
		} while (!valid);
	}

	private static void stealPiece(@NotNull Square location, @NotNull Piece piece, @NotNull Player player) {
		piece.setTeamColour(player.getTeamColour());
		display.draw(location);
		display.showTitle("Select location for stolen " + piece.getClass().getSimpleName(), player.getTeamColour());
		boolean valid = false;
		do {
			Square newLocation = board.getSquare(player.placePiece(board, piece));
			if (newLocation != null && newLocation.getItem() == null) {
				valid = nextTo(newLocation, location);
				if (valid) {
					newLocation.setItem(piece);
				} else {
					display.showWarning("Must be placed next to thief");
				}
			}
		} while (!valid);
	}

	private static boolean nextTo(@NotNull Square newLocation, @NotNull Square oldLocation) {
		int row = Math.abs(oldLocation.getCoordinates().getRow() - newLocation.getCoordinates().getRow());
		int col = Math.abs(oldLocation.getCoordinates().getCol() - newLocation.getCoordinates().getCol());
		return row <= 1 && col <= 1;
	}

	private static boolean checkWinCondition() {
		int player1Count = board.countPieces(player1);
		boolean player1Emperor = board.findPiece(Emperor.class, player1.getTeamColour()) != null;
		int player2Count = board.countPieces(player2);
		boolean player2Emperor = board.findPiece(Emperor.class, player2.getTeamColour()) != null;
		if (player1Count <= 1 || !player1Emperor)
			display.showTitle(player2.getName() + " Wins", player2.getTeamColour());
		if (player2Count <= 1 || !player2Emperor)
			display.showTitle(player1.getName() + " Wins", player1.getTeamColour());
		return player1Count <= 1 || !player1Emperor || player2Count <= 1 || !player2Emperor;
	}
}
