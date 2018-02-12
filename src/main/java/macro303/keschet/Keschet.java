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
		board.draw();
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
		board.draw(true);
		boolean placed = false;
		do {
			Coordinates selected = player.placePiece(board, piece);
			Square location = board.getSquare(selected);
			if (location != null && location.getPiece() == null && ((player == player1 && selected.getRow() < 3) || (player == player2 && selected.getRow() > 6))) {
				location.setPiece(piece);
				placed = true;
			} else if (location == null) {
				LOGGER.info("Must be placed on the board (0-9)");
			} else if (location.getPiece() != null) {
				LOGGER.info("Must be placed on an empty square");
			} else {
				LOGGER.info("Invalid Placement. Place within 3 rows on your side");
			}
		} while (!placed);
	}
}
