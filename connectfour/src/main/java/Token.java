public enum Token
{
	BLUE, YELLOW, NONE;

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
