package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.pieces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
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
	private static Board board;
	private static Square start;
	private static Square end;
	private static Square emperor;

	@BeforeAll
	static void beforeAll() {
		board = new Board(10);
		start = board.getSquare(new Coordinates(2, 2));
		end = board.getSquare(new Coordinates(4, 4));
		emperor = board.getSquare(new Coordinates(4, 5));
	}

	@BeforeEach
	void beforeEach(){
		start.setItem(new Merchant(Colour.BLUE));
		end.setItem(new General(Colour.RED));
		emperor.setItem(null);
	}

	@Test
	void test_allyMerchantMove() {
		end.setItem(null);
		emperor.setItem(new Emperor(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Emperor ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_allyMerchantAttackMove() {
		emperor.setItem(new Emperor(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Emperor Attack ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_enemyMerchantMove() {
		end.setItem(null);
		emperor.setItem(new Emperor(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Emperor ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_enemyMerchantAttackMove() {
		emperor.setItem(new Emperor(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Emperor Attack ==> " + valid);
		assertFalse(valid);
	}
}
