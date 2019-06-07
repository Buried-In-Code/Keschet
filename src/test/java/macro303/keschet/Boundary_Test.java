package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Square;
import macro303.keschet.core.pieces.Emperor;
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
	private static Board board;
	private static Square start;
	private static Square end;

	@BeforeAll
	static void beforeAll() {
		board = new Board(10);
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