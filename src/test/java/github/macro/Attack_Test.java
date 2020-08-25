package github.macro;

import github.macro.pieces.General;
import github.macro.players.ConsolePlayer;
import github.macro.players.Player;
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
	private static Board board;
	private static Player player1;
	private static Player player2;
	private static Square start;
	private static Square end;
	private static Square block;

	@BeforeAll
	static void beforeAll() {
		board = new Board();
		player1 = new ConsolePlayer("Player", Util.getP1_COLOUR());
		player2 = new ConsolePlayer("Opponent", Util.getP2_COLOUR());
		start = board.getSquare(2, 2);
		block = board.getSquare(3, 3);
		end = board.getSquare(4, 4);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new General(player1));
		block.setPiece(null);
		end.setPiece(null);
	}

	@Test
	void test_allyAttackMove() {
		end.setPiece(new General(player1));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Attack ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_blockedMove() {
		block.setPiece(new General(player2));
		end.setPiece(new General(player2));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Movement Blocked ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_enemytakingMove() {
		end.setPiece(new General(player2));
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
