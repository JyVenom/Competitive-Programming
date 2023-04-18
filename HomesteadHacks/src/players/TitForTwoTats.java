package players;

public class TitForTwoTats implements Player
{
	private boolean forgivenDefect;
	
	public TitForTwoTats()
	{
		forgivenDefect = false;
	}
	
	public String name()
	{
		return "TitForTwoTats";
	}

	public boolean firstMove()
	{
		forgivenDefect = false;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
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

}
