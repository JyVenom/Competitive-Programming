package players;

public class GrimTrigger implements Player
{
	private boolean betrayed;
	
	public GrimTrigger()
	{
		betrayed = false;
	}

	public String name()
	{
		return "GrimTrigger";
	}

	public boolean firstMove()
	{
		betrayed = false;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		if(!opponentLastMove) betrayed = true;
		return !betrayed;
	}
}
