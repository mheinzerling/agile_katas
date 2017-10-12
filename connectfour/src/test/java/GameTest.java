import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class GameTest
{
	private Game  game;
	private Token player1Token;
	private Token player2Token;

	@Before
	public void setUp() throws Exception
	{
		int numberOfRows = 6;
		int numberOfColumns = 7;
		game = new Game(numberOfRows, numberOfColumns);
		player1Token = new Token(Color.BLUE);
		player2Token = new Token(Color.YELLOW);
	}

	@Test
	public void aPlayerConnectingFourHorizontalTokensOfSameColorShouldWinTheGame() throws Exception
	{
		game.putTokenInColumn(player1Token, 0);
		game.putTokenInColumn(player1Token, 1);
		game.putTokenInColumn(player1Token, 2);
		try
		{
			game.putTokenInColumn(player1Token, 3);
			printFailingMessageForNotWinningTheGame(player1Token);
		}
		catch( GameIsOverException ignore )
		{
		}
	}

	@Test
	public void aPlayerConnectingFourVerticalTokensOfSameColorShouldWinTheGame() throws Exception
	{
		game.putTokenInColumn(player2Token, 0);
		game.putTokenInColumn(player2Token, 0);
		game.putTokenInColumn(player2Token, 0);
		try
		{
			game.putTokenInColumn(player2Token, 0);
			printFailingMessageForNotWinningTheGame(player2Token);
		}
		catch( GameIsOverException ignore )
		{
		}
	}

	@Test
	public void aPlayerNotConnectingFourVerticalTokensOfSameColorShouldNotWinTheGameYet() throws Exception
	{
		game.putTokenInColumn(player2Token, 0);
	}

	@Test
	public void aPlayerConnectingFourLeftDiagonalTokensOfSameColorShouldWinTheGame() throws Exception
	{
		game.putTokenInColumn(player1Token, 0);
		game.putTokenInColumn(player2Token, 1);
		game.putTokenInColumn(player1Token, 2);
		game.putTokenInColumn(player2Token, 3);
		game.putTokenInColumn(player1Token, 0);
		game.putTokenInColumn(player2Token, 2);
		game.putTokenInColumn(player1Token, 1);
		game.putTokenInColumn(player2Token, 3);
		game.putTokenInColumn(player1Token, 2);
		game.putTokenInColumn(player2Token, 0);
		game.putTokenInColumn(player2Token, 1);
		game.putTokenInColumn(player2Token, 3);
		game.putTokenInColumn(player1Token, 0);
		game.putTokenInColumn(player2Token, 1);
		game.putTokenInColumn(player1Token, 2);
		try
		{
			game.putTokenInColumn(player1Token, 3);
			printFailingMessageForNotWinningTheGame(player1Token);
		}
		catch( GameIsOverException ignored )
		{
		}
	}

	@Test
	public void aPlayerConnectingFourRightDiagonalTokensShouldWinTheGame() throws Exception
	{
		game.putTokenInColumn(player1Token, 0);
		game.putTokenInColumn(player1Token, 0);
		game.putTokenInColumn(player2Token, 0);
		game.putTokenInColumn(player2Token, 0);
		game.putTokenInColumn(player2Token, 1);
		game.putTokenInColumn(player1Token, 1);
		game.putTokenInColumn(player2Token, 1);
		game.putTokenInColumn(player2Token, 1);
		game.putTokenInColumn(player1Token, 2);
		game.putTokenInColumn(player2Token, 2);
		game.putTokenInColumn(player1Token, 2);
		game.putTokenInColumn(player1Token, 2);
		try
		{
			game.putTokenInColumn(player2Token, 3);
			printFailingMessageForNotWinningTheGame(player2Token);
		}
		catch( GameIsOverException ignored )
		{
		}
	}

	private void printFailingMessageForNotWinningTheGame(Token token)
	{
		fail(String.format("%s should be considered a winner already!", token));
	}
}