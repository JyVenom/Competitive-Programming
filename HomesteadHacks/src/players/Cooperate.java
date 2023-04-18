package players;

public class Cooperate implements Player
{
	public Cooperate()
	{
		
	}
	
	public String name()
	{
		return "Cooperate";
	}
	
	public boolean firstMove()
	{
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		return true;
	}

}
