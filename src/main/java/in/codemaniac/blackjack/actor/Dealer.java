package in.codemaniac.blackjack.actor;

import in.codemaniac.blackjack.asset.Card;
import in.codemaniac.blackjack.concept.PlayerMove;
import in.codemaniac.blackjack.concept.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Dealer extends AbstractPlayer {

    private final Map<Player, Integer> tablePlayerHandCounts;

    public Dealer(final String name) {
        super(name);
        this.tablePlayerHandCounts = new HashMap<>();
    }

    @Override
    public PlayerMove playHand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Card> returnCards() {
        tablePlayerHandCounts.clear();
        return super.returnCards();
    }

    public Result inspectPlayerHand(final Player tablePlayer) {
        final int playerHandCount = tablePlayer.getHandCount();
        tablePlayerHandCounts.put(tablePlayer, playerHandCount);
        // TODO
        return Result.NONE;
    }
}
