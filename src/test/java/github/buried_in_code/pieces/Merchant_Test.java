package github.buried_in_code.pieces;

import github.buried_in_code.Board;
import github.buried_in_code.Square;
import github.buried_in_code.Utils;
import github.buried_in_code.console.Colour;
import github.buried_in_code.players.ConsolePlayer;
import github.buried_in_code.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by BuriedInCode on 2019-May-29
 */
public class Merchant_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static Board board;
	private static Player player1;
	private static Player player2;
	private static Square start;
	private static Square end;
	private static Square emperor;

	@BeforeAll
	static void beforeAll() {
		board = new Board();
		player1 = new ConsolePlayer("Player", Colour.YELLOW);
		player2 = new ConsolePlayer("Opponent", Colour.RED);
		start = board.getSquare(2, 2);
		end = board.getSquare(4, 4);
		emperor = board.getSquare(4, 5);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new Merchant(player1));
		end.setPiece(new General(player2));
		emperor.setPiece(null);
	}

	@AfterEach
	void afterEach(){
		board.draw();
	}

	@Test
	void test_allyMerchantMove() {
		end.setPiece(null);
		emperor.setPiece(new Emperor(player1));
		boolean valid = Utils.validMovement(board, start, end);
		LOGGER.info("Ally Emperor ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_allyMerchantAttackMove() {
		emperor.setPiece(new Emperor(player1));
		boolean valid = Utils.validMovement(board, start, end);
		LOGGER.info("Ally Emperor Attack ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_enemyMerchantMove() {
		end.setPiece(null);
		emperor.setPiece(new Emperor(player2));
		boolean valid = Utils.validMovement(board, start, end);
		LOGGER.info("Enemy Emperor ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_enemyMerchantAttackMove() {
		emperor.setPiece(new Emperor(player2));
		boolean valid = Utils.validMovement(board, start, end);
		LOGGER.info("Enemy Emperor Attack ==> " + valid);
		assertFalse(valid);
	}
}
