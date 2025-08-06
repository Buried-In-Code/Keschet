package github.buriedincode.pieces;

import github.buriedincode.Direction;
import github.buriedincode.players.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Lancer extends Piece {
  public Lancer(@NotNull Player player) {
    super(player, 10, "L",
        List.of(Direction.NORTH_EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_WEST));
  }

  @Override
  public String toString() {
    return "Lancer{} " + super.toString();
  }
}
