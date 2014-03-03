package in.codemaniac.blackjack;

import in.codemaniac.blackjack.actor.TablePlayer;
import in.codemaniac.blackjack.asset.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        LOG.info("LETS PLAY BLACKJACK IN GOD MODE !");

        final Table table = new Table("!Xobile", 2, 1, 25);

        table.addTablePlayer(new TablePlayer("Russell", 1000));
        table.addTablePlayer(new TablePlayer("Ashish", 1000));

        table.start();        
    }
}
