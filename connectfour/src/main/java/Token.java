class Token
{
	private final Color color;

	public Token(Color color)
	{
		this.color = color;
	}

	@Override
	public String toString()
	{
		return color.toString();
	}

	public Color getColor()
	{
		return color;
	}
}
