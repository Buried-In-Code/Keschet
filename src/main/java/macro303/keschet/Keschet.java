package macro303.keschet;

import macro303.board_game.Coordinates;
import macro303.board_game.Game;
import macro303.board_game.Square;
import macro303.board_game.Team;
import macro303.keschet.pieces.*;
import macro303.keschet.players.Player;
import macro303.keschet.players.auto.AutoPlayer;
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
		setPlayers("Jonah", "Hanoj");
		placePieces();
		playGame();
	}

	private void placePieces() {
		Player[] players = new Player[]{(Player) player1, (Player) player2};
//		1 Emperor
		for (int i = 0; i < 1; i++)
			for (Player player : players)
				placePiece(player, new Emperor(player.getTeamColour()));
//		1 General
		for (int i = 0; i < 1; i++)
			for (Player player : players)
				placePiece(player, new General(player.getTeamColour()));
//		1 Scholar
		for (int i = 0; i < 1; i++)
			for (Player player : players)
				placePiece(player, new Scholar(player.getTeamColour()));
//		2 Merchant
		for (int i = 0; i < 2; i++)
			for (Player player : players)
				placePiece(player, new Merchant(player.getTeamColour()));
//		3 Thief
		for (int i = 0; i < 3; i++)
			for (Player player : players)
				placePiece(player, new Thief(player.getTeamColour()));
//		4 Lancer
		for (int i = 0; i < 4; i++)
			for (Player player : players)
				placePiece(player, new Lancer(player.getTeamColour()));
//		5 Archer
		for (int i = 0; i < 5; i++)
			for (Player player : players)
				placePiece(player, new Archer(player.getTeamColour()));
//		8 Spearman
		for (int i = 0; i < 8; i++)
			for (Player player : players)
				placePiece(player, new Spearman(player.getTeamColour()));
	}

	private void placePiece(@NotNull Player player, @NotNull Piece piece) {
		((KeschetDisplay) display).draw(true);
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

	public static void main(@Nullable String... args) {
		new Keschet();
	}

	@Override
	@Nullable
	protected Team checkWinCondition() {
		int player1Count = ((KeschetBoard) board).countPieces((Player) player1);
		boolean player1Emperor = ((KeschetBoard) board).findPiece(Emperor.class, player1.getTeamColour()) != null;
		int player2Count = ((KeschetBoard) board).countPieces((Player) player2);
		boolean player2Emperor = ((KeschetBoard) board).findPiece(Emperor.class, player2.getTeamColour()) != null;
		Team winner = null;
		if ((player1Count <= 1 || !player1Emperor) && (player2Count <= 1 || !player2Emperor))
			winner = draw;
		if (player1Count <= 1 || !player1Emperor)
			winner = player2;
		if (player2Count <= 1 || !player2Emperor)
			winner = player1;
		if (winner != null)
			display.showTitle(winner.getName() + " Wins", winner.getTeamColour());
		return winner;
	}

	@Override
	protected void executeTurn(@NotNull Team player) {
		display.draw();
		display.showTitle("Select a Piece", player.getTeamColour());
		Player current = (Player) player;
		boolean valid = false;
		do {
			Coordinates moveFrom = current.selectPiece(board);
			Square fromLocation = board.getSquare(moveFrom);
			if (fromLocation != null && fromLocation.getItem() != null && ((Piece) fromLocation.getItem()).getTeamColour() == current.getTeamColour()) {
				((KeschetDisplay) display).draw(fromLocation);
				display.showTitle("Move " + ((Piece) fromLocation.getItem()).getClass().getSimpleName(), current.getTeamColour());
				Coordinates moveTo = current.movePieceTo(board, (Piece) fromLocation.getItem());
				Square toLocation = board.getSquare(moveTo);
				if (toLocation != null && (toLocation.getItem() == null || ((Piece) toLocation.getItem()).getTeamColour() != current.getTeamColour())) {
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
							stealPiece(toLocation, taken, current);
						}
					} else {
						display.showWarning("That is an invalid move try again");
					}
				} else if (toLocation == null) {
					display.showWarning("Must be placed on the board (0-9)");
				} else if (toLocation.getItem() != null && ((Piece) toLocation.getItem()).getTeamColour() == current.getTeamColour()) {
					display.showWarning("That's your piece, you can't take your own piece");
				} else {
					display.showError("You did something wrong. Call the Wizard!");
				}
			} else if (fromLocation == null) {
				display.showWarning("Must be placed on the board (0-9)");
			} else if (fromLocation.getItem() == null) {
				display.showWarning("No Piece at that location");
			} else if (((Piece) fromLocation.getItem()).getTeamColour() != current.getTeamColour()) {
				display.showWarning("That's not your piece, put it back");
			} else {
				display.showError("You did something wrong. Call the Wizard!");
			}
		} while (!valid);
	}

	@Override
	protected void setPlayers(@NotNull String name1, @NotNull String name2) {
		if (new Random().nextInt(2) == 0) {
			player1 = new AutoPlayer(name1, player1Colour);
			player2 = new AutoPlayer(name2, player2Colour);
		} else {
			player1 = new AutoPlayer(name2, player1Colour);
			player2 = new AutoPlayer(name1, player2Colour);
		}
		display.showMessage(player1.getTeamColour().getColourCode() + "Player 1 is: " + player1.getName());
		display.showMessage(player2.getTeamColour().getColourCode() + "Player 2 is: " + player2.getName());
	}

	private void stealPiece(@NotNull Square location, @NotNull Piece piece, @NotNull Player player) {
		piece.setTeamColour(player.getTeamColour());
		((KeschetDisplay) display).draw(location);
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

	private boolean nextTo(@NotNull Square newLocation, @NotNull Square oldLocation) {
		int row = Math.abs(oldLocation.getCoordinates().getRow() - newLocation.getCoordinates().getRow());
		int col = Math.abs(oldLocation.getCoordinates().getCol() - newLocation.getCoordinates().getCol());
		return row <= 1 && col <= 1;
	}
}
