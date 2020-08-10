package github.macro.core.pieces;

import github.macro.GameBoard;
import github.macro.Square;
import github.macro.Util;
import github.macro.console.Colour;
import github.macro.pieces.Emperor;
import github.macro.pieces.General;
import github.macro.pieces.Merchant;
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
public class Merchant_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static GameBoard board;
	private static Square start;
	private static Square end;
	private static Square emperor;

	@BeforeAll
	static void beforeAll() {
		board = new GameBoard();
		start = board.getSquare(2, 2);
		end = board.getSquare(4, 4);
		emperor = board.getSquare(4, 5);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new Merchant(Colour.BLUE));
		end.setPiece(new General(Colour.RED));
		emperor.setPiece(null);
	}

	@Test
	void test_allyMerchantMove() {
		end.setPiece(null);
		emperor.setPiece(new Emperor(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Emperor ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_allyMerchantAttackMove() {
		emperor.setPiece(new Emperor(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Emperor Attack ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_enemyMerchantMove() {
		end.setPiece(null);
		emperor.setPiece(new Emperor(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Emperor ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_enemyMerchantAttackMove() {
		emperor.setPiece(new Emperor(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Emperor Attack ==> " + valid);
		assertFalse(valid);
	}
}
