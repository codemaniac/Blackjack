package in.codemaniac.blackjack.actor;

import in.codemaniac.blackjack.concept.PlayerMove;
import in.codemaniac.blackjack.asset.Card;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Player {

    protected static final Logger LOG = LoggerFactory.getLogger(Player.class);
    protected final String name;
    protected Card handCard1;
    protected Card handCard2;
    protected Card[] remainingHandCards = new Card[3];

    public Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addHandCard(final Card handCard) {
        if (handCard1 == null) {
            this.handCard1 = handCard;
            logHand(handCard);
        } else if (handCard2 == null) {
            this.handCard2 = handCard;
            logHand(handCard);
        } else {
            for (int i = 0; i < remainingHandCards.length; i++) {
                if (remainingHandCards[i] == null) {
                    remainingHandCards[i] = handCard;
                    logHand(handCard);
                    break;
                }
            }
        }
    }

    private void logHand(final Card handCard) {
        LOG.info(String.format("%s was handed %s", this.name, handCard));
    }

    public List<Card> returnHandCards() {
        final List<Card> handCards = new ArrayList<>();
        if (handCard1 != null) {
            handCards.add(handCard1);
        }
        if (handCard2 != null) {
            handCards.add(handCard2);
        }
        for (final Card c : remainingHandCards) {
            if (c != null) {
                handCards.add(c);
            }
        }

        handCard1 = handCard2 = null;
        remainingHandCards = new Card[3];

        return handCards;
    }

    public abstract PlayerMove playHand();

}
