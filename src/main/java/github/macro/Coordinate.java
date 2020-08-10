package github.macro;

/**
 * Created by Macro303 on 2019-Jun-07
 */
public class Coordinate {
	private final int row;
	private final int col;

	public Coordinate(int row, int col) {
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
	public int hashCode() {
		int result = row;
		result = 31 * result + col;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Coordinate)) return false;

		Coordinate that = (Coordinate) o;

		if (row != that.row) return false;
		return col == that.col;
	}

	@Override
	public String toString() {
		return "Coordinates{" +
				"row=" + row +
				", col=" + col +
				'}';
	}
}
