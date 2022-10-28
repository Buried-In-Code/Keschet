package github.buried_in_code.pieces;

import github.buried_in_code.Board;
import github.buried_in_code.Direction;
import github.buried_in_code.Square;
import github.buried_in_code.console.Colour;
import github.buried_in_code.players.ConsolePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Macro303 on 2019-Jun-07
 */
public class Archer_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static Board board;
	private static Square start;
	private static Square end;
	private static Archer piece;

	@BeforeAll
	static void beforeAll() {
		board = new Board();
		var player = new ConsolePlayer("Player", Colour.YELLOW);
		start = board.getSquare(2, 2);
		piece = new Archer(player);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(piece);
	}

	@AfterEach
	void afterEach() {
		if (end != null) {
			end.setPiece(null);
			end = null;
		}
		board.draw();
	}

	@Test
	void test_validDirections() {
		boolean north = piece.getDirections().contains(Direction.NORTH);
		LOGGER.info("North is a Valid Direction => " + north);
		assertTrue(north);
		boolean east = piece.getDirections().contains(Direction.EAST);
		LOGGER.info("East is a Valid Direction => " + east);
		assertTrue(east);
		boolean south = piece.getDirections().contains(Direction.SOUTH);
		LOGGER.info("South is a Valid Direction => " + south);
		assertTrue(south);
		boolean west = piece.getDirections().contains(Direction.WEST);
		LOGGER.info("West is a Valid Direction => " + west);
		assertTrue(west);
	}

	@Test
	void test_invalidDirections() {
		boolean northEast = piece.getDirections().contains(Direction.NORTH_EAST);
		LOGGER.info("North-East is an Invalid Direction => " + !northEast);
		assertFalse(northEast);
		boolean southEast = piece.getDirections().contains(Direction.SOUTH_EAST);
		LOGGER.info("South-East is an Invalid Direction => " + !southEast);
		assertFalse(southEast);
		boolean southWest = piece.getDirections().contains(Direction.SOUTH_WEST);
		LOGGER.info("South-West is an Invalid Direction => " + !southWest);
		assertFalse(southWest);
		boolean northWest = piece.getDirections().contains(Direction.NORTH_WEST);
		LOGGER.info("North-West is an Invalid Direction => " + !northWest);
		assertFalse(northWest);
	}
}