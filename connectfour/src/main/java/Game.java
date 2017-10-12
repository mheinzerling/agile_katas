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
		Color[] connectingTokens = grid.getRowOfTokensAlong(rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
		connectingTokens = grid.getColumnOfTokensAlong(columnNumber);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, rowWhereTokenIsPlaced);
		connectingTokens = grid.getLeftDiagonalOfTokensAlong(columnNumber, rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
		connectingTokens = grid.getRightDiagonalOfTokensAlong(columnNumber, rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
	}

	private void checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(Color[] connectingTokensIncludingPlacedToken,
		int indexOfPlacedToken) throws GameIsOverException
	{
		final int LEFT = 1;
		final int RIGHT = -1;
		checkIfThereAreFourConnectingTokensOfSameColorOnSide(connectingTokensIncludingPlacedToken, indexOfPlacedToken, LEFT);
		checkIfThereAreFourConnectingTokensOfSameColorOnSide(connectingTokensIncludingPlacedToken, indexOfPlacedToken, RIGHT);
	}

	private void checkIfThereAreFourConnectingTokensOfSameColorOnSide(Color[] rowOfTokensWhereTokenIsPlaced, int indexOfCurrentlyPlacedToken,
		int direction) throws GameIsOverException
	{
		int startingIndex = indexOfCurrentlyPlacedToken + 3 * -direction;
		Color colorOfPlacedToken = rowOfTokensWhereTokenIsPlaced[indexOfCurrentlyPlacedToken];
		int numberOfTokensHavingTheSameColorAsPlacedToken = 1;
		for( int index = startingIndex; index != indexOfCurrentlyPlacedToken && isWithinBounds(rowOfTokensWhereTokenIsPlaced, index); index += direction )
		{
			if( areOfDifferentColors(colorOfPlacedToken, rowOfTokensWhereTokenIsPlaced[index]) ) break;
			numberOfTokensHavingTheSameColorAsPlacedToken++;
			if( numberOfTokensHavingTheSameColorAsPlacedToken == 4 ) throw new GameIsOverException(colorOfPlacedToken + " wins!");
		}
	}

	private boolean isWithinBounds(Color[] rowOfTokensWhereTokenIsPlaced, int index)
	{
		return index >= 0 && index < rowOfTokensWhereTokenIsPlaced.length;
	}

	private boolean areOfDifferentColors(Color colorOfTokenPlaced, Color colorOfConnectingToken)
	{
		return colorOfTokenPlaced != colorOfConnectingToken;
	}

	@Override
	public String toString()
	{
		return String.valueOf(grid);
	}
}
