package players;

public class AdaptivePavlov implements Player
{
	private int numMoves, numDefects, status;
	private boolean lastMove, forgivenDefect;
	
	public AdaptivePavlov()
	{
		numMoves = 0;
		numDefects = 0;
		status = 0;
		lastMove = true;
		forgivenDefect = false;
	}
	
	public String name()
	{
		return "AdaptivePavlov";
	}

	public boolean firstMove()
	{
		numMoves = 1;
		numDefects = 0;
		status = 0;
		lastMove = true;
		forgivenDefect = false;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		if(numMoves == 6)
		{
			if(numDefects == 0) status = 0;
			else if(numDefects < 3) status = 1;
			else if(numDefects == 3) status = 2;
			else status = 3;
		}
		numMoves++;
		if(!opponentLastMove) numDefects++;
		
		if(status == 0)
		{
			return opponentLastMove;
		}
		else if(status == 1)
		{
			int lastScore = Fight.score1(lastMove, opponentLastMove);
			if(lastScore >= 3) return lastMove;
			lastMove = !lastMove;
			return lastMove;
		}
		else if(status == 2)
		{
			if(opponentLastMove) return true;
			else if(forgivenDefect)
			{
				forgivenDefect = false;
				return false;
			}
			else
			{
				forgivenDefect = true;
				return true;
			}
		}
		else return false;
	}

}
