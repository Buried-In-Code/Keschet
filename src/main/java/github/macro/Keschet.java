package github.macro;

import github.macro.console.Colour;
import github.macro.console.Console;
import github.macro.pieces.*;
import github.macro.players.AutoPlayer;
import github.macro.players.ConsolePlayer;
import github.macro.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Keschet {
	private static final Logger LOGGER = LogManager.getLogger(Keschet.class);
	private final GameDisplay display;
	private final GameBoard board;
	private final Player player1;
	private final Player player2;

	private Keschet(@Nullable String player1name, @Nullable String player2name) {
		this.board = new GameBoard();
		this.display = new GameDisplay(this.board);
		player1 = setPlayer(player1name == null, Colour.YELLOW, 1);
		player2 = setPlayer(player2name == null, Colour.RED, 2);
		placePieces();
		while (checkWinCondition() == null) {
			executeTurn(player1);
			if (checkWinCondition() == null)
				executeTurn(player2);
		}
	}

	public static void main(@Nullable String... args) {
		LOGGER.info("Initializing Keschet");
		checkLogLevels();
		new Keschet(null, null);
	}

	private static void checkLogLevels() {
		LOGGER.trace("TRACE is Visible");
		LOGGER.debug("DEBUG is Visible");
		LOGGER.info("INFO is Visible");
		LOGGER.warn("WARN is Visible");
		LOGGER.error("ERROR is Visible");
		LOGGER.fatal("FATAL is Visible");
	}

	@NotNull
	private Player setPlayer(boolean isAuto, @NotNull Colour colour, int number) {
		Player newPlayer;
		if (isAuto)
			newPlayer = new AutoPlayer("Automated Player", colour, number);
		else {
			var name = Console.displayPrompt("Player name");
			newPlayer = new ConsolePlayer(name, colour, number);
		}
		display.showMessage(String.format("%s is Player %s", newPlayer.getName(), newPlayer.getPlayerNum()), newPlayer.getColour());
		return newPlayer;
	}

	private void placePieces() {
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

	private void placePiece(@NotNull Player player, @NotNull Piece piece) {
		display.draw();
		display.showMessage(String.format("Placing %s", piece.getName()), player.getColour());
		boolean placed = false;
		do {
			Coordinate selected = player.placePiece(board, piece);
			Square location = board.getSquare(selected.getRow(), selected.getCol());
			if (location == null)
				display.showMessage(String.format("Must be placed on the board (0-9) => %s", selected));
			else if (location.getPiece() != null)
				display.showMessage(String.format("Must be placed on an empty square => %s", location));
			else if ((player.getPlayerNum() != 1 || selected.getRow() >= 3) && (player.getPlayerNum() != 2 || selected.getRow() <= 6))
				display.showMessage(String.format("Must be placed within 3 rows on your side => %s", selected));
			else {
				location.setPiece(piece);
				placed = true;
			}
		} while (!placed);
	}

	protected void executeTurn(@NotNull Player player) {
		boolean valid = false;
		do {
			display.draw();
			display.showTitle("Select a Piece", player.getColour());
			Coordinate moveFrom = player.selectPiece(board);
			Square fromLocation = board.getSquare(moveFrom.getRow(), moveFrom.getCol());
			if (fromLocation != null && fromLocation.getPiece() != null && fromLocation.getPiece().getColour() == player.getColour()) {
				display.draw(fromLocation);
				display.showTitle(String.format("Move %s", fromLocation.getPiece().getName()), player.getColour());
				Coordinate moveTo = player.movePieceTo(board, fromLocation.getPiece());
				Square toLocation = board.getSquare(moveTo.getRow(), moveTo.getCol());
				if (toLocation != null && (toLocation.getPiece() == null || toLocation.getPiece().getColour() != player.getColour())) {
					boolean validMovement = Util.validMovement(board, fromLocation, toLocation);
					if (validMovement) {
						Piece taken = null;
						if (toLocation.getPiece() != null && fromLocation.getPiece() instanceof Thief) {
							taken = toLocation.getPiece();
						}
						toLocation.setPiece(fromLocation.getPiece());
						fromLocation.setPiece(null);
						valid = true;
						if (taken != null) {
							stealPiece(toLocation, taken, player);
						}
					} else {
						display.showMessage("That is an invalid move try again");
					}
				} else if (toLocation == null) {
					display.showMessage("Must be placed on the board (0-9)");
				} else if (toLocation.getPiece() != null && toLocation.getPiece().getColour() == player.getColour()) {
					display.showMessage("That's your piece, you can't take your own piece");
				} else {
					display.showMessage("You did something wrong. Call the Wizard!");
				}
			} else if (fromLocation == null) {
				display.showMessage("Must be placed on the board (0-9)");
			} else if (fromLocation.getPiece() == null) {
				display.showMessage("No Piece at that location");
			} else if (fromLocation.getPiece().getColour() != player.getColour()) {
				display.showMessage("That's not your piece, put it back");
			} else {
				display.showMessage("You did something wrong. Call the Wizard!");
			}
		} while (!valid);
	}

	@Nullable
	protected Player checkWinCondition() {
		int player1Count = board.countPieces(player1);
		boolean player1Emperor = board.findPiece(Emperor.class, player1.getColour()) != null;
		int player2Count = board.countPieces(player2);
		boolean player2Emperor = board.findPiece(Emperor.class, player2.getColour()) != null;
		Player winner = null;
		if (player1Count <= 1 || !player1Emperor)
			winner = player2;
		if (player2Count <= 1 || !player2Emperor)
			winner = player1;
		if (winner != null)
			display.showTitle(String.format("%s Wins", winner.getName()), winner.getColour());
		return winner;
	}

	private void stealPiece(@NotNull Square location, @NotNull Piece piece, @NotNull Player player) {
		piece.setColour(player.getColour());
		display.draw(location);
		display.showTitle(String.format("Select location for stolen %s", piece.getName()), player.getColour());
		boolean valid = false;
		do {
			Coordinate selected = player.placePiece(board, piece);
			Square newLocation = board.getSquare(selected.getRow(), selected.getCol());
			if (newLocation != null && newLocation.getPiece() == null) {
				valid = nextTo(newLocation, location);
				if (valid) {
					newLocation.setPiece(piece);
				} else {
					display.showMessage("Must be placed next to thief");
				}
			}
		} while (!valid);
	}

	private boolean nextTo(@NotNull Square newLocation, @NotNull Square oldLocation) {
		int row = Math.abs(oldLocation.getCoord().getRow() - newLocation.getCoord().getRow());
		int col = Math.abs(oldLocation.getCoord().getCol() - newLocation.getCoord().getCol());
		return row <= 1 && col <= 1;
	}
}
