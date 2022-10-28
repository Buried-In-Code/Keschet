package github.buried_in_code.pieces;

import github.buried_in_code.Direction;
import github.buried_in_code.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2018-Feb-08.
 */
public abstract class Piece {
	protected final int distance;
	@NotNull
	protected final String symbol;
	@NotNull
	protected final List<@NotNull Direction> directions;
	@NotNull
	protected Player player;

	protected Piece(
			@NotNull Player player,
			int distance,
			@NotNull String symbol,
			@NotNull List<@NotNull Direction> directions
	) {
		this.player = player;
		this.distance = distance;
		this.symbol = symbol;
		this.directions = directions;
	}

	@NotNull
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(@NotNull Player player) {
		this.player = player;
	}

	public int getDistance() {
		return distance;
	}

	@NotNull
	public String getSymbol() {
		return symbol;
	}

	@NotNull
	public List<@NotNull Direction> getDirections() {
		return directions;
	}

	@NotNull
	public String getName() {
		var name = getClass().getSimpleName().toLowerCase();
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	@Override
	public String toString() {
		return "Piece{" +
				       "distance=" + distance +
				       ", symbol='" + symbol + '\'' +
				       ", directions=" + directions +
				       ", player=" + player +
				       '}';
	}
}