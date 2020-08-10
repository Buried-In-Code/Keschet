package github.macro;

import github.macro.console.Colour;
import github.macro.pieces.Emperor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Macro303 on 2018-02-14.
 */
public class Boundary_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static GameBoard board;
	private static Square start;
	private static Square end;

	@BeforeAll
	static void beforeAll() {
		board = new GameBoard();
		start = board.getSquare(2, 2);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new Emperor(Colour.BLUE));
		if (end != null)
			end.setPiece(null);
	}

	@Test
	void test_distanceInMove() {
		end = board.getSquare(6, 6);
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Distance In ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_distanceOutMove() {
		end = board.getSquare(7, 7);
		assert end != null;
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Distance Out ==> " + valid);
		assertFalse(valid);
	}
}