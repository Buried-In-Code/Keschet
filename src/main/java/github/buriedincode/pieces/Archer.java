package github.buriedincode.pieces;

import github.buriedincode.Direction;
import github.buriedincode.players.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Archer extends Piece {
  public Archer(@NotNull Player player) {
    super(player, 6, "A", List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
  }

  @Override
  public String toString() {
    return "Archer{} " + super.toString();
  }
}
