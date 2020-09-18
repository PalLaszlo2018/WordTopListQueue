/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistqueue;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates the various threads that will collect the words in a common collection
 *
 * @author laszlop
 */
public class WordCollection {

    List<URL> urlList;
    Set<String> skipWords;
    private final WordStore storer;

    public WordCollection(List<URL> urlList, WordStore storer, Set<String> skipWords) {
        this.urlList = urlList;
        this.skipWords = skipWords;
        this.storer = storer;
    }
    
    /**
     * Creates Threads and waits for the completion of all Threads
     */

    public void createThreads() {
        ExecutorService pool = Executors.newFixedThreadPool(urlList.size());
        CompletionService completion = new ExecutorCompletionService(pool);
        for (int i = 0; i < urlList.size(); i++) {
            completion.submit(new WordCollector(urlList.get(i), storer, skipWords));
        }
        for (int i = 0; i < urlList.size(); i++) {
            try {
                completion.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(WordCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        pool.shutdown();
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
