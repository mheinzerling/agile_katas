import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ConnectFourGameTest
{
	private ConnectFourGame connectFourGame;
	private GameToken       player1Token;
	private GameToken       player2Token;

	@Before
	public void setUp() throws Exception
	{
		int numberOfRows = 6;
		int numberOfColumns = 7;
		connectFourGame = new ConnectFourGame(numberOfRows, numberOfColumns);
		player1Token = new GameToken(GameTokenColor.BLUE);
		player2Token = new GameToken(GameTokenColor.YELLOW);
	}

	@Test
	public void aPlayerConnectingFourHorizontalTokensOfSameColorShouldWinTheGame() throws Exception
	{
		connectFourGame.putTokenInColumn(player1Token, 0);
		connectFourGame.putTokenInColumn(player1Token, 1);
		connectFourGame.putTokenInColumn(player1Token, 2);
		try
		{
			connectFourGame.putTokenInColumn(player1Token, 3);
			printFailingMessageForNotWinningTheGame(player1Token);
		}
		catch( GameIsOverException ignore )
		{
		}
	}

	@Test
	public void aPlayerConnectingFourVerticalTokensOfSameColorShouldWinTheGame() throws Exception
	{
		connectFourGame.putTokenInColumn(player2Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 0);
		try
		{
			connectFourGame.putTokenInColumn(player2Token, 0);
			printFailingMessageForNotWinningTheGame(player2Token);
		}
		catch( GameIsOverException ignore )
		{
		}
	}

	@Test
	public void aPlayerNotConnectingFourVerticalTokensOfSameColorShouldNotWinTheGameYet() throws Exception
	{
		connectFourGame.putTokenInColumn(player2Token, 0);
	}

	@Test
	public void aPlayerConnectingFourLeftDiagonalTokensOfSameColorShouldWinTheGame() throws Exception
	{
		connectFourGame.putTokenInColumn(player1Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 1);
		connectFourGame.putTokenInColumn(player1Token, 2);
		connectFourGame.putTokenInColumn(player2Token, 3);
		connectFourGame.putTokenInColumn(player1Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 2);
		connectFourGame.putTokenInColumn(player1Token, 1);
		connectFourGame.putTokenInColumn(player2Token, 3);
		connectFourGame.putTokenInColumn(player1Token, 2);
		connectFourGame.putTokenInColumn(player2Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 1);
		connectFourGame.putTokenInColumn(player2Token, 3);
		connectFourGame.putTokenInColumn(player1Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 1);
		connectFourGame.putTokenInColumn(player1Token, 2);
		try
		{
			connectFourGame.putTokenInColumn(player1Token, 3);
			printFailingMessageForNotWinningTheGame(player1Token);
		}
		catch( GameIsOverException ignored )
		{
		}
	}

	@Test
	public void aPlayerConnectingFourRightDiagonalTokensShouldWinTheGame() throws Exception
	{
		connectFourGame.putTokenInColumn(player1Token, 0);
		connectFourGame.putTokenInColumn(player1Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 0);
		connectFourGame.putTokenInColumn(player2Token, 1);
		connectFourGame.putTokenInColumn(player1Token, 1);
		connectFourGame.putTokenInColumn(player2Token, 1);
		connectFourGame.putTokenInColumn(player2Token, 1);
		connectFourGame.putTokenInColumn(player1Token, 2);
		connectFourGame.putTokenInColumn(player2Token, 2);
		connectFourGame.putTokenInColumn(player1Token, 2);
		connectFourGame.putTokenInColumn(player1Token, 2);
		try
		{
			connectFourGame.putTokenInColumn(player2Token, 3);
			printFailingMessageForNotWinningTheGame(player2Token);
		}
		catch( GameIsOverException ignored )
		{
		}
	}

	private void printFailingMessageForNotWinningTheGame(GameToken token)
	{
		fail(String.format("%s should be considered a winner already!", token));
	}
}