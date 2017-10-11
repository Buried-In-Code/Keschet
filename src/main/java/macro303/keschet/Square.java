package macro303.keschet;

import macro303.keschet.pieces.Piece;

import java.util.Objects;

public class Square {
	private Piece piece;

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Square)) return false;
		Square square = (Square) o;
		return Objects.equals(getPiece(), square.getPiece());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPiece());
	}

	@Override
	public String toString() {
		return "Square{" +
				"piece=" + piece +
				'}';
	}

}
