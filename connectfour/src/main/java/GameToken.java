class GameToken
{
	private final GameTokenColor tokenColor;

	public GameToken(GameTokenColor gameTokenColor)
	{
		this.tokenColor = gameTokenColor;
	}

	@Override
	public String toString()
	{
		return tokenColor.toString();
	}

	public GameTokenColor getTokenColor()
	{
		return tokenColor;
	}
}
