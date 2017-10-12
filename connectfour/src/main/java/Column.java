import java.util.Arrays;
import java.util.List;

class Column
{
	private final List<Token> tokenRows;
	private       int         currentIndex;

	public Column(int numberOfRows)
	{
		this.tokenRows = Arrays.asList(new Token[numberOfRows]);
		this.currentIndex = 0;
	}

	public int getNumberOfRows()
	{
		return tokenRows.size();
	}

	public int place(Token token)
	{
		if( columnIsFull() ) throw new FullColumnException("Column is full!");
		tokenRows.set(currentIndex, token);
		currentIndex++;
		return currentIndex - 1;
	}

	private boolean columnIsFull()
	{
		return currentIndex == tokenRows.size();
	}

	public int getCurrentNumberOfTokens()
	{
		return currentIndex;
	}

	public Color getColorOfTokenPlacedIn(int rowNumber)
	{
		if( tokenRows.get(rowNumber) == null ) return Color.NULL_COLOR;
		return tokenRows.get(rowNumber).getColor();
	}
}
