package in.codemaniac.blackjack.actor;

import in.codemaniac.blackjack.asset.Card;
import in.codemaniac.blackjack.concept.PlayerMove;

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

    protected Card[] getHandCards() {
        return handCards;
    }

    protected int getHandCount() {
        return handCount;
    }

    @Override
    public PlayerMove playHand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
