package players;

public class Fight
{
	public static final int N = 200;
	
	public static void main(String[] args)
	{
		fight(new User(), new Pavlov(), true);
	}

	public static double[] fight(Player p1, Player p2, boolean verbose)
	{
		if(verbose) System.out.println(p1.name()+"\t"+p2.name());
		boolean m1 = p1.firstMove(), m2 = p2.firstMove();
		if(verbose) System.out.println((m1?'C':'D')+"\t"+(m2?'C':'D'));
		int s1 = score1(m1, m2), s2 = score2(m1, m2);
		
		for(int i = 1; i < N; i++)
		{
			boolean temp = m1;
			m1 = p1.makeMove(m2);
			m2 = p2.makeMove(temp);
			if(verbose) System.out.println((m1?'C':'D')+"\t"+(m2?'C':'D'));
			s1 += score1(m1, m2);
			s2 += score2(m1, m2);
		}
		
		double a1 = (double)s1/N, a2 = (double)s2/N;
		if(verbose)
		{
			System.out.println(p1.name()+" Average: " + a1);
			System.out.println(p2.name()+" Average: " + a2);
			System.out.println();
		}
		return new double[]{a1,a2};
	}
	
	public static int score1(boolean m1, boolean m2)
	{
		if(m1)
		{
			if(m2) return 3;
			else return 0;
		}
		else
		{
			if(m2) return 5;
			else return 1;
		}
	}
	
	public static int score2(boolean m1, boolean m2)
	{
		return score1(m2, m1);
	}
}
