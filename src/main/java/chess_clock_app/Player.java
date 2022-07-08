/**
 * 
 */
package chess_clock_app;

import java.util.Objects;

/**
 * @author SteveBrown
 *
 * Simple class to represent a chess player.
 * 
 */
public final class Player {
	private final String PL_NAME;
	private final PlayerClock TIMER;
	
	public Player(String plName) {
		PL_NAME = plName;
		TIMER = new PlayerClock();
	}

	public String getPlayerName() {
		return PL_NAME;
	}
	
	public PlayerClock getPlayerClock() {
		return TIMER;
	}

	@Override
	public String toString() {
		return String.format(
				"Player = %s - Time Elapsed = %s", 
				PL_NAME, TIMER.getElaspsedSeconds());
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
