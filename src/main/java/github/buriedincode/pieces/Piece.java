package github.buriedincode.pieces;

import github.buriedincode.Direction;
import github.buriedincode.players.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public abstract class Piece {
  protected final int distance;
  @NotNull
  protected final String symbol;
  @NotNull
  protected final List<@NotNull Direction> directions;
  @NotNull
  protected Player player;

  protected Piece(@NotNull Player player, int distance, @NotNull String symbol,
      @NotNull List<@NotNull Direction> directions) {
    this.player = player;
    this.distance = distance;
    this.symbol = symbol;
    this.directions = directions;
  }

  @NotNull
  public List<@NotNull Direction> getDirections() {
    return directions;
  }

  public int getDistance() {
    return distance;
  }

  @NotNull
  public String getName() {
    var name = getClass().getSimpleName().toLowerCase();
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  @NotNull
  public Player getPlayer() {
    return player;
  }

  @NotNull
  public String getSymbol() {
    return symbol;
  }

  public void setPlayer(@NotNull Player player) {
    this.player = player;
  }

  @Override
  public String toString() {
    return "Piece{" + "distance=" + distance + ", symbol='" + symbol + '\'' + ", directions=" + directions
        + ", player=" + player + '}';
  }
}
