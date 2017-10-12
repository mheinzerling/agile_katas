import java.util.Arrays;
import java.util.List;

class ConnectFourGrid
{
	private final List<ConnectFourColumn> connectFourColumns;

	public ConnectFourGrid(int numberOfRows, int numberOfColumns)
	{
		connectFourColumns = Arrays.asList(new ConnectFourColumn[numberOfColumns]);
		for( int i = 0; i < connectFourColumns.size(); i++ )
		{
			connectFourColumns.set(i, new ConnectFourColumn(numberOfRows));
		}
	}

	public GameTokenColor[] getRightDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		GameTokenColor[] gameTokenColors = new GameTokenColor[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber >= 0 && columnNumber < getNumberOfColumns(); rowNumber--, columnNumber++ )
			gameTokenColors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber < getNumberOfRows() && columnNumber >= 0; rowNumber++, columnNumber-- )
			gameTokenColors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		return gameTokenColors;
	}

	public GameTokenColor[] getLeftDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		GameTokenColor[] gameTokenColors = new GameTokenColor[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced; rowNumber >= 0 && columnNumber >= 0; rowNumber--, columnNumber-- )
			gameTokenColors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber < getNumberOfRows() && columnNumber < getNumberOfColumns(); rowNumber++, columnNumber++ )
			gameTokenColors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		return gameTokenColors;
	}

	public int put(GameToken token, int column)
	{
		checkIfColumnExists(column);
		return connectFourColumns.get(column).place(token);
	}

	private void checkIfColumnExists(int columnNumber)
	{
		if( columnNumber < 0 || columnNumber >= connectFourColumns.size() ) throw new NonexistentColumnException("Column does not exist");
	}

	public GameTokenColor[] getColumnOfTokensAlong(int columnNumber)
	{
		GameTokenColor[] gameTokenColors = new GameTokenColor[getNumberOfColumns()];
		for( int rowNumber = 0; rowNumber < getNumberOfRows(); rowNumber++ )
		{
			gameTokenColors[rowNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		}
		return gameTokenColors;
	}

	private GameTokenColor getColorOfTokenPlacedIn(int rowWhereTokenIsPlaced, int columnNumber)
	{
		return connectFourColumns.get(columnNumber).getColorOfTokenPlacedIn(rowWhereTokenIsPlaced);
	}

	public GameTokenColor[] getRowOfTokensAlong(int rowWhereTokenIsPlaced)
	{
		GameTokenColor[] gameTokenColors = new GameTokenColor[getNumberOfColumns()];
		for( int columnNumber = 0; columnNumber < getNumberOfColumns(); columnNumber++ )
		{
			gameTokenColors[columnNumber] = getColorOfTokenPlacedIn(rowWhereTokenIsPlaced, columnNumber);
		}
		return gameTokenColors;
	}

	public int getNumberOfTokensIn(int columnNumber)
	{
		checkIfColumnExists(columnNumber);
		return connectFourColumns.get(columnNumber).getCurrentNumberOfTokens();
	}

	public int getNumberOfRows()
	{
		return connectFourColumns.get(0).getNumberOfRows();
	}

	public int getNumberOfColumns()
	{
		return connectFourColumns.size();
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < getNumberOfRows(); i++ )
		{
			sb.append(Arrays.toString(getRowOfTokensAlong(i)));
			sb.append("\n");
		}
		return sb.toString();
	}
}
