package github.macro.pieces;

import github.macro.Direction;
import github.macro.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2021-Jun-02.
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