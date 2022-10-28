package github.buried_in_code.pieces;

import github.buried_in_code.Direction;
import github.buried_in_code.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2018-Feb-08.
 */
public class Archer extends Piece {
	public Archer(@NotNull Player player) {
		super(player, 6, "A", List.of(
				Direction.NORTH,
				Direction.EAST,
				Direction.SOUTH,
				Direction.WEST
		));
	}

	@Override
	public String toString() {
		return "Archer{} " + super.toString();
	}
}