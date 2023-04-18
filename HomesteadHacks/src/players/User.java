package players;

import javax.swing.*;

public class User implements Player
{
	public User()
	{
		
	}
	
	public String name()
	{
		return "User";
	}

	public boolean firstMove()
	{
		String move = null;
		while(move == null || move.length() == 0)
		{
			move = JOptionPane.showInputDialog("C to cooperate, D to defect");
		}
		return move.charAt(0) == 'c' || move.charAt(0) == 'C';
	}

	public boolean makeMove(boolean opponentLastMove)
	{
		String move = null;
		while(move == null || move.length() == 0)
		{
			move = JOptionPane.showInputDialog("C to cooperate, D to defect");
		}
		return move.charAt(0) == 'c' || move.charAt(0) == 'C';
	}
}
