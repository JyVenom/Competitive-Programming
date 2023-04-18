package players;

public class Jerry implements Player{

    private boolean betrayed;

    @Override
    public String name() {
        return "Jerry";
    }

    @Override
    public boolean firstMove() {
        betrayed = false;
        return true;
    }

    @Override
    public boolean makeMove(boolean opponentLastMove) {
        if(!opponentLastMove) betrayed = true;
        return !betrayed;
    }
}
