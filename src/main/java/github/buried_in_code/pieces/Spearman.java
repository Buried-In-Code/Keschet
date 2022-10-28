package github.buried_in_code.pieces;

import github.buried_in_code.Direction;
import github.buried_in_code.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by BuriedInCode on 2018-Feb-12.
 */
public class Spearman extends Piece {
	public Spearman(@NotNull Player player) {
		super(player, 2, "P", List.of(
				Direction.NORTH,
				Direction.EAST,
				Direction.SOUTH,
				Direction.WEST
		));
	}

	@Override
	public String toString() {
		return "Spearman{} " + super.toString();
	}
}
