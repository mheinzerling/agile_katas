import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ConnectFourGridTest
{
	private GameToken       player1Token;
	private GameToken       player2Token;
	private ConnectFourGrid connectFourGrid;

	@Before
	public void setUp() throws Exception
	{
		int numberOfRows = 6;
		int numberOfColumns = 7;
		connectFourGrid = new ConnectFourGrid(numberOfRows, numberOfColumns);
		player1Token = new GameToken(GameTokenColor.BLUE);
		player2Token = new GameToken(GameTokenColor.YELLOW);
	}

	@Test
	public void initializing6x7Grid() throws Exception
	{
		int expectedNumberOfRows = 6;
		int expectedNumberOfColumns = 7;
		assertTrue(expectedNumberOfRows == connectFourGrid.getNumberOfRows());
		assertTrue(expectedNumberOfColumns == connectFourGrid.getNumberOfColumns());
	}

	@Test
	public void checkingNumberOfTokensInANonExistentColumnShouldRaiseAnException() throws Exception
	{
		int nonExistentColumn = 7;
		try
		{
			connectFourGrid.getNumberOfTokensIn(nonExistentColumn);
			fail();
		}
		catch( NonexistentColumnException ignored )
		{
		}
	}

	@Test
	public void puttingATokenInAnEmptyColumn() throws Exception
	{
		int columnNumber = 1;
		placePlayer1TokenIn(columnNumber);
		assertTrue(connectFourGrid.getNumberOfTokensIn(columnNumber) == 1);
	}

	@Test
	public void puttingATokenInColumnWithFiveTokens() throws Exception
	{
		int columnNumber = 5;
		alternatelyPlaceSixTokensIn(columnNumber);
		assertTrue(connectFourGrid.getNumberOfTokensIn(columnNumber) == 6);
	}

	@Test
	public void placingATokenInAColumnFilledWithTokensShouldRaiseAnException() throws Exception
	{
		int columnNumber = 6;
		alternatelyPlaceSixTokensIn(columnNumber);
		try
		{
			placePlayer2TokenIn(columnNumber);
			fail();
		}
		catch( FullColumnException ignored )
		{
		}
	}

	private void alternatelyPlaceSixTokensIn(int columnNumber) throws GameIsOverException
	{
		placePlayer1TokenIn(columnNumber);
		placePlayer2TokenIn(columnNumber);
		placePlayer1TokenIn(columnNumber);
		placePlayer2TokenIn(columnNumber);
		placePlayer1TokenIn(columnNumber);
		placePlayer2TokenIn(columnNumber);
	}

	private void placePlayer2TokenIn(int columnNumber) throws GameIsOverException
	{
		connectFourGrid.putTokenInColumn(player2Token, columnNumber);
	}

	private void placePlayer1TokenIn(int columnNumber) throws GameIsOverException
	{
		connectFourGrid.putTokenInColumn(player1Token, columnNumber);
	}

	@Test
	public void placingATokenInANonExistentColumnShouldRaiseAnException() throws Exception
	{
		int nonExistentColumn = 7;
		try
		{
			placePlayer1TokenIn(nonExistentColumn);
			fail();
		}
		catch( NonexistentColumnException ignored )
		{
		}
	}
}