package Main;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import Crawler.CrawlerController;
import DB.DbManager;
import Indexer.Indexer;
import Indexer.IndexerThread;
import Ranker.PageRank;

import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException{

        /* Remove Mongo Logging */
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
        
        AtomicInteger synchronization = new AtomicInteger();

        CrawlerController _crawler = new CrawlerController(5, 1200, synchronization);
        Thread crawler = new Thread(_crawler);
        crawler.start();
        Indexer _indexer = new Indexer(synchronization);
        Thread indexer = new Thread(_indexer);
        indexer.start();

        //PageRank Ind = new PageRank(3, 0.7);
        //PageRank.main(new String[]{""});
    }
}