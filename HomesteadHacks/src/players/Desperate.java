package players;

public class Desperate implements Player
{
	private boolean lastMove;
	
	public Desperate()
	{
		lastMove = false;
	}
	
	public String name()
	{
		return "Desperate";
	}

	public boolean firstMove()
	{
		lastMove = false;
		return false;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		lastMove = !(lastMove || opponentLastMove);
		return lastMove;
	}

}
