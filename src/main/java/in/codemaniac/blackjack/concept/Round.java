package in.codemaniac.blackjack.concept;

import in.codemaniac.blackjack.actor.Dealer;
import in.codemaniac.blackjack.actor.TablePlayer;
import in.codemaniac.blackjack.asset.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Round {

    private static final Logger LOG = LoggerFactory.getLogger(Round.class);
    private final Dealer dealer;
    private final Map<TablePlayer, Float> bets;
    private final TablePlayer[] players;
    private final List<TablePlayer> activePlayers;
    private final Queue<Card> shoe;
    private final float initialBetAmount;

    public Round(final Dealer dealer,
            final TablePlayer[] players,
            final Queue<Card> shoe,
            final float initialBetAmount) {
        this.bets = new HashMap<>();
        this.dealer = dealer;
        this.players = players;
        this.activePlayers = new ArrayList<>(Arrays.asList(players));
        this.shoe = shoe;
        this.initialBetAmount = initialBetAmount;
    }

    public void play() {
        // collect initial bets
        collectInitialBets();
        // hand once
        dealHand();
        // hand twice
        dealHand();
        // TODO: game play
        // collect all delt cards and place them at the end of the shoe
        reclaimCards();
    }

    private void collectInitialBets() {
        for (final TablePlayer p : activePlayers) {
            final float initialBet = p.placeInitialBet(initialBetAmount);
            if (initialBet > 0) {
                bets.put(p, initialBet);
                LOG.info(String.format("%s placed initial bet of %f", p.getName(), initialBetAmount));
            } else {
                LOG.info(String.format("%s went bust", p.getName()));
                activePlayers.remove(p);
            }
        }
    }

    private void dealHand() {
        for (final TablePlayer p : activePlayers) {
            p.addHandCard(shoe.remove());
        }
        dealer.addHandCard(shoe.remove());
    }

    private void reclaimCards() {
        for (final TablePlayer p : players) {
            shoe.addAll(p.returnCards());
        }
        shoe.addAll(dealer.returnCards());
    }
}
