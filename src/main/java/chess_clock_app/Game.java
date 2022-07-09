/**
 * 
 */
package chess_clock_app;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Objects;

/**
 * @author SteveBrown
 *
 */
public final class Game {
	private final Player whitePlayer;
	private final Player blackPlayer;	
	private final Clock clock;
	
	private Player currentPlayer;
	
	private Game(GameBuilder builder) {
		whitePlayer = builder.whitePlayer;
		blackPlayer = builder.blackPlayer;
		clock = builder.clk;
	}
	
	public void startGame() {
		currentPlayer = whitePlayer;
		whitePlayer.startTurn();
	} 
	
	public void nextTurn() {
		stopTurn();
		executePlayerTurn();
	}
	
	private void stopTurn() {
		if(Objects.nonNull(currentPlayer)) {
			currentPlayer.stopTurn();
		}
	}
	
	private void executePlayerTurn() {
		togglePlayer().startTurn();
	}
	
	private Player togglePlayer() {
		if(currentPlayer.isWhitePlayer()) {
			currentPlayer = blackPlayer;
		}else {
			currentPlayer = whitePlayer;
		}
		return currentPlayer;
	} 

	public static GameBuilder getBuilder() {
		return new Game.GameBuilder();
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public Player getWhitePlayer() {
		return whitePlayer;
	}
	public Player getBlackPlayer() {
		return blackPlayer;
	}
	public Clock getClock() {
		return clock;
	}

	public static class GameBuilder {
		private Player whitePlayer;
		private Player blackPlayer;
		private String whitePlName;
		private String blackPlName;
		private Clock clk;
		
		public Game build(String whitePlName, String blackPlName) {
			this.whitePlName = whitePlName;
			this.blackPlName = blackPlName;
			
			configureGame();
			
			return new Game(this);
		}
		
		public GameBuilder withClock(Clock clk) {
			this.clk = clk;
			return this;
		}
					
		private void configureGame() {
			setClock();
			
			WhitePlayerClock leftClk = new WhitePlayerClock(clk);
			BlackPlayerClock rightClk = new BlackPlayerClock(clk);
			
//			try {
//				leftClk.setOpponentClock(rightClk);
//				rightClk.setOpponentClock(leftClk);
//			} catch (PlayerClockException e) {
//				System.out.println(e.getMessage() + "\nQUITTING");
//				System.exit(0);
//			}
		
			whitePlayer = new Player(whitePlName, leftClk);
			blackPlayer = new Player(blackPlName, rightClk);
			
		}
		
		private void setClock() {
			if (Objects.isNull(clk)) {  
				clk = Clock.tickSeconds(ZoneId.of("Europe/Malta"));
			}
		}
		
	}	
	
}
