import java.util.Arrays;

class Grid
{
	private final Token[][] grid;
	private final int[]     nextTokenHeights;

	public Grid(int numberOfRows, int numberOfColumns)
	{
		grid = new Token[numberOfColumns][numberOfRows];
		nextTokenHeights = new int[numberOfColumns];
		for( int c = 0; c < numberOfColumns; c++ )
		{
			for( int r = 0; r < numberOfRows; r++ )
			{
				grid[c][r] = Token.NONE;
			}
			nextTokenHeights[c] = 0;
		}
	}

	public Token[] getRightDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber >= 0 && columnNumber < getNumberOfColumns(); rowNumber--, columnNumber++ )
			tokens[columnNumber] = grid[columnNumber][rowNumber];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced; rowNumber < getNumberOfRows() && columnNumber >= 0; rowNumber++, columnNumber-- )
			tokens[columnNumber] = grid[columnNumber][rowNumber];
		return tokens;
	}

	public Token[] getLeftDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced; rowNumber >= 0 && columnNumber >= 0; rowNumber--, columnNumber-- )
			tokens[columnNumber] = grid[columnNumber][rowNumber];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber < getNumberOfRows() && columnNumber < getNumberOfColumns(); rowNumber++, columnNumber++ )
			tokens[columnNumber] = grid[columnNumber][rowNumber];
		return tokens;
	}

	public int put(Token token, int column) throws FullColumnException, NonexistentColumnException
	{
		checkIfColumnExists(column);
		if( nextTokenHeights[column] >= getNumberOfRows() ) throw new FullColumnException("Column is full!");
		grid[column][nextTokenHeights[column]] = token;
		nextTokenHeights[column]++;
		return nextTokenHeights[column] - 1;
	}

	private void checkIfColumnExists(int columnNumber) throws NonexistentColumnException
	{
		if( columnNumber < 0 || columnNumber >= grid.length ) throw new NonexistentColumnException("Column does not exist");
	}

	public Token[] getColumnOfTokensAlong(int columnNumber)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int rowNumber = 0; rowNumber < getNumberOfRows(); rowNumber++ )
		{
			tokens[rowNumber] = grid[columnNumber][rowNumber];
		}
		return tokens;
	}

	public Token[] getRowOfTokensAlong(int rowWhereTokenIsPlaced)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int columnNumber = 0; columnNumber < getNumberOfColumns(); columnNumber++ )
		{
			tokens[columnNumber] = grid[columnNumber][rowWhereTokenIsPlaced];
		}
		return tokens;
	}

	public int getNumberOfTokensIn(int columnNumber) throws NonexistentColumnException
	{
		checkIfColumnExists(columnNumber);
		return nextTokenHeights[columnNumber];
	}

	public int getNumberOfRows()
	{
		return grid[0].length;
	}

	public int getNumberOfColumns()
	{
		return grid.length;
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
