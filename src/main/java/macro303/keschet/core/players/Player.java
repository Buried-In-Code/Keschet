package macro303.keschet.core.players;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.PlayerBase;
import macro303.keschet.core.Coordinates;
import macro303.keschet.core.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Player extends PlayerBase {

	protected Player(@NotNull String name, @NotNull Colour colour, int playerNum) {
		super(name, colour, playerNum);
	}

	@NotNull
	public abstract Coordinates placePiece(@NotNull Board board, @NotNull Piece piece);

	@NotNull
	public abstract Coordinates selectPiece(@NotNull Board board);

	@NotNull
	public abstract Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece);

	@Override
	public String toString() {
		return "Player{} " + super.toString();
	}
}
