package macro303.keschet;

import macro303.board_game.Game;
import macro303.board_game.PlayerBase;
import macro303.board_game.Square;
import macro303.keschet.core.Coordinates;
import macro303.keschet.core.pieces.*;
import macro303.keschet.core.players.Player;
import macro303.keschet.core.players.AutoPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static macro303.keschet.Util.player1Colour;
import static macro303.keschet.Util.player2Colour;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Keschet extends Game {
	private static final Logger LOGGER = LogManager.getLogger(Keschet.class);

	private Keschet() {
		super(new KeschetBoard(10), new KeschetDisplay());
		setPlayers("Joe", "Minnie");
		placePieces();
		playGame();
	}

	@Override
	protected void setPlayers(@NotNull String name1, @NotNull String name2) {
		if (new Random().nextInt(2) == 0) {
			player1 = new AutoPlayer(name1, player1Colour, 1);
			player2 = new AutoPlayer(name2, player2Colour, 2);
		} else {
			player1 = new AutoPlayer(name2, player1Colour, 1);
			player2 = new AutoPlayer(name1, player2Colour, 2);
		}
		display.showMessage(player1.getName() + " is Player " + player1.getPlayerNum(), player1.getColour());
		display.showMessage(player2.getName() + " is Player " + player2.getPlayerNum(), player2.getColour());
	}

	private void placePieces() {
		Player[] players = new Player[]{(Player) player1, (Player) player2};
//		1 Emperor
		for (int i = 0; i < 1; i++)
			for (Player player : players)
				placePiece(player, new Emperor(player.getColour()));
//		1 General
		for (int i = 0; i < 1; i++)
			for (Player player : players)
				placePiece(player, new General(player.getColour()));
//		1 Scholar
		for (int i = 0; i < 1; i++)
			for (Player player : players)
				placePiece(player, new Scholar(player.getColour()));
//		2 Merchant
		for (int i = 0; i < 2; i++)
			for (Player player : players)
				placePiece(player, new Merchant(player.getColour()));
//		3 Thief
		for (int i = 0; i < 3; i++)
			for (Player player : players)
				placePiece(player, new Thief(player.getColour()));
//		4 Lancer
		for (int i = 0; i < 4; i++)
			for (Player player : players)
				placePiece(player, new Lancer(player.getColour()));
//		5 Archer
		for (int i = 0; i < 5; i++)
			for (Player player : players)
				placePiece(player, new Archer(player.getColour()));
//		8 Spearman
		for (int i = 0; i < 8; i++)
			for (Player player : players)
				placePiece(player, new Spearman(player.getColour()));
	}

	private void placePiece(@NotNull Player player, @NotNull Piece piece) {
		if (!(player instanceof AutoPlayer)) {
			((KeschetDisplay) display).draw(true);
			display.showTitle("Place " + piece.getClass().getSimpleName(), player.getColour());
		}
		boolean placed = false;
		do {
			Coordinates selected = player.placePiece(board, piece);
			Square location = board.getSquare(selected.getRow(), selected.getCol());
			if (location != null && location.getPiece() == null && ((player == player1 && selected.getRow() < 3) || (player == player2 && selected.getRow() > 6))) {
				location.setPiece(piece);
				placed = true;
			} else if (location == null) {
				display.showMessage("Must be placed on the board (0-9) => " + selected);
			} else if (location.getPiece() != null) {
				display.showMessage("Must be placed on an empty square => " + location);
			} else if ((player.getPlayerNum() != 1 || selected.getRow() >= 3) && (player.getPlayerNum() != 2 || selected.getRow() <= 6)) {
				display.showMessage("Must be placed within 3 rows on your side => " + selected);
			} else {
				display.showMessage("You did something wrong. Call the Wizard!");
			}
		} while (!placed);
	}

	@Override
	protected void executeTurn(@NotNull PlayerBase player) {
		Player current = (Player) player;
		boolean valid = false;
		do {
			display.draw();
			display.showTitle("Select a Piece", player.getColour());
			Coordinates moveFrom = current.selectPiece(board);
			Square fromLocation = board.getSquare(moveFrom.getRow(), moveFrom.getCol());
			if (fromLocation != null && fromLocation.getPiece() != null && ((Piece) fromLocation.getPiece()).getColour() == current.getColour()) {
				((KeschetDisplay) display).draw(fromLocation);
				display.showTitle("Move " + ((Piece) fromLocation.getPiece()).getClass().getSimpleName(), current.getColour());
				Coordinates moveTo = current.movePieceTo(board, (Piece) fromLocation.getPiece());
				Square toLocation = board.getSquare(moveTo.getRow(), moveTo.getCol());
				if (toLocation != null && (toLocation.getPiece() == null || ((Piece) toLocation.getPiece()).getColour() != current.getColour())) {
					boolean validMovement = Util.validMovement(board, fromLocation, toLocation);
					if (validMovement) {
						Piece taken = null;
						if (toLocation.getPiece() != null && fromLocation.getPiece() instanceof Thief) {
							taken = (Piece) toLocation.getPiece();
						}
						toLocation.setPiece(fromLocation.getPiece());
						fromLocation.setPiece(null);
						valid = true;
						if (taken != null) {
							stealPiece(toLocation, taken, current);
						}
					} else {
						display.showMessage("That is an invalid move try again");
					}
				} else if (toLocation == null) {
					display.showMessage("Must be placed on the board (0-9)");
				} else if (toLocation.getPiece() != null && ((Piece) toLocation.getPiece()).getColour() == current.getColour()) {
					display.showMessage("That's your piece, you can't take your own piece");
				} else {
					display.showMessage("You did something wrong. Call the Wizard!");
				}
			} else if (fromLocation == null) {
				display.showMessage("Must be placed on the board (0-9)");
			} else if (fromLocation.getPiece() == null) {
				display.showMessage("No Piece at that location");
			} else if (((Piece) fromLocation.getPiece()).getColour() != current.getColour()) {
				display.showMessage("That's not your piece, put it back");
			} else {
				display.showMessage("You did something wrong. Call the Wizard!");
			}
		} while (!valid);
	}

	@Override
	@Nullable
	protected PlayerBase checkWinCondition() {
		int player1Count = ((KeschetBoard) board).countPieces((Player) player1);
		boolean player1Emperor = ((KeschetBoard) board).findPiece(Emperor.class, player1.getColour()) != null;
		int player2Count = ((KeschetBoard) board).countPieces((Player) player2);
		boolean player2Emperor = ((KeschetBoard) board).findPiece(Emperor.class, player2.getColour()) != null;
		PlayerBase winner = null;
		if ((player1Count <= 1 || !player1Emperor) && (player2Count <= 1 || !player2Emperor))
			winner = draw;
		if (player1Count <= 1 || !player1Emperor)
			winner = player2;
		if (player2Count <= 1 || !player2Emperor)
			winner = player1;
		if (winner != null)
			display.showTitle(winner.getName() + " Wins", winner.getColour());
		return winner;
	}

	private void stealPiece(@NotNull Square location, @NotNull Piece piece, @NotNull Player player) {
		piece.setColour(player.getColour());
		((KeschetDisplay) display).draw(location);
		display.showTitle("Select location for stolen " + piece.getClass().getSimpleName(), player.getColour());
		boolean valid = false;
		do {
			Coordinates selected = player.placePiece(board, piece);
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
		int row = Math.abs(oldLocation.getY() - newLocation.getY());
		int col = Math.abs(oldLocation.getX() - newLocation.getX());
		return row <= 1 && col <= 1;
	}

	public static void main(@Nullable String... args) {
		LOGGER.info("Initializing Keschet");
		loggerColours();
		new Keschet();
	}

	private static void loggerColours() {
		LOGGER.trace("TRACE is Visible");
		LOGGER.debug("DEBUG is Visible");
		LOGGER.info("INFO is Visible");
		LOGGER.warn("WARN is Visible");
		LOGGER.error("ERROR is Visible");
		LOGGER.fatal("FATAL is Visible");
	}
}
