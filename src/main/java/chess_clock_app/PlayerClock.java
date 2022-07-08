/**
 * 
 */
package chess_clock_app;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 *
 * Individual clock for a player
 * -----------------------------
 * - When a player's turn starts the clock is started.
 * 
 * - When the player finishes their turn the clock is stopped.
 * 
 * - The elapsed time of the turn is calculated from the 
 * - 2 Instants turnStarted & clockStopped.
 * 
 * - The elapsed time of the turn is added to the overall record.
 */
public final class PlayerClock {
	private final List<Long> RECORD;
	
	private Instant turnStarted;
	private long elapsedSecs;
		
	public PlayerClock() {
		RECORD = new ArrayList<>();
	}

	public void startClock(Clock clockTick) {
		turnStarted = clockTick.instant();
	}
	
	public void stopClock() {
		final Instant clockStopped = Instant.now();		
		final long turn = ChronoUnit.SECONDS.between(turnStarted, clockStopped);
		if(turn > 0) {
			elapsedSecs += turn;	
			RECORD.add(turn);	
		}		
	}
	
	public long getElaspsedSeconds() {
		return elapsedSecs;
	}
	
	public List<Long> getRecord(){
		return RECORD;
	}
	
	@Override
	public String toString() {
		return String.format("TimeElapsed: %s", elapsedSecs);
	}
	
}
