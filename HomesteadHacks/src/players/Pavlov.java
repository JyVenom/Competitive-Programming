package players;

public class Pavlov implements Player
{
	private boolean lastMove;
	public Pavlov()
	{
		lastMove = true;
	}
	
	public String name()
	{
		return "Pavlov";
	}

	public boolean firstMove()
	{
		lastMove = true;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		int lastScore = Fight.score1(lastMove, opponentLastMove);
		if(lastScore >= 3) return lastMove;
		lastMove = !lastMove;
		return lastMove;
	}
}
