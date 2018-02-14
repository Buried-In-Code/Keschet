package macro303.keschet.players.console;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.board.Board;
import macro303.keschet.display.console.Console;
import macro303.keschet.pieces.Piece;
import macro303.keschet.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-12.
 */
public class ConsolePlayer extends Player {
	private static final Logger LOGGER = LogManager.getLogger(ConsolePlayer.class);
	@Nullable
	private Piece currentPiece = null;

	public ConsolePlayer(@NotNull String name, @NotNull Colour colour) {
		super(name, new Console(), colour);
	}

	@NotNull
	@Override
	public Coordinates placePiece(@NotNull Board board, @NotNull Piece piece) {
		if (currentPiece != piece)
			((Console) display).showTitle("Place " + piece.getClass().getSimpleName(), getColour());
		currentPiece = piece;
		return display.requestLocation();
	}

	@NotNull
	@Override
	public Coordinates selectPiece(@NotNull Board board) {
		((Console) display).showTitle("Select a piece", getColour());
		return display.requestLocation();
	}

	@NotNull
	@Override
	public Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece) {
		((Console) display).showTitle("Move " + piece.getClass().getSimpleName() + " to", getColour());
		return display.requestLocation();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ConsolePlayer)) return false;
		if (!super.equals(o)) return false;

		ConsolePlayer that = (ConsolePlayer) o;

		return currentPiece != null ? currentPiece.equals(that.currentPiece) : that.currentPiece == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (currentPiece != null ? currentPiece.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ConsolePlayer{" +
				"currentPiece=" + currentPiece +
				'}';
	}
}
