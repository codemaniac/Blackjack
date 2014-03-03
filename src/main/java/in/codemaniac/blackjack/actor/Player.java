package in.codemaniac.blackjack.actor;

import in.codemaniac.blackjack.concept.PlayerMove;
import in.codemaniac.blackjack.asset.Card;
import in.codemaniac.blackjack.concept.Rank;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Player {

    protected static final Logger LOG = LoggerFactory.getLogger(Player.class);
    protected final String name;
    protected Card[] handCards = new Card[5];
    protected int handCount;

    public Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addHandCard(final Card handCard) {
        for (int i = 0; i < this.handCards.length; i++) {
            if (this.handCards[i] == null) {
                this.handCards[i] = handCard;
                processHandCard(handCard);
                break;
            }
        }
    }

    private void processHandCard(final Card handCard) {
        this.handCount += handCard.getRank().getValue();
        int nAces = 0;
        for (final Card c : this.handCards) {
            if (c != null && c.getRank().equals(Rank.ACE)) {
                nAces++;
            }
        }
        while (this.handCount > 21 && nAces > 0) {
            nAces--;
            this.handCount -= 10;
        }
        LOG.info(String.format("%s was handed %s; Hand count = %d", this.name, handCard, this.handCount));
    }

    public List<Card> returnCards() {
        final List<Card> reclaimableCards = new ArrayList<>();
        for (final Card c : this.handCards) {
            if (c != null) {
                reclaimableCards.add(c);
            }
        }

        this.handCards = new Card[5];
        this.handCount = 0;

        return reclaimableCards;
    }

    public abstract PlayerMove playHand();

}
