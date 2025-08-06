package github.buriedincode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import github.buriedincode.console.Colour;
import github.buriedincode.pieces.General;
import github.buriedincode.players.ConsolePlayer;
import github.buriedincode.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Attack_Test {
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
    block = board.getSquare(3, 3);
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
    end.setPiece(null);
  }

  @Test
  void test_allyAttackMove() {
    end.setPiece(new General(player1));
    boolean valid = Utils.validMovement(board, start, end);
    LOGGER.info("Ally Attack ==> " + valid);
    assertFalse(valid);
  }

  @Test
  void test_blockedMove() {
    block.setPiece(new General(player2));
    end.setPiece(new General(player2));
    boolean valid = Utils.validMovement(board, start, end);
    LOGGER.info("Movement Blocked ==> " + valid);
    assertFalse(valid);
  }

  @Test
  void test_enemyTakingMove() {
    end.setPiece(new General(player2));
    boolean valid = Utils.validMovement(board, start, end);
    LOGGER.info("Enemy Attack ==> " + valid);
    assertTrue(valid);
  }

  @Test
  void test_noPieceMove() {
    boolean valid = Utils.validMovement(board, end, start);
    LOGGER.info("No Piece ==> " + valid);
    assertFalse(valid);
  }
}
