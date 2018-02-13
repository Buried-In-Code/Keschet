package macro303.keschet.display;

import macro303.keschet.Coordinates;
import macro303.keschet.board.Board;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-14.
 */
public interface Display {
	void showInfo(@NotNull String message);

	void showWarning(@NotNull String message);

	void drawBoard(@NotNull Board board, boolean colourSides);

	@NotNull
	Coordinates requestLocation();
}
