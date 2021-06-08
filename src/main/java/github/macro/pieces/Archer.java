package github.macro.pieces;

import github.macro.Direction;
import github.macro.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2021-Jun-02.
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