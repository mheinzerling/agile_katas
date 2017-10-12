import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class GridTest
{
	private Token player1Token;
	private Token player2Token;
	private Grid  grid;

	@Before
	public void setUp() throws Exception
	{
		int numberOfRows = 6;
		int numberOfColumns = 7;
		grid = new Grid(numberOfRows, numberOfColumns);
		player1Token = Token.BLUE;
		player2Token = Token.YELLOW;
	}

	@Test
	public void initializing6x7Grid() throws Exception
	{
		int expectedNumberOfRows = 6;
		int expectedNumberOfColumns = 7;
		assertTrue(expectedNumberOfRows == grid.getNumberOfRows());
		assertTrue(expectedNumberOfColumns == grid.getNumberOfColumns());
	}

	@Test
	public void checkingNumberOfTokensInANonExistentColumnShouldRaiseAnException() throws Exception
	{
		int nonExistentColumn = 7;
		try
		{
			grid.getNumberOfTokensIn(nonExistentColumn);
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
		assertTrue(grid.getNumberOfTokensIn(columnNumber) == 1);
	}

	@Test
	public void puttingATokenInColumnWithFiveTokens() throws Exception
	{
		int columnNumber = 5;
		alternatelyPlaceSixTokensIn(columnNumber);
		assertTrue(grid.getNumberOfTokensIn(columnNumber) == 6);
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

	private void alternatelyPlaceSixTokensIn(int columnNumber) throws GameIsOverException, FullColumnException, NonexistentColumnException
	{
		placePlayer1TokenIn(columnNumber);
		placePlayer2TokenIn(columnNumber);
		placePlayer1TokenIn(columnNumber);
		placePlayer2TokenIn(columnNumber);
		placePlayer1TokenIn(columnNumber);
		placePlayer2TokenIn(columnNumber);
	}

	private void placePlayer2TokenIn(int columnNumber) throws FullColumnException, NonexistentColumnException
	{
		grid.put(player2Token, columnNumber);
	}

	private void placePlayer1TokenIn(int columnNumber) throws FullColumnException, NonexistentColumnException
	{
		grid.put(player1Token, columnNumber);
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