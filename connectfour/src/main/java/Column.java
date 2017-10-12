
class Column
{
	private final Token[] tokenRows;
	private final int     numberOfRows;
	private       int     nextTokenHeight;

	public Column(int numberOfRows)
	{
		this.nextTokenHeight = 0;
		this.numberOfRows = numberOfRows;
		this.tokenRows = new Token[numberOfRows];

	}

	public int getNumberOfRows()
	{
		return numberOfRows;
	}

	public int place(Token token) throws FullColumnException
	{
		if( nextTokenHeight >= numberOfRows ) throw new FullColumnException("Column is full!");
		tokenRows[nextTokenHeight] = token;
		nextTokenHeight++;
		return nextTokenHeight - 1;
	}

	public int getCurrentNumberOfTokens()
	{
		return nextTokenHeight;
	}

	public Token getToken(int row)
	{
		if( tokenRows[row] == null ) return Token.NONE;
		return tokenRows[row];
	}
}
