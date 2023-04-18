package players;

import java.text.DecimalFormat;

public class Tournament {
	public static void main(String[] args) {
		Player[] players = new Player[]{
						new Joss(),
						new Pavlov(),
						new TitForTat(),
						new TitForTwoTats(),
						new GrimTrigger(),
						new AdaptiveTitForTat(),
						new AdaptivePavlov(),
						new Defect(),
						new Cooperate(),
						new Alternate(),
						new Desperate(),
						new Hopeless()
						//add you class here to the tournament for testing
				};
		double[] scores = new double[players.length];
		for(int i = 0; i < players.length; i++)
		{
			for(int j = i+1; j < players.length; j++)
			{
				double[] results = Fight.fight(players[i], players[j], true);
				scores[i]+=results[0];
				scores[j]+=results[1];
			}
		}

		DecimalFormat df = new DecimalFormat("0.000");
		for(int i = 0; i < players.length; i++)
		{
			System.out.println(players[i].name() + ": " + df.format(scores[i]/players.length));
		}
	}
}
