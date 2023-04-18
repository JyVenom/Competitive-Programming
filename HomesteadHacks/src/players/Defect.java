package players;

public class Defect implements Player
{
	public Defect()
	{
		
	}
	
	public String name()
	{
		return "Defect";
	}

	public boolean firstMove()
	{
		return false;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		return false;
	}

}
