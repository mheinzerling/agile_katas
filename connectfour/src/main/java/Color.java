public enum Color
{
	BLUE, YELLOW, NULL_COLOR;

	@Override
	public String toString()
	{
		switch( this )
		{
			case BLUE:
				return "BLUE";
			case YELLOW:
				return "YELLOW";
			default:
				return "NIL";
		}
	}
}
