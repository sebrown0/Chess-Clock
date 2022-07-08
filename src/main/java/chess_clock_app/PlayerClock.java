/**
 * 
 */
package chess_clock_app;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utils.PlayerClockException;

/**
 * @author SteveBrown
 *
 */
public abstract class PlayerClock  implements ClockAction {	
	private final List<Long> RECORD;
	private final Clock clockTick;
		
	private Instant turnStarted;
	private long elapsedSecs;
	
	protected  ClockAction opponentClock;
	
	public PlayerClock(Clock clockTick) {		
		this.clockTick = clockTick;		
		RECORD = new ArrayList<>();			
	}
	
	public abstract void 
		setOpponentClock(ClockAction opponentClock) 
				throws PlayerClockException ;
	
	public long getElaspsedSeconds() {
		return elapsedSecs;
	}
	
	public List<Long> getRecord(){
		return RECORD;
	}
	
	@Override
	public void start() {
		if(Objects.nonNull(opponentClock)) {
			opponentClock.stop();
		}
		turnStarted = clockTick.instant();
	}

	@Override
	public void stop() {
		if(Objects.nonNull(turnStarted)) {
			final Instant clockStopped = Instant.now();		
			final long turn = 
					ChronoUnit.SECONDS.between(turnStarted, clockStopped);
			turnStarted = null;
			if(turn > 0) {
				elapsedSecs += turn;	
				RECORD.add(turn);	
			}		
		}
	}
		
	@Override
	public String toString() {
		return String.format("TimeElapsed: %s", elapsedSecs);
	}
	
	public ClockAction getOpponentClock() {
		return opponentClock;
	}
	
	public Instant getTurnStarted() {
		return turnStarted;
	}
}
