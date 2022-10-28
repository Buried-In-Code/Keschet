package github.buried_in_code.pieces;

import github.buried_in_code.Direction;
import github.buried_in_code.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by BuriedInCode on 2018-Feb-09.
 */
public class Lancer extends Piece {
	public Lancer(@NotNull Player player) {
		super(player, 10, "L", List.of(
				Direction.NORTH_EAST,
				Direction.SOUTH_EAST,
				Direction.SOUTH_WEST,
				Direction.NORTH_WEST
		));
	}

	@Override
	public String toString() {
		return "Lancer{} " + super.toString();
	}
}
