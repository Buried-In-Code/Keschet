package macro303.keschet;

import macro303.keschet.board.Board;
import macro303.keschet.board.Square;
import macro303.keschet.pieces.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Keschet {
	private static final Logger LOGGER = LogManager.getLogger(Keschet.class);
	private static Team team1;
	private static Team team2;
	private static Board board;

	public static void main(String... args) {
		board = new Board();
		team1 = new Team(Colour.BLUE);
		team2 = new Team(Colour.RED);
		placePieces();
	}

	private static void placePieces() {
		for (int i = 0; i < team1.getPieces().size(); i++) {
			placePiece(team1.getPieces().get(i));
			placePiece(team2.getPieces().get(i));
		}
	}

	private static void placePiece(@NotNull Piece piece) {
		boolean placed = false;
		while (!placed) {
			Square location = board.getSquare(selectLocation());
			if (location != null) {
				location.setPiece(piece);
				placed = true;
			}
		}
	}

	@NotNull
	private static Coordinates selectLocation() {
		//TODO Prompt user for coordinates
		return new Coordinates(0, 0);
	}
}
