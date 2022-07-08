/**
 * 
 */
package chess_clock_app;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

import utils.PlayerClockException;

/**
 * @author SteveBrown
 *
 * 
 */
public class ChessClock {	
	@SuppressWarnings("unused")
	private final Instant START_TIME;
	private final Player PLAYER_LEFT;
	private final Player PLAYER_RIGHT;
	private final Clock CLOCK_TICK;
		
	private Player currentPlayer;
	
	public ChessClock(Player playerLeft, Player playerRight) {
		PLAYER_LEFT = playerLeft;
		PLAYER_RIGHT = playerRight;
	
		START_TIME = Instant.now();
		CLOCK_TICK = Clock.tickSeconds(ZoneId.of("Europe/Malta"));		
	}
	 
	public void pressLeft() throws PlayerClockException {
		if(isCurrentPlayer(PLAYER_LEFT)) {
			throw new PlayerClockException("The left player's clock is already running.");
		}	else if(isCurrentPlayer(PLAYER_RIGHT)) {
			PLAYER_RIGHT.getPlayerClock().stopClock();			
		}
		currentPlayer = PLAYER_LEFT;
		currentPlayer.getPlayerClock().startClock(CLOCK_TICK);
	}
	
	public void pressRight() {
		if(isCurrentPlayer(PLAYER_RIGHT)) {
			//throw
		}	else if(isCurrentPlayer(PLAYER_LEFT)) {
			PLAYER_LEFT.getPlayerClock().stopClock();			
		}
		currentPlayer = PLAYER_RIGHT;
		currentPlayer.getPlayerClock().startClock(CLOCK_TICK);
	}
		
	private boolean isCurrentPlayer(Player player) {
		if(Objects.isNull(currentPlayer)) {
			return false;
		}else if(currentPlayer.equals(player)) {
			return true;
		}
		return false;
	}
	
	public ChessClock stopClock() {
		if(Objects.nonNull(currentPlayer)) {
			currentPlayer.getPlayerClock().stopClock();	
		}
		return this;		
	}
	
	public Player getLeftPlayer() {
		return PLAYER_LEFT;
	}
	public Player getRightPlayer() {
		return PLAYER_RIGHT;
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public String toString() {
		return 
				String.format("Current clock -> %s ** %s", 
				PLAYER_LEFT, PLAYER_RIGHT);
	}
	
}