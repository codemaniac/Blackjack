package in.codemaniac.blackjack.concept;

public final class PlayerMove {

    private final Move move;
    private final float bet;

    public PlayerMove(final Move move, final float bet) {
        this.move = move;
        this.bet = bet;
    }

    public Move getMove() {
        return move;
    }

    public float getBet() {
        return bet;
    }

}
