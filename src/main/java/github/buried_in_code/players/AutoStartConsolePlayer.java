package github.buried_in_code.players;

import github.buried_in_code.Board;
import github.buried_in_code.Square;
import github.buried_in_code.console.Colour;
import github.buried_in_code.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by BuriedInCode on 2018-Feb-22.
 */
public class AutoStartConsolePlayer extends ConsolePlayer {
	public AutoStartConsolePlayer(@NotNull String name, @NotNull Colour colour) {
		super(name, colour);
	}

	@NotNull
	@Override
	public Square placePiece(@NotNull Board board, @NotNull Piece piece) {
		if (colour == Colour.YELLOW)
			return placePlayer1Piece(board);
		else
			return placePlayer2Piece(board);
	}

	private Square placePlayer1Piece(@NotNull Board board) {
		for (var row = 0; row < 3; row++) {
			for (var col = 0; col < Board.WIDTH; col++) {
				var square = board.getSquare(row, col);
				if (square != null && square.getPiece() == null)
					return square;
			}
		}
		return requestLocation(board);
	}

	private Square placePlayer2Piece(@NotNull Board board) {
		for (var row = Board.HEIGHT - 1; row > 6; row--) {
			for (var col = 0; col < Board.WIDTH; col++) {
				var square = board.getSquare(row, col);
				if (square != null && square.getPiece() == null)
					return square;
			}
		}
		return requestLocation(board);
	}
}
