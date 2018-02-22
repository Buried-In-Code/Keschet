package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.pieces.Piece;
import macro303.keschet.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class KeschetBoard extends Board {
	private static final Logger LOGGER = LogManager.getLogger(KeschetBoard.class);

	public KeschetBoard(int size) {
		super(size);
	}

	public int countPieces(@NotNull Player player) {
		int counter = 0;
		for (int row = 0; row < Util.SIZE; row++) {
			for (int col = 0; col < Util.SIZE; col++) {
				Square location = getSquare(new Coordinates(row, col));
				assert location != null;
				if (location.getItem() != null && ((Piece) location.getItem()).getTeamColour() == player.getTeamColour())
					counter++;
			}
		}
		return counter;
	}

	@Nullable
	public Square findPiece(@NotNull Class clazz, @NotNull Colour teamColour) {
		Square temp = null;
		for (int row = 0; row < Util.SIZE; row++) {
			for (int col = 0; col < Util.SIZE; col++) {
				Square location = getSquare(new Coordinates(row, col));
				assert location != null;
				if (location.getItem() != null && location.getItem().getClass().equals(clazz) && ((Piece) location.getItem()).getTeamColour() == teamColour)
					temp = location;
			}
		}
		return temp;
	}
}
