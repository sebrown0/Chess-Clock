/**
 * 
 */
package chess_clock_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import chess_clock_app.BlackPlayerClock;
import chess_clock_app.ClockAction;
import chess_clock_app.Player;
import chess_clock_app.PlayerClock;
import chess_clock_app.WhitePlayerClock;
import utils.ProgPause;

/**
 * @author SteveBrown
 *
 */
class Player_Tests {
	private static final Clock CLK = 
			Clock.tickSeconds(ZoneId.of("Europe/Malta"));
	
	@Test
	void test_playerObj() {
		Player p = new Player(null, null);
		
		assertNotNull(p);
	}

	@Test
	void test_playerName() {
		Player p = new Player("TestName", null);
		
		assertEquals("TestName", p.getPlayerName());
	}

	@Test
	void test_clockType() {
		ClockAction plClk = new BlackPlayerClock(CLK);
		
		assertTrue(plClk instanceof BlackPlayerClock);
	}
	
	@Test
	void test_playerClock() {		
		ClockAction plClk = new BlackPlayerClock(CLK);
		Player p = new Player("TestName", plClk);
		
		assertTrue(p.getPlayerClock() instanceof BlackPlayerClock);
	}
	
	@Test
	void test_clockType_isWhiteClockType() {
		Player p = 
				new Player("White", new WhitePlayerClock(CLK));
		
		assertTrue(p.isWhitePlayer());
	}
	
	@Test
	void test_clockType_not_isWhiteClockType() {
		Player p = 
				new Player("Black", new BlackPlayerClock(CLK));
		
		assertFalse(p.isWhitePlayer());
	}
	
	@Test
	void test_playerRecord() {
		Player p = 
				new Player("Black", new BlackPlayerClock(CLK));
		
		assertTrue(p.getRecord().size() == 0);
	}

	@Test
	void test_startTurn() {
		Player p = 
				new Player("Black", new BlackPlayerClock(CLK));
				
		PlayerClock plClk = (PlayerClock) p.getPlayerClock();
		p.startTurn();		
		
		assertNotNull(plClk.getTurnStarted());		
	}
	
	@Test
	void test_stopTurn() {
		Player p = 
				new Player("Black", new BlackPlayerClock(CLK));
				
		PlayerClock plClk = (PlayerClock) p.getPlayerClock();
		p.startTurn();		
		ProgPause.forSeconds(1);
		p.stopTurn();
		
		assertAll(() -> {
			assertNull(plClk.getTurnStarted());
			assertTrue(plClk.getElaspsedSeconds() == 1);
		});
				
	}
	
	@Test
	void test_toString() {
		Player p = 
				new Player("Test", new BlackPlayerClock(CLK));
				
		p.startTurn();		
		ProgPause.forSeconds(1);
		p.stopTurn();
		
		assertEquals("Player = Test - Time Elapsed = 1", p.toString());
	}
	
	@Test
	void test_hashCode() {
		Player p = 
				new Player("Test", new BlackPlayerClock(CLK));
		
		assertNotNull(Integer.valueOf(p.hashCode()));
	}
	
	@Test
	void test_equals_identity_isTrue() {
		Player p1 = 
				new Player("Test", new BlackPlayerClock(CLK));
		Player p2 = p1;
		
		assertTrue(p1.equals(p2));
	}
	
	@Test
	void test_equals_identity_isFalse() {
		Player p1 = 
				new Player("Test", new BlackPlayerClock(CLK));
		String p2 = "";
		
		assertFalse(p1.equals(p2));
	}
	
	@Test
	void test_equals_value_isTrue() {
		Player p1 = 
				new Player("Test", new BlackPlayerClock(CLK));
		Player p2 = 
				new Player("Test", new BlackPlayerClock(CLK));
		
		assertTrue(p1.equals(p2));
	}
	
	@Test
	void test_equals_value_isFalse() {
		Player p1 = 
				new Player("Test A", new BlackPlayerClock(CLK));
		Player p2 = 
				new Player("Test B", new BlackPlayerClock(CLK));
		
		assertFalse(p1.equals(p2));
	}
	
}
