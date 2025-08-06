package github.buriedincode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

public class Direction_Test {
  private static final Logger LOGGER = LogManager.getLogger();
  private static Board board;
  private static Player player;
  private static Square start;
  private static Square end;

  @BeforeAll
  static void beforeAll() {
    board = new Board();
    player = new ConsolePlayer("Tester", Colour.YELLOW);
    start = board.getSquare(2, 2);
  }

  @AfterEach
  void afterEach() {
    board.draw();
  }

  @BeforeEach
  void beforeEach() {
    start.setPiece(new General(player));
    if (end != null) {
      end.setPiece(null);
    }
  }

  @Test
  void test_eastDirection() {
    end = board.getSquare(2, 3);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("East ==> " + direction);
    assertEquals(Direction.EAST, direction);
  }

  @Test
  void test_invalidDirection() {
    end = board.getSquare(4, 3);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("Invalid ==> " + direction);
    assertNull(direction);
  }

  @Test
  void test_noDirection() {
    Direction direction = Utils.calculateDirection(start, start);
    LOGGER.info("None ==> " + direction);
    assertNull(direction);
  }

  @Test
  void test_northDirection() {
    end = board.getSquare(1, 2);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("North ==> " + direction);
    assertEquals(Direction.NORTH, direction);
  }

  @Test
  void test_northEastDirection() {
    end = board.getSquare(1, 3);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("North-East ==> " + direction);
    assertEquals(Direction.NORTH_EAST, direction);
  }

  @Test
  void test_northWestDirection() {
    end = board.getSquare(1, 1);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("North-West ==> " + direction);
    assertEquals(Direction.NORTH_WEST, direction);
  }

  @Test
  void test_southDirection() {
    end = board.getSquare(3, 2);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("South ==> " + direction);
    assertEquals(Direction.SOUTH, direction);
  }

  @Test
  void test_southEastDirection() {
    end = board.getSquare(3, 3);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("South-East ==> " + direction);
    assertEquals(Direction.SOUTH_EAST, direction);
  }

  @Test
  void test_southWestDirection() {
    end = board.getSquare(3, 1);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("South-West ==> " + direction);
    assertEquals(Direction.SOUTH_WEST, direction);
  }

  @Test
  void test_westDirection() {
    end = board.getSquare(2, 1);
    assert end != null;
    Direction direction = Utils.calculateDirection(start, end);
    LOGGER.info("West ==> " + direction);
    assertEquals(Direction.WEST, direction);
  }
}
