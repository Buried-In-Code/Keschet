package macro303.keschet;

import macro303.board_game.Board;
import macro303.board_game.Colour;
import macro303.board_game.Coordinates;
import macro303.board_game.Square;
import macro303.keschet.pieces.General;
import macro303.keschet.pieces.Piece;
import macro303.keschet.pieces.Scholar;
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
public class Scholar_Test {
	private static final Logger LOGGER = LogManager.getLogger();
	private static Board board;
	private static Square start;
	private static Square end;
	private static Square block;

	@BeforeAll
	static void beforeAll() {
		board = new Board(10);
		start = board.getSquare(new Coordinates(2, 2));
		block = board.getSquare(new Coordinates(4, 5));
		end = board.getSquare(new Coordinates(4, 4));
	}

	@BeforeEach
	void beforeEach(){
		start.setItem(new General(Colour.BLUE));
		block.setItem(null);
		end.setItem(new General(Colour.RED));
	}

	@Test
	void test_enemyBlockMove() {
		block.setItem(new Scholar(Colour.RED));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Enemy Scholar Block ==> " + valid);
		assertFalse(valid);
	}

	@Test
	void test_allyBlockMove() {
		block.setItem(new Scholar(Colour.BLUE));
		boolean valid = Util.validMovement(board, start, end);
		LOGGER.info("Ally Scholar Block ==> " + valid);
		assertTrue(valid);
	}
}
