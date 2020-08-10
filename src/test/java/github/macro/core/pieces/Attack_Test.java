package github.macro.core.pieces;

import github.macro.GameBoard;
import github.macro.Square;
import github.macro.Util;
import github.macro.console.Colour;
import github.macro.pieces.General;
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
public class Attack_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static GameBoard board;
	private static Square start;
	private static Square end;
	private static Square block;

	@BeforeAll
	static void beforeAll() {
		board = new GameBoard();
		start = board.getSquare(2, 2);
		block = board.getSquare(3, 3);
		end = board.getSquare(4, 4);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new General(Colour.BLUE));
		block.setPiece(null);
		end.setPiece(null);
	}

	@Test
	void test_allyAttackMove() {
		end.setPiece(new General(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Attack ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_blockedMove() {
		block.setPiece(new General(Colour.RED));
		end.setPiece(new General(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Movement Blocked ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_enemytakingMove() {
		end.setPiece(new General(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Attack ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_noPieceMove() {
		boolean valid = Util.validMovement(board, end, start);
		LOGGER.info("No Piece ==> " + valid);
		assertFalse(valid);
	}
}
