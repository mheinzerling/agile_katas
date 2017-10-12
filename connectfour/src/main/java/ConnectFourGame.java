public class ConnectFourGame
{
	private ConnectFourGrid connectFourGrid;

	public ConnectFourGame(int numberOfRows, int numberOfColumns)
	{
		connectFourGrid = new ConnectFourGrid(numberOfRows, numberOfColumns);
	}

	public void putTokenInColumn(GameToken gameToken, int columnNumber) throws GameIsOverException
	{
		int rowWhereTokenIsPlaced = connectFourGrid.putTokenInColumn(gameToken, columnNumber);
		checkIfGameIsOverByPlacingTokenIn(columnNumber, rowWhereTokenIsPlaced);
	}

	private void checkIfGameIsOverByPlacingTokenIn(int columnNumber, int rowWhereTokenIsPlaced) throws GameIsOverException
	{
		GameTokenColor[] connectingTokens = connectFourGrid.getRowOfTokensAlong(rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
		connectingTokens = connectFourGrid.getColumnOfTokensAlong(columnNumber);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, rowWhereTokenIsPlaced);
		connectingTokens = connectFourGrid.getLeftDiagonalOfTokensAlong(columnNumber, rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
		connectingTokens = connectFourGrid.getRightDiagonalOfTokensAlong(columnNumber, rowWhereTokenIsPlaced);
		checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(connectingTokens, columnNumber);
	}

	private void checkIfThereAreFourConnectingTokensOfTheSameColorInConnectingTokensAlong(GameTokenColor[] connectingTokensIncludingPlacedToken,
		int indexOfPlacedToken) throws GameIsOverException
	{
		final int LEFT = 1;
		final int RIGHT = -1;
		checkIfThereAreFourConnectingTokensOfSameColorOnSide(connectingTokensIncludingPlacedToken, indexOfPlacedToken, LEFT);
		checkIfThereAreFourConnectingTokensOfSameColorOnSide(connectingTokensIncludingPlacedToken, indexOfPlacedToken, RIGHT);
	}

	private void checkIfThereAreFourConnectingTokensOfSameColorOnSide(GameTokenColor[] rowOfTokensWhereTokenIsPlaced, int indexOfCurrentlyPlacedToken,
		int direction) throws GameIsOverException
	{
		int startingIndex = indexOfCurrentlyPlacedToken + 3 * -direction;
		GameTokenColor colorOfPlacedToken = rowOfTokensWhereTokenIsPlaced[indexOfCurrentlyPlacedToken];
		int numberOfTokensHavingTheSameColorAsPlacedToken = 1;
		for( int index = startingIndex; index != indexOfCurrentlyPlacedToken && isWithinBounds(rowOfTokensWhereTokenIsPlaced, index); index += direction )
		{
			if( areOfDifferentColors(colorOfPlacedToken, rowOfTokensWhereTokenIsPlaced[index]) ) break;
			numberOfTokensHavingTheSameColorAsPlacedToken++;
			if( numberOfTokensHavingTheSameColorAsPlacedToken == 4 ) throw new GameIsOverException(colorOfPlacedToken + " wins!");
		}
	}

	private boolean isWithinBounds(GameTokenColor[] rowOfTokensWhereTokenIsPlaced, int index)
	{
		return index >= 0 && index < rowOfTokensWhereTokenIsPlaced.length;
	}

	private boolean areOfDifferentColors(GameTokenColor colorOfTokenPlaced, GameTokenColor colorOfConnectingToken)
	{
		return colorOfTokenPlaced != colorOfConnectingToken;
	}

	@Override
	public String toString()
	{
		return String.valueOf(connectFourGrid);
	}
}
