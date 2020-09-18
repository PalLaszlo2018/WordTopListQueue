/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistqueue;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import static wordtoplistqueue.WordCollector.LOG;

/**
 * This class creates the various threads that will collect the words in a common collection
 *
 * @author laszlop
 */
public class WordCollection {

    List<URL> urlList;
    Set<String> skipWords;
    private final WordStore storer;
    private static final int MAX_THREADS = 4;
    private final BlockingQueue<URL> urlQueue;
    private final CountDownLatch latch;

    public WordCollection(List<URL> urlList, WordStore storer, Set<String> skipWords) {
        this.urlList = urlList;
        this.skipWords = skipWords;
        this.storer = storer;
        urlQueue = new ArrayBlockingQueue(urlList.size(), false, urlList);
        latch = new CountDownLatch(urlList.size());
    }
    
    /**
     * Creates Threads and waits for the completion of all Threads
     */    
    public void runThreads() throws Exception {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < MAX_THREADS; i++) {
            threadList.add(new WordCollector(urlQueue, latch, skipWords, storer));
            threadList.get(i).start();
            LOG.info("THREAD " + (i + 1) + " STARTED.");
        }
        latch.await();
    }
    
    /**
     * Prints the full list of the found words.
     */
    public void print() {
        storer.print();
    }

    /**
     * Logs the n-sized top-list of the found words.
     *
     * @param n
     */
    public void print(int n) {
        storer.print(n);
    }

}
