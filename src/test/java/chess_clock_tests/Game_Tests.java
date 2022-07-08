/**
 * 
 */
package chess_clock_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import chess_clock_app.Game;
import chess_clock_app.WhitePlayerClock;
import utils.ProgPause;

/**
 * @author SteveBrown
 *
 */
class Game_Tests {
 
	@Test
	void test_gameObj() {
		Game game = Game.getBuilder().build("","");
		assertNotNull(game);
	}
	
	@Test
	void test_gameBuilder() {
		Game game = Game.getBuilder().build("Tom", "Jane");
		assertEquals("Jane", game.getBlackPlayer().getPlayerName());;
	}

	@Test
	void test_defaultGameClock() {
		Game game = Game.getBuilder().build("Tom", "Jane");
		
		assertEquals("Europe/Malta", game.getClock().getZone().toString());
	}
	
	@Test
	void test_injectGameClock() {
		Game game = 
			Game.getBuilder()
					.withClock(Clock.tickSeconds(ZoneId.of("Europe/Paris")))
					.build("Tom", "Jane");
		
		assertEquals("Europe/Paris", game.getClock().getZone().toString());
	}
	
	@Test
	void test_startGame() {
		Game game = Game.getBuilder().build("Tom", "Jane");
		game.startGame();
		
		assertTrue(game.getCurrentPlayer().getPlayerClock() instanceof WhitePlayerClock);
	}
	
	@Test
	void test_gameRecord() {
		Game game = Game.getBuilder().build("Tom", "Jane");
		game.startGame();
		
		ProgPause.forSeconds(2);		
		game.nextTurn();
		
		ProgPause.forSeconds(3);		
		game.nextTurn();
		
		ProgPause.forSeconds(4);		
		game.nextTurn();

		assertAll(() -> {
			assertTrue(game.getWhitePlayer().getRecord().equals(Arrays.asList(2L,4L)));
			assertTrue(game.getBlackPlayer().getRecord().equals(Arrays.asList(3L)));
		});		
	}
}
