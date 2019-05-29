package macro303.keschet.players;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Team;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Player extends Team {

	protected Player(@NotNull String name, @NotNull Colour teamColour) {
		super(name, teamColour);
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
