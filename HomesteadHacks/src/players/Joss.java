package players;

public class Joss implements Player
{
	private double cooperationChance;
	public Joss()
	{
		cooperationChance = 0.9;
	}
	
	public Joss(double cooperationChance)
	{
		this.cooperationChance = cooperationChance;
	}
	
	public String name()
	{
		return "Joss"+cooperationChance;
	}

	public boolean firstMove()
	{
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		if(opponentLastMove && Math.random()<cooperationChance) return true;
		return false;
	}
}
