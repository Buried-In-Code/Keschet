package github.macro.core.pieces;

import github.macro.Board;
import github.macro.Square;
import github.macro.console.Colour;
import github.macro.pieces.General;
import github.macro.pieces.Scholar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Macro303 on 2019-May-29
 */
public class Scholar_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static Board board;
	private static Square start;
	private static Square end;
	private static Square block;

	@BeforeAll
	static void beforeAll() {
		board = new Board();
		start = board.getSquare(2, 2);
		block = board.getSquare(4, 5);
		end = board.getSquare(4, 4);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new General(Colour.BLUE));
		block.setPiece(null);
		end.setPiece(new General(Colour.RED));
	}

	@Test
	void test_enemyBlockMove() {
		block.setPiece(new Scholar(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Scholar Block ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_allyBlockMove() {
		block.setPiece(new Scholar(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Scholar Block ==> " + valid);
		assertTrue(valid);
	}
}
