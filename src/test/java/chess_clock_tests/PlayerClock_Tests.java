package chess_clock_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import chess_clock_app.BlackPlayerClock;
import chess_clock_app.PlayerClock;
import chess_clock_app.WhitePlayerClock;
import utils.PlayerClockException;
import utils.ProgPause;

/**
 * @author SteveBrown
 *
 * 
 */
class PlayerClock_Tests {
	
	private static final Clock CLK = 
			Clock.tickSeconds(ZoneId.of("Europe/Malta"));
	
	@Test
	void test_playerClockObj() {
		PlayerClock plClk = new BlackPlayerClock(CLK);
		assertNotNull(plClk);
	}

	@Test
	void test_setBlackOpponentsClock_valid() throws PlayerClockException {
		PlayerClock plClk1 = new WhitePlayerClock(CLK);
		PlayerClock plClk2 = new BlackPlayerClock(CLK);
		
		plClk2.setOpponentClock(plClk1);
		assertTrue(plClk2.getOpponentClock() instanceof WhitePlayerClock);
	}
	
	@Test
	void test_setWhiteOpponentsClock_valid() throws PlayerClockException {
		PlayerClock plClk1 = new WhitePlayerClock(CLK);
		PlayerClock plClk2 = new BlackPlayerClock(CLK);
		
		plClk1.setOpponentClock(plClk2);
		assertTrue(plClk1.getOpponentClock() instanceof BlackPlayerClock);
	}
	
	@Test
	void test_setOpponentsClock_invalid() {
		PlayerClock plClk1 = new WhitePlayerClock(CLK);
		PlayerClock plClk2 = new WhitePlayerClock(CLK);
		
		try {
			plClk2.setOpponentClock(plClk1);
		} catch (PlayerClockException e) {
			assertNull(plClk2.getOpponentClock());
		}		
	}
	
	@Test
	void test_getOppenentClock() throws PlayerClockException {
		PlayerClock plClk1 = new WhitePlayerClock(CLK);
		PlayerClock plClk2 = new BlackPlayerClock(CLK);
		
		plClk2.setOpponentClock(plClk1);
		PlayerClock opp = (PlayerClock) plClk2.getOpponentClock();
		
		assertTrue(plClk1.equals(opp));
	} 
	
	@Test
	void test_start() {
		PlayerClock plClk = new BlackPlayerClock(CLK);
		plClk.start();
		
		assertNotNull(plClk.getTurnStarted());
	}
	@Test
	void test_stop() {
		PlayerClock plClk = new BlackPlayerClock(CLK);
		plClk.start();
		ProgPause.forSeconds(1);
		plClk.stop();
		
		assertAll(() -> {
			assertNull(plClk.getTurnStarted());
			assertEquals(1, plClk.getElaspsedSeconds());
		});		
	}
	
	@Test
	void test_toString() {
		PlayerClock plClk = new BlackPlayerClock(CLK);
		assertEquals("TimeElapsed: 0", plClk.toString());
	}
	
}
