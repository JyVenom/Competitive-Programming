package players;

/**
 * An interface representing a strategy for iterated prisoner's dilemma.
 * True for cooperation, false for defection.
 * Mutual cooperation yields 3 points for each party.
 * Mutual defection yields 1 point for each party.
 * If one party defects and the other cooperates, the cooperating party gets 0 points
 * and the defecting party receives 5 points.
 * @author rfeng670
 */
public interface Player
{
	/**
	 * @return String that is the name of the player
	 */
	String name();
	/**
	 * First move, and signifies a new round: true for cooperate, false for defect.
	 * Make sure to reset all instance variables for a new round.
	 * @return a boolean representing the player's next move
	 */
	boolean firstMove();
	/**
	 * Makes a move: true for cooperate, false for defect.
	 * @return a boolean representing the player's next move
	 */
	boolean makeMove(boolean opponentLastMove);
}
