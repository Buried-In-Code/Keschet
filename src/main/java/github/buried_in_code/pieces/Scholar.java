package github.buried_in_code.pieces;

import github.buried_in_code.Direction;
import github.buried_in_code.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2018-Feb-09.
 */
public class Scholar extends Piece {
	public Scholar(@NotNull Player player) {
		super(player, 2, "C", List.of(
				Direction.NORTH,
				Direction.NORTH_EAST,
				Direction.EAST,
				Direction.SOUTH_EAST,
				Direction.SOUTH,
				Direction.SOUTH_WEST,
				Direction.WEST,
				Direction.NORTH_WEST
		));
	}

	@Override
	public String toString() {
		return "Scholar{} " + super.toString();
	}
}