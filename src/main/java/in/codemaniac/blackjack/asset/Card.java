package in.codemaniac.blackjack.asset;

import in.codemaniac.blackjack.concept.Suit;
import in.codemaniac.blackjack.concept.Rank;

public final class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.format("[%s OF %s]", rank, suit);
    }
}
