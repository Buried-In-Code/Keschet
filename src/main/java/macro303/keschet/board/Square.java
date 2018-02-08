package macro303.keschet.board;

import macro303.keschet.pieces.Piece;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Square {
	@Nullable
	private Piece piece = null;

	@Nullable
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(@Nullable Piece piece) {
		this.piece = piece;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Square)) return false;

		Square square = (Square) o;

		return piece != null ? piece.equals(square.piece) : square.piece == null;
	}

	@Override
	public int hashCode() {
		return piece != null ? piece.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Square{" +
				"piece=" + piece +
				'}';
	}
}
