package macro303.keschet.display;

import macro303.board_game.Board;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-14.
 */
public interface Display {
	void showInfo(@NotNull String message);

	void showWarning(@NotNull String message);

	void drawBoard(@NotNull Board board);

	void drawBoard(@NotNull Board board, boolean colourSides);

	void drawBoard(@NotNull Board board, @NotNull Square location);

	@NotNull
	Coordinates requestLocation();
}
