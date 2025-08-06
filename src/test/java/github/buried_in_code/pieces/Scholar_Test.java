package github.buriedincode.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import github.buriedincode.Board;
import github.buriedincode.Square;
import github.buriedincode.Utils;
import github.buriedincode.console.Colour;
import github.buriedincode.players.ConsolePlayer;
import github.buriedincode.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Scholar_Test {
  private static final Logger LOGGER = LogManager.getLogger();
  private static Board board;
  private static Player player1;
  private static Player player2;
  private static Square start;
  private static Square end;
  private static Square block;

  @BeforeAll
  static void beforeAll() {
    board = new Board();
    player1 = new ConsolePlayer("Player", Colour.YELLOW);
    player2 = new ConsolePlayer("Opponent", Colour.RED);
    start = board.getSquare(2, 2);
    block = board.getSquare(4, 5);
    end = board.getSquare(4, 4);
  }

  @AfterEach
  void afterEach() {
    board.draw();
  }

  @BeforeEach
  void beforeEach() {
    start.setPiece(new General(player1));
    block.setPiece(null);
    end.setPiece(new General(player2));
  }

  @Test
  void test_allyBlockMove() {
    block.setPiece(new Scholar(player1));
    boolean valid = Utils.validMovement(board, start, end);
    LOGGER.info("Ally Scholar Block ==> " + valid);
    assertTrue(valid);
  }

  @Test
  void test_enemyBlockMove() {
    block.setPiece(new Scholar(player2));
    boolean valid = Utils.validMovement(board, start, end);
    LOGGER.info("Enemy Scholar Block ==> " + valid);
    assertFalse(valid);
  }
}
