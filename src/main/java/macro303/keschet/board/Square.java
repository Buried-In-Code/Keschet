package macro303.keschet.board;

import macro303.keschet.Coordinates;
import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Square {
	@Nullable
	private Piece piece = null;
	@NotNull
	private Coordinates coordinates;

	Square(@NotNull Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Nullable
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(@Nullable Piece piece) {
		this.piece = piece;
	}

	@NotNull
	public Coordinates getCoordinates() {
		return coordinates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Square)) return false;

		Square square = (Square) o;

		if (piece != null ? !piece.equals(square.piece) : square.piece != null) return false;
		return coordinates.equals(square.coordinates);
	}

	@Override
	public int hashCode() {
		int result = piece != null ? piece.hashCode() : 0;
		result = 31 * result + coordinates.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Square{" +
				"piece=" + piece +
				", coordinates=" + coordinates +
				'}';
	}
}
