package macro303.keschet;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Coordinates {
	private final int row;
	private final int col;

	public Coordinates(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Coordinates)) return false;

		Coordinates that = (Coordinates) o;

		if (row != that.row) return false;
		return col == that.col;
	}

	@Override
	public int hashCode() {
		int result = row;
		result = 31 * result + col;
		return result;
	}

	@Override
	public String toString() {
		return "Coordinates{" +
				"row=" + row +
				", col=" + col +
				'}';
	}
}