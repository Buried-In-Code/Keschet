package macro303.keschet;

public class Pair<L, R> {

	private final L x;
	private final R y;

	public Pair(L x, R y) {
		this.x = x;
		this.y = y;
	}

	public L getX() {
		return x;
	}

	public R getY() {
		return y;
	}

	@Override
	public int hashCode() {
		int result = x != null ? x.hashCode() : 0;
		result = 31 * result + (y != null ? y.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pair)) return false;

		Pair<?, ?> pair = (Pair<?, ?>) o;

		if (x != null ? !x.equals(pair.x) : pair.x != null) return false;
		return y != null ? y.equals(pair.y) : pair.y == null;
	}

	@Override
	public String toString() {
		return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

}