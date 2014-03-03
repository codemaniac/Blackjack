package in.codemaniac.blackjack;

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
        // place initial bet
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
        // hand once
        for (final TablePlayer p : activePlayers) {
            p.addHandCard(shoe.remove());
        }
        dealer.addHandCard(shoe.remove());
        // hand twice
        for (final TablePlayer p : activePlayers) {
            p.addHandCard(shoe.remove());
        }
        dealer.addHandCard(shoe.remove());

        // TODO: game play
        
        // collect back cards
        for (final TablePlayer p : players) {
            shoe.addAll(p.returnHandCards());
        }
        shoe.addAll(dealer.returnHandCards());
    }

}
