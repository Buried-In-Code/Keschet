package macro303.keschet.players;

import macro303.keschet.Colour;
import macro303.keschet.Coordinates;
import macro303.keschet.board.Board;
import macro303.keschet.display.Display;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public abstract class Player {
	@NotNull
	protected final Display display;
	@NotNull
	protected final Colour colour;
	@NotNull
	private final String name;

	protected Player(@NotNull String name, @NotNull Display display, @NotNull Colour colour) {
		this.name = name;
		this.display = display;
		this.colour = colour;
	}

	@NotNull
	public String getName() {
		return name;
	}

	@NotNull
	public Colour getColour() {
		return colour;
	}

	@NotNull
	public Display getDisplay() {
		return display;
	}

	@NotNull
	public abstract Coordinates placePiece(@NotNull Board board, @NotNull Piece piece);

	@NotNull
	public abstract Coordinates selectPiece(@NotNull Board board);

	@NotNull
	public abstract Coordinates movePieceTo(@NotNull Board board, @NotNull Piece piece);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		if (!display.equals(player.display)) return false;
		if (colour != player.colour) return false;
		return name.equals(player.name);
	}

	@Override
	public int hashCode() {
		int result = display.hashCode();
		result = 31 * result + colour.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"display=" + display +
				", colour=" + colour +
				", name='" + name + '\'' +
				'}';
	}
}
