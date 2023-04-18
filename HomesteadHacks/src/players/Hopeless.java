package players;

public class Hopeless implements Player
{
	private boolean lastMove;
	
	public Hopeless()
	{
		lastMove = false;
	}
	
	public String name()
	{
		return "Hopeless";
	}

	public boolean firstMove()
	{
		lastMove = true;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		lastMove = !(lastMove && opponentLastMove);
		return lastMove;
	}

}
