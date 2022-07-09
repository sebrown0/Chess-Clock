/**
 * 
 */
package chess_clock_app;

import java.time.Clock;

/**
 * @author SteveBrown
 *
 */
public class WhitePlayerClock extends PlayerClock {
 
	public WhitePlayerClock(Clock clockTick) {		
		super(clockTick);					
	}		
	
	@Override
	public PlayerClock setOpponentClock() {
		opponentClock = new BlackPlayerClock(clockTick);
		return this;
	}			
	
}
