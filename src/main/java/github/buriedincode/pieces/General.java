package github.buriedincode.pieces;

import github.buriedincode.Direction;
import github.buriedincode.players.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class General extends Piece {
  public General(@NotNull Player player) {
    super(player, 10, "G", List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST,
        Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST));
  }

  @Override
  public String toString() {
    return "General{} " + super.toString();
  }
}
