import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Grid
{
	private final List<Column> columns;

	public Grid(int numberOfRows, int numberOfColumns)
	{
		columns = new ArrayList<Column>(numberOfColumns);
		for( int i = 0; i < numberOfColumns; i++ )
			columns.add(new Column(numberOfRows));
	}

	public Token[] getRightDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber >= 0 && columnNumber < getNumberOfColumns(); rowNumber--, columnNumber++ )
			tokens[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced; rowNumber < getNumberOfRows() && columnNumber >= 0; rowNumber++, columnNumber-- )
			tokens[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		return tokens;
	}

	public Token[] getLeftDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced; rowNumber >= 0 && columnNumber >= 0; rowNumber--, columnNumber-- )
			tokens[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber < getNumberOfRows() && columnNumber < getNumberOfColumns(); rowNumber++, columnNumber++ )
			tokens[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		return tokens;
	}

	public int put(Token token, int column)
	{
		checkIfColumnExists(column);
		return columns.get(column).place(token);
	}

	private void checkIfColumnExists(int columnNumber)
	{
		if( columnNumber < 0 || columnNumber >= columns.size() ) throw new NonexistentColumnException("Column does not exist");
	}

	public Token[] getColumnOfTokensAlong(int columnNumber)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int rowNumber = 0; rowNumber < getNumberOfRows(); rowNumber++ )
		{
			tokens[rowNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		}
		return tokens;
	}

	private Token getColorOfTokenPlacedIn(int rowWhereTokenIsPlaced, int columnNumber)
	{
		return columns.get(columnNumber).getColorOfTokenPlacedIn(rowWhereTokenIsPlaced);
	}

	public Token[] getRowOfTokensAlong(int rowWhereTokenIsPlaced)
	{
		Token[] tokens = new Token[getNumberOfColumns()];
		for( int columnNumber = 0; columnNumber < getNumberOfColumns(); columnNumber++ )
		{
			tokens[columnNumber] = getColorOfTokenPlacedIn(rowWhereTokenIsPlaced, columnNumber);
		}
		return tokens;
	}

	public int getNumberOfTokensIn(int columnNumber)
	{
		checkIfColumnExists(columnNumber);
		return columns.get(columnNumber).getCurrentNumberOfTokens();
	}

	public int getNumberOfRows()
	{
		return columns.get(0).getNumberOfRows();
	}

	public int getNumberOfColumns()
	{
		return columns.size();
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
