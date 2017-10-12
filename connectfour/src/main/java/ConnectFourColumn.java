import java.util.Arrays;
import java.util.List;

public class ConnectFourColumn
{
	private List<GameToken> gameTokenRows;
	private int             currentIndex;

	public ConnectFourColumn(int numberOfRows)
	{
		this.gameTokenRows = Arrays.asList(new GameToken[numberOfRows]);
		this.currentIndex = 0;
	}

	public int getNumberOfRows()
	{
		return gameTokenRows.size();
	}

	public int place(GameToken gameToken)
	{
		if( columnIsFull() ) throw new FullColumnException("Column is full!");
		gameTokenRows.set(currentIndex, gameToken);
		currentIndex++;
		return currentIndex - 1;
	}

	private boolean columnIsFull()
	{
		return currentIndex == gameTokenRows.size();
	}

	public int getCurrentNumberOfTokens()
	{
		return currentIndex;
	}

	public GameTokenColor getColorOfTokenPlacedIn(int rowNumber)
	{
		if( gameTokenRows.get(rowNumber) == null ) return GameTokenColor.NULL_COLOR;
		return gameTokenRows.get(rowNumber).getTokenColor();
	}
}
