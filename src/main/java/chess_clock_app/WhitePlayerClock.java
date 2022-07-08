/**
 * 
 */
package chess_clock_app;

import java.time.Clock;

import utils.PlayerClockException;

/**
 * @author SteveBrown
 *
 */
public class WhitePlayerClock extends PlayerClock {

	public WhitePlayerClock(Clock clockTick) {		
		super(clockTick);					
	}		
	
	@Override
	public void setOpponentClock(ClockAction opponentClock) 
		throws PlayerClockException {
		
		if(false==(opponentClock instanceof BlackPlayerClock)) {			
			throw new PlayerClockException(
					"White player must have BlackClock for opponent.");
		}else {
			super.opponentClock = opponentClock;
		}
	}		
	
}
