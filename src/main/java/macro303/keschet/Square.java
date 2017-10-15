package macro303.keschet;

import macro303.keschet.pieces.Piece;

public class Square {
	private Piece piece;

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public int hashCode() {
		return piece != null ? piece.hashCode() : 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Square)) return false;

		Square square = (Square) o;

		return piece != null ? piece.equals(square.piece) : square.piece == null;
	}

	@Override
	public String toString() {
		return "Square{" +
				"piece=" + piece +
				'}';
	}

}
