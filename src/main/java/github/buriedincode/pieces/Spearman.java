package github.buriedincode.pieces;

import github.buriedincode.Direction;
import github.buriedincode.players.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Spearman extends Piece {
  public Spearman(@NotNull Player player) {
    super(player, 2, "P", List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
  }

  @Override
  public String toString() {
    return "Spearman{} " + super.toString();
  }
}
