package github.macro.core.pieces;

import github.macro.Direction;
import github.macro.Board;
import github.macro.Square;
import github.macro.console.Colour;
import github.macro.pieces.Archer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Macro303 on 2019-Jun-07
 */
public class Archer_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Board board = new Board();
	private static Square start;
	private static Square end;
	private static Archer testPiece;

	@BeforeAll
	static void beforeAll() {
		start = board.getSquare(2, 2);
		testPiece = new Archer(Colour.BLUE);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(testPiece);
	}

	@AfterEach
	void afterEach() {
		if (end != null) {
			end.setPiece(null);
			end = null;
		}
	}

	@Test
	void test_validDirections() {
		boolean north = Arrays.asList(testPiece.getValidDirections()).contains(Direction.NORTH);
		LOGGER.info("North is a Valid Direction => " + north);
		assertTrue(north);
		boolean east = Arrays.asList(testPiece.getValidDirections()).contains(Direction.EAST);
		LOGGER.info("East is a Valid Direction => " + east);
		assertTrue(east);
		boolean south = Arrays.asList(testPiece.getValidDirections()).contains(Direction.SOUTH);
		LOGGER.info("South is a Valid Direction => " + south);
		assertTrue(south);
		boolean west = Arrays.asList(testPiece.getValidDirections()).contains(Direction.WEST);
		LOGGER.info("West is a Valid Direction => " + west);
		assertTrue(west);
	}

	@Test
	void test_invalidDirections() {
		boolean northEast = Arrays.asList(testPiece.getValidDirections()).contains(Direction.NORTH_EAST);
		LOGGER.info("North-East is an Invalid Direction => " + !northEast);
		assertFalse(northEast);
		boolean southEast = Arrays.asList(testPiece.getValidDirections()).contains(Direction.SOUTH_EAST);
		LOGGER.info("South-East is an Invalid Direction => " + !southEast);
		assertFalse(southEast);
		boolean southWest = Arrays.asList(testPiece.getValidDirections()).contains(Direction.SOUTH_WEST);
		LOGGER.info("South-West is an Invalid Direction => " + !southWest);
		assertFalse(southWest);
		boolean northWest = Arrays.asList(testPiece.getValidDirections()).contains(Direction.NORTH_WEST);
		LOGGER.info("North-West is an Invalid Direction => " + !northWest);
		assertFalse(northWest);
		boolean invalid = Arrays.asList(testPiece.getValidDirections()).contains(Direction.INVALID);
		LOGGER.info("Invalid is an Invalid Direction => " + !invalid);
		assertFalse(invalid);
	}
}