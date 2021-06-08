package github.macro;

import github.macro.console.Colour;
import github.macro.console.Console;
import github.macro.pieces.*;
import github.macro.players.AutoStartConsolePlayer;
import github.macro.players.Player;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Created by Macro303 on 2021-Jun-02.
 */
public class Keschet {
	private static final Logger LOGGER = LogManager.getLogger();
	@NotNull
	private final Player p1;
	@NotNull
	private final Player p2;
	@NotNull
	private final Board board = new Board();

	public Keschet(@NotNull Player p1, @NotNull Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		placePieces();
		while (checkWinCondition() == null) {
			executeTurn(p1);
			if (checkWinCondition() == null)
				executeTurn(p2);
		}
	}

	private static void checkLogLevels() {
		Arrays.stream(Level.values()).forEach(level -> LOGGER.log(level, "{} is Visible", level.name()));
	}

	public static void main(String[] args) {
		checkLogLevels();
		LOGGER.info("Welcome to Keschet");
		var player1 = new AutoStartConsolePlayer(Console.displayPrompt("Player 1 Name"), Colour.YELLOW);
		var player2 = new AutoStartConsolePlayer(Console.displayPrompt("Player 2 Name"), Colour.RED);
		new Keschet(player1, player2);
	}

	private void placePieces() {
		for (var count = 0; count < 7; count++) {
			for (var pNum = 0; pNum < 2; pNum++) {
				var player = pNum == 0 ? p1 : p2;
				if (count < 1) {
					placePiece(player, new Emperor(player));
					placePiece(player, new General(player));
					placePiece(player, new Scholar(player));
				}
				if (count < 2)
					placePiece(player, new Merchant(player));
				if (count < 3)
					placePiece(player, new Thief(player));
				if (count < 4)
					placePiece(player, new Lancer(player));
				if (count < 5)
					placePiece(player, new Archer(player));
				placePiece(player, new Spearman(player));
			}
		}
	}

	private void placePiece(@NotNull Player player, @NotNull Piece piece) {
		board.draw(true);
		Console.displayMessageFormat("Placing %s", player.getColour(), piece.getName());
		var placed = false;
		do {
			var location = player.placePiece(board, piece);
			if (location.getPiece() != null)
				Console.displayMessageFormat("Must be placed on an empty square => %s", location);
			else if ((player.getColour() != Colour.YELLOW || location.getRow() >= 3) && (player.getColour() != Colour.RED || location.getRow() <= 6))
				Console.displayMessageFormat("Must be placed within 3 rows on your side => %s", location);
			else {
				location.setPiece(piece);
				placed = true;
			}
		} while (!placed);
	}

	@Nullable
	private Player checkWinCondition() {
		var p1Count = board.countPieces(p1);
		var p1Emperor = board.findPiece(Emperor.class, p1) != null;
		var p2Count = board.countPieces(p2);
		var p2Emperor = board.findPiece(Emperor.class, p2) != null;
		Player winner = null;
		if (p1Count <= 1 || !p1Emperor)
			winner = p2;
		else if (p2Count <= 1 || !p2Emperor)
			winner = p1;
		if (winner != null)
			Console.displayMessageFormat("%s Wins", winner.getColour(), winner.getName());
		return winner;
	}

	private void executeTurn(@NotNull Player player) {
		var valid = false;
		do {
			board.draw();
			Console.displayMessage("Select a Piece", player.getColour());
			var fromSquare = player.selectPiece(board);
			if (fromSquare.getPiece() != null && fromSquare.getPiece().getPlayer() == player) {
				var movable = Utils.movablePiece(board, fromSquare);
				if (movable) {
					board.draw(false, fromSquare);
					Console.displayMessageFormat("Move %s", player.getColour(), fromSquare.getPiece().getName());
					var toSquare = player.movePieceTo(board, fromSquare.getPiece());
					if (toSquare.getPiece() == null || toSquare.getPiece().getPlayer() != player) {
						var validMovement = Utils.validMovement(board, fromSquare, toSquare);
						if (validMovement) {
							Piece taken = null;
							if (toSquare.getPiece() != null && fromSquare.getPiece() instanceof Thief)
								taken = toSquare.getPiece();
							toSquare.setPiece(fromSquare.getPiece());
							fromSquare.setPiece(null);
							valid = true;
							if (taken != null)
								stealPiece(toSquare, taken, player);
						} else
							Console.displayMessage("This is an invalid move, try again");
					} else
						Console.displayMessage("That's your piece, you can't take your own piece");
				} else
					Console.displayMessage("That piece is boxed in and can't move");
			} else if (fromSquare.getPiece() == null)
				Console.displayMessage("No Piece at that location");
			else
				Console.displayMessage("That's not your piece, put it back");
		} while (!valid);
	}

	private void stealPiece(@NotNull Square location, @NotNull Piece piece, @NotNull Player player) {
		piece.setPlayer(player);
		board.draw(false, location);
		Console.displayMessageFormat("Select location for stolen %s", player.getColour(), piece.getName());
		var valid = false;
		do {
			var newSquare = player.placeStolenPiece(board, piece, location);
			if (newSquare.getPiece() == null) {
				var row = Math.abs(location.getRow() - newSquare.getRow());
				var col = Math.abs(location.getCol() - newSquare.getCol());
				valid = row <= 1 && col <= 1;
				if (valid)
					newSquare.setPiece(piece);
				else
					Console.displayMessage("Must be placed next to Thief");
			}
		} while (!valid);
	}
}