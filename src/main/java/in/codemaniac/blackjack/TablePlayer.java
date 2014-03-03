package in.codemaniac.blackjack;

public final class TablePlayer extends Player {

    private float balance;

    public TablePlayer(final String name, final float initialBalance) {
        super(name);
        balance = initialBalance;
    }

    public float placeInitialBet(final float initialBetAmount) {
        final boolean hasSufficientBalance = balance >= initialBetAmount;
        balance = hasSufficientBalance ? balance - initialBetAmount : balance;
        return hasSufficientBalance ? initialBetAmount : 0;
    }

    @Override
    public PlayerMove playHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
