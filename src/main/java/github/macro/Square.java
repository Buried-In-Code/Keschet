package github.macro;

import github.macro.console.Colour;
import github.macro.pieces.Piece;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Square {
	@NotNull
	private final Coordinate coord;
	@Nullable
	private Piece piece;

	public Square(Coordinate coord) {
		this.coord = coord;
	}

	@NotNull
	public Coordinate getCoord() {
		return coord;
	}

	@Nullable
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(@Nullable Piece piece) {
		this.piece = piece;
	}

	public String draw() {
		return String.format(" %s%s ", piece == null ? "~" : piece.getColouredSymbol(), Colour.RESET.getANSICode());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Square)) return false;

		Square square = (Square) o;

		if (!coord.equals(square.coord)) return false;
		return Objects.equals(piece, square.piece);
	}

	@Override
	public int hashCode() {
		int result = coord.hashCode();
		result = 31 * result + (piece != null ? piece.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Square{" +
				"coord=" + coord +
				", piece=" + piece +
				'}';
	}
}
