package github.buried_in_code;

import github.buried_in_code.console.Colour;
import github.buried_in_code.pieces.Piece;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by BuriedInCode on 2021-Jun-02.
 */
public class Square {
	private final int row;
	private final int col;
	@Nullable
	private Piece piece;

	public Square(int row, int col) {
		this(row, col, null);
	}

	public Square(int row, int col, @Nullable Piece piece) {
		this.row = row;
		this.col = col;
		this.piece = piece;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Nullable
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(@Nullable Piece piece) {
		this.piece = piece;
	}

	@NotNull
	public String display() {
		Colour colour;
		if (piece == null)
			colour = Colour.BLACK;
		else
			colour = piece.getPlayer().getColour();
		String symbol;
		if (piece == null)
			symbol = "~";
		else
			symbol = piece.getSymbol();

		return String.format(" %s%s%s ", colour, symbol, Colour.RESET);
	}
}
