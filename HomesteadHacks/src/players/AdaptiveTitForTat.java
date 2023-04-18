package players;

public class AdaptiveTitForTat implements Player
{
	private double rate, world;
	
	public AdaptiveTitForTat()
	{
		this(0.05);
	}
	
	public AdaptiveTitForTat(double rate)
	{
		this.rate = rate;
		world = 0.5;
	}

	public String name()
	{
		return "AdaptiveTitForTat";
	}

	public boolean firstMove()
	{
		world = 0.5;
		return true;
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		if(opponentLastMove)
		{
			world = world+rate*(1-world);
		}
		else
		{
			world = world-rate*world;
		}
		return world >= 0.5;
	}

}
