package in.codemaniac.blackjack.asset;

import in.codemaniac.blackjack.actor.Dealer;
import in.codemaniac.blackjack.concept.Round;
import in.codemaniac.blackjack.actor.TablePlayer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Table {

    private static final Logger LOG = LoggerFactory.getLogger(Table.class);
    private final Dealer dealer;
    private final TablePlayer[] players;
    private final Queue<Card> shoe;
    private final float initialBetAmount;
    private int currentRoundNumber;

    public Table(final String dealerName,
            final int tableSize,
            final int shoeSize,
            final float initialBetAmount) {
        dealer = new Dealer(dealerName);
        players = new TablePlayer[tableSize];
        this.shoe = new LinkedList<>();
        for (int i = 0; i < shoeSize; i++) {
            final Deck deck = new Deck();
            deck.shuffle();
            this.shoe.addAll(Arrays.asList(deck.getCards()));
        }
        this.initialBetAmount = initialBetAmount;
    }

    public void addTablePlayer(final TablePlayer tablePlayer) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = tablePlayer;
                LOG.info(String.format("%s joined the table", tablePlayer.getName()));
                return;
            }
        }
        LOG.warn("Table full !");
    }

    public void start() {
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                LOG.info(String.format("Round %d", ++currentRoundNumber));
                new Round(dealer, players, shoe, initialBetAmount).play();
            }
        }, 0, 10000);
    }
}
