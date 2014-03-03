package in.codemaniac.blackjack.actor;

import in.codemaniac.blackjack.asset.Card;
import in.codemaniac.blackjack.concept.PlayerMove;

public final class Player extends AbstractPlayer {

    private float balance;

    public Player(final String name, final float initialBalance) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected Card[] getHandCards() {
        return handCards;
    }

    protected int getHandCount() {
        return handCount;
    }
}
