package github.buriedincode.pieces;

import github.buriedincode.Direction;
import github.buriedincode.players.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Scholar extends Piece {
  public Scholar(@NotNull Player player) {
    super(player, 2, "C", List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST,
        Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST));
  }

  @Override
  public String toString() {
    return "Scholar{} " + super.toString();
  }
}
