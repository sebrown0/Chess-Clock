/**
 * 
 */
package utils;

/**
 * @author SteveBrown
 *
 * Simple class to pause the current thread 
 * for a specified duration.
 */
public class ProgPause {
		
	/**
	 * 
	 * @param secs number of seconds to pause the thread
	 */	
	public static void forSeconds(long secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
