/**
 * 
 */
package chess_clock_app;

import java.util.List;
import java.util.Objects;

/**
 * @author SteveBrown
 *
 * Simple class to represent a chess player.
 * 
 */
public final class Player {
	private final String PL_NAME;
	private final ClockAction TIMER;
	
	public Player(String plName, ClockAction c) {
		PL_NAME = plName;
		TIMER = c;
	}

	public void startTurn() {
		TIMER.start();
	}
	
	public void stopTurn() {
		TIMER.stop();		
	}
	
	public String getPlayerName() {
		return PL_NAME;
	}
	
	public ClockAction getPlayerClock() {
		return TIMER;
	}
	
	public List<Long> getRecord(){
		return ((PlayerClock) TIMER).getRecord();
	}

	public boolean isWhitePlayer() {
		return TIMER instanceof WhitePlayerClock;
	}
	
	@Override
	public String toString() {		
		return String.format(
				"Player = %s - Time Elapsed = %s", 
				PL_NAME, ((PlayerClock)TIMER).getElaspsedSeconds());
	}

	@Override
	public int hashCode() {
		return Objects.hash(PL_NAME, TIMER);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(PL_NAME, other.PL_NAME);
	}
		
}
