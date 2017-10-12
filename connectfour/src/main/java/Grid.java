import java.util.Arrays;
import java.util.List;

class Grid
{
	private final List<Column> columns;

	public Grid(int numberOfRows, int numberOfColumns)
	{
		columns = Arrays.asList(new Column[numberOfColumns]);
		for( int i = 0; i < columns.size(); i++ )
		{
			columns.set(i, new Column(numberOfRows));
		}
	}

	public Color[] getRightDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		Color[] colors = new Color[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber >= 0 && columnNumber < getNumberOfColumns(); rowNumber--, columnNumber++ )
			colors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber < getNumberOfRows() && columnNumber >= 0; rowNumber++, columnNumber-- )
			colors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		return colors;
	}

	public Color[] getLeftDiagonalOfTokensAlong(int columnWhereTokenIsPlaced, int rowWhereTokenIsPlaced)
	{
		Color[] colors = new Color[getNumberOfColumns()];
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced; rowNumber >= 0 && columnNumber >= 0; rowNumber--, columnNumber-- )
			colors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		for( int rowNumber = rowWhereTokenIsPlaced, columnNumber = columnWhereTokenIsPlaced;
			 rowNumber < getNumberOfRows() && columnNumber < getNumberOfColumns(); rowNumber++, columnNumber++ )
			colors[columnNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		return colors;
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

	public Color[] getColumnOfTokensAlong(int columnNumber)
	{
		Color[] colors = new Color[getNumberOfColumns()];
		for( int rowNumber = 0; rowNumber < getNumberOfRows(); rowNumber++ )
		{
			colors[rowNumber] = getColorOfTokenPlacedIn(rowNumber, columnNumber);
		}
		return colors;
	}

	private Color getColorOfTokenPlacedIn(int rowWhereTokenIsPlaced, int columnNumber)
	{
		return columns.get(columnNumber).getColorOfTokenPlacedIn(rowWhereTokenIsPlaced);
	}

	public Color[] getRowOfTokensAlong(int rowWhereTokenIsPlaced)
	{
		Color[] colors = new Color[getNumberOfColumns()];
		for( int columnNumber = 0; columnNumber < getNumberOfColumns(); columnNumber++ )
		{
			colors[columnNumber] = getColorOfTokenPlacedIn(rowWhereTokenIsPlaced, columnNumber);
		}
		return colors;
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
