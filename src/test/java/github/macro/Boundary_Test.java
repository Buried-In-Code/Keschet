package github.macro;

import github.macro.console.Colour;
import github.macro.pieces.Emperor;
import github.macro.players.ConsolePlayer;
import github.macro.players.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Macro303 on 2018-Feb-14.
 */
public class Boundary_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static Board board;
	private static Player player;
	private static Square start;
	private static Square end;

	@BeforeAll
	static void beforeAll() {
		board = new Board();
		player = new ConsolePlayer("Tester", Colour.YELLOW);
		start = board.getSquare(2, 2);
	}

	@BeforeEach
	void beforeEach() {
		start.setPiece(new Emperor(player));
		if (end != null)
			end.setPiece(null);
	}

	@AfterEach
	void afterEach(){
		board.draw();
	}

	@Test
	void test_distanceInMove() {
		end = board.getSquare(6, 6);
		assert end != null;
		boolean valid = Utils.validMovement(board, start, end);
		LOGGER.info("Distance In ==> " + valid);
		assertTrue(valid);
	}

	@Test
	void test_distanceOutMove() {
		end = board.getSquare(7, 7);
		assert end != null;
		boolean valid = Utils.validMovement(board, start, end);
		LOGGER.info("Distance Out ==> " + valid);
		assertFalse(valid);
	}
}