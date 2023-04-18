package players;

public class Alternate implements Player
{
	private boolean lastMove;
	
	public Alternate()
	{
		lastMove = true;
	}
	
	public String name()
	{
		return "Alternate";
	}

	public boolean firstMove()
	{
		lastMove = true;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		lastMove = !lastMove;
		return lastMove;
	}

}
