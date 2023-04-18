package players;

public class TitForTat implements Player
{
	public TitForTat()
	{
		
	}
	
	public String name()
	{
		return "TitForTat";
	}

	public boolean firstMove()
	{
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		return opponentLastMove;
	}

}
