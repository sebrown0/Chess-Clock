/**
 * 
 */
package chess_clock_app;

import utils.PlayerClockException;

/**
 * @author SteveBrown
 *
 * Press a button on the clock.
 */
public interface ClockPress {
	void press(Player curPlayer) throws PlayerClockException;
}
