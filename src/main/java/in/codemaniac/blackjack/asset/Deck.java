package in.codemaniac.blackjack.asset;

import in.codemaniac.blackjack.concept.Suit;
import in.codemaniac.blackjack.concept.Rank;
import java.util.Arrays;
import java.util.Collections;

public final class Deck {

    private final Card[] cards = new Card[52];

    {
        int idx = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards[idx++] = new Card(suit, rank);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(Arrays.asList(cards));
    }

    public Card[] getCards() {
        return cards;
    }

}
