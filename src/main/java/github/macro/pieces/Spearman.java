package github.macro.pieces;

import github.macro.Direction;
import github.macro.players.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Macro303 on 2018-Feb-12.
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