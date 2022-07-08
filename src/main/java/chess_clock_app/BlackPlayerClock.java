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
public class BlackPlayerClock extends PlayerClock {

	public BlackPlayerClock(Clock clockTick) {		
		super(clockTick);		
	}

	@Override
	public void setOpponentClock(ClockAction opponentClock) 
		throws PlayerClockException {
		
		if(false==(opponentClock instanceof WhitePlayerClock)) {
			throw new PlayerClockException(
				"Black player must have WhiteClock for opponent.");
		}else {
			super.opponentClock = opponentClock;
		}		
	}		

}
