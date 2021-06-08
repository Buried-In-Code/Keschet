package github.macro.pieces;

import github.macro.Direction;
import github.macro.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2021-Jun-02.
 */
public class General extends Piece {
	public General(@NotNull Player player) {
		super(player, 10, "G", List.of(
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
		return "General{} " + super.toString();
	}
}