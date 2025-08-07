package github.buriedincode.players;

import github.buriedincode.Board;
import github.buriedincode.Square;
import github.buriedincode.console.Colour;
import github.buriedincode.pieces.Piece;
import org.jetbrains.annotations.NotNull;

public abstract class Player {
  @NotNull
  protected final String name;
  @NotNull
  protected final Colour colour;

  protected Player(@NotNull String name, @NotNull Colour colour) {
    this.name = name;
    this.colour = colour;
  }

  @NotNull
  public Colour getColour() {
    return colour;
  }

  @NotNull
  public String getName() {
    return name;
  }

  @NotNull
  public abstract Square movePieceTo(@NotNull Board board, @NotNull Piece piece);

  @NotNull
  public abstract Square placePiece(@NotNull Board board, @NotNull Piece piece);

  @NotNull
  public abstract Square placeStolenPiece(@NotNull Board board, @NotNull Piece piece, @NotNull Square thiefSquare);

  @NotNull
  public abstract Square selectPiece(@NotNull Board board);

  @Override
  public String toString() {
    return "Player{" + "name='" + name + '\'' + ", colour=" + colour + '}';
  }
}
