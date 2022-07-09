/**
 * 
 */
package chess_clock_app;

import java.time.Clock;

/**
 * @author SteveBrown
 *
 */
public class BlackPlayerClock extends PlayerClock { 

	public BlackPlayerClock(Clock clockTick) {		
		super(clockTick);		
	}

	@Override
	public PlayerClock setOpponentClock() {
		opponentClock = new WhitePlayerClock(clockTick);
		return this;
	}		

}
