package macro303.keschet.players.console;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
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
		super(name, colour);
	}

	@NotNull
	@Override
	public Coordinates placePiece(@NotNull Board board, @NotNull Piece piece) {
		currentPiece = piece;
		return requestLocation();
	}

	@NotNull
	@Override
	public Coordinates selectPiece(@NotNull Board board) {
		return requestLocation();
	}

	@NotNull
	@Override
	public Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece) {
		return requestLocation();
	}

	@NotNull
	private Coordinates requestLocation() {
		boolean valid = false;
		int row = -1;
		int col = -1;
		do {
			String input = Reader.readConsole("Location (Row:Col)");
			String[] location = input.split(":");
			try {
				row = Integer.parseInt(location[0]);
				col = Integer.parseInt(location[1]);
				valid = true;
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				LOGGER.warn("Invalid Selection");
			}
		} while (!valid);
		return new Coordinates(row, col);
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
