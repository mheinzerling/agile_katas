class Game
{
	private final Grid grid;

	public Game(int numberOfRows, int numberOfColumns)
	{
		grid = new Grid(numberOfRows, numberOfColumns);
	}

	public void putTokenInColumn(Token token, int columnNumber) throws GameIsOverException
	{
		int rowWhereTokenIsPlaced = grid.put(token, columnNumber);
		checkIfGameIsOverByPlacingTokenIn(columnNumber, rowWhereTokenIsPlaced);
	}

	private void checkIfGameIsOverByPlacingTokenIn(int columnNumber, int rowWhereTokenIsPlaced) throws GameIsOverException
	{
		Token[] connectingTokens = grid.getRowOfTokensAlong(rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
		connectingTokens = grid.getColumnOfTokensAlong(columnNumber);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, rowWhereTokenIsPlaced);
		connectingTokens = grid.getLeftDiagonalOfTokensAlong(columnNumber, rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
		connectingTokens = grid.getRightDiagonalOfTokensAlong(columnNumber, rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
	}

	private void checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(Token[] connectingTokensIncludingPlacedToken,
		int indexOfPlacedToken) throws GameIsOverException
	{
		final int LEFT = 1;
		final int RIGHT = -1;
		checkIfThereAreFourConnectingTokensOfSameColorOnSide(connectingTokensIncludingPlacedToken, indexOfPlacedToken, LEFT);
		checkIfThereAreFourConnectingTokensOfSameColorOnSide(connectingTokensIncludingPlacedToken, indexOfPlacedToken, RIGHT);
	}

	private void checkIfThereAreFourConnectingTokensOfSameColorOnSide(Token[] rowOfTokensWhereTokenIsPlaced, int indexOfCurrentlyPlacedToken,
		int direction) throws GameIsOverException
	{
		int startingIndex = indexOfCurrentlyPlacedToken + 3 * -direction;
		Token tokenOfPlacedToken = rowOfTokensWhereTokenIsPlaced[indexOfCurrentlyPlacedToken];
		int numberOfTokensHavingTheSameColorAsPlacedToken = 1;
		for( int index = startingIndex; index != indexOfCurrentlyPlacedToken && isWithinBounds(rowOfTokensWhereTokenIsPlaced, index); index += direction )
		{
			if( areOfDifferentColors(tokenOfPlacedToken, rowOfTokensWhereTokenIsPlaced[index]) ) break;
			numberOfTokensHavingTheSameColorAsPlacedToken++;
			if( numberOfTokensHavingTheSameColorAsPlacedToken == 4 ) throw new GameIsOverException(tokenOfPlacedToken + " wins!");
		}
	}

	private boolean isWithinBounds(Token[] rowOfTokensWhereTokenIsPlaced, int index)
	{
		return index >= 0 && index < rowOfTokensWhereTokenIsPlaced.length;
	}

	private boolean areOfDifferentColors(Token tokenOfTokenPlaced, Token tokenOfConnectingToken)
	{
		return tokenOfTokenPlaced != tokenOfConnectingToken;
	}

	@Override
	public String toString()
	{
		return String.valueOf(grid);
	}
}
