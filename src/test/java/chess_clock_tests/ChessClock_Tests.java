/**
 * 
 */
package chess_clock_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import chess_clock_app.ChessClock;
import chess_clock_app.Player;
import chess_clock_app.PlayerClock;
import utils.PlayerClockException;
import utils.ProgPause;

/**
 * @author SteveBrown
 *
 */
class ChessClock_Tests {

	@Test
	void test_playerObject() {
		Player left = new Player("Tom");
		assertNotNull(left);
	}
	
	@Test
	void test_playerName() {
		Player left = new Player("Tom");
		assertTrue(left.getPlayerName().equals("Tom"));
	}
	
	@Test
	void test_() {
		
	}
	
	@Test
	void test_chessClockObject() {
		ChessClock c = new ChessClock(null, null);
		assertNotNull(c);
	}

	@Test
	void test_playerClockObject() {
		PlayerClock pc = new PlayerClock();
		assertNotNull(pc);
	}
	
//	@Test
//	void test_currentPlayer() {
//		PlayerClock left = new PlayerClock();
//		PlayerClock right = new PlayerClock();
//		ChessClock chessClock = new ChessClock(left, right);
//		
//		chessClock.pressLeft();
//		
//		assertEquals("Tom", chessClock.getCurrentPlayer().getPlayerName());
//	}
	
	@Test
	void test_chessClock_toString() {		
		ChessClock chessClock = 
				new ChessClock(new Player("Tom"), new Player("Jane"));
		
		assertEquals(
				"Current clock -> Player = Tom - Time Elapsed = 0 ** Player = Jane - Time Elapsed = 0", 
				chessClock.toString());
	}
	
	@Test
	void test_elapsedSeconds() throws PlayerClockException {
		ChessClock chessClock = 
				new ChessClock(new Player("Tom"), new Player("Jane"));
		
		chessClock.pressLeft();
		ProgPause.forSeconds(2);				
		chessClock.stopClock();
		
		assertTrue(chessClock.getCurrentPlayer().getPlayerClock().getElaspsedSeconds() == 2);		
	}
	
	@Test
	void test_throwsPlayerClockEx() throws PlayerClockException {
		ChessClock chessClock = 
				new ChessClock(new Player("Tom"), new Player("Jane"));
			
		chessClock.pressLeft();
		
		assertThrows(
				PlayerClockException.class, 
				() -> { chessClock.pressLeft(); });
	}
	
	@Test
	void test_chessClock() throws PlayerClockException {
		ChessClock chessClock = 
				new ChessClock(new Player("Tom"), new Player("Jane"));
		
		chessClock.pressLeft();
		ProgPause.forSeconds(2);
		
		chessClock.pressRight();
		ProgPause.forSeconds(1);
		
		chessClock.pressLeft();
		ProgPause.forSeconds(5);
		
		chessClock.pressRight();
		ProgPause.forSeconds(3);
		
		chessClock.pressLeft();		
		chessClock.stopClock();
		
		assertAll(() -> {
			assertTrue(chessClock.getLeftPlayer().getPlayerClock().getRecord().equals(Arrays.asList(2L,5L)));
			assertTrue(chessClock.getRightPlayer().getPlayerClock().getRecord().equals(Arrays.asList(1L,3L)));
		});
	}
	
}
