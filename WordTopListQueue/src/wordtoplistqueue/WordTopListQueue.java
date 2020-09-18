/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistqueue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author laszlop
 */
public class WordTopListQueue {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException, IOException
     */
    public static void main(String[] args) throws MalformedURLException, IOException, Exception {
        System.out.println("WordTopListRecursive application started.");
        Set<String> skipWords = new HashSet<>(Arrays.asList("an", "and", "by", "for", "if", "is", "in", "it", "of", "on", "that",
                "the", "to", "with", "www", "url", "name", "label", "html", "window", "title", "basepath"));
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://justinjackson.ca/words.html"));
        urlList.add(new URL("http://abouthungary.hu/"));
        urlList.add(new URL("https://www.javatpoint.com/java-tutorial"));
        urlList.add(new URL("https://www.bbc.com/"));
        urlList.add(new URL("https://www.cnn.com/"));
        urlList.add(new URL("https://www.rt.com/"));
        urlList.add(new URL("https://www.foxnews.com/"));
        urlList.add(new URL("http://www.chinatoday.com.cn/english/"));
        urlList.add(new URL("https://www.sony.com/"));
        urlList.add(new URL("https://www.abc.net.au/news/australia/"));
        System.out.println("Checked URL-s: " + urlList);
        System.out.println("What do you want to check? \n 1 - most frequent words \n 2 - longest words \n "
                + "3 - words with highest vowel frequency");
        Scanner scanner = new Scanner(System.in);
        char character = scanner.next().charAt(0);
        switch (character) {
            case '1':
                checkFrequency(urlList, skipWords);
                break;
            case '2':
                checkLongest(urlList, skipWords);
                break;
            case '3':
                checkVowelFreq(urlList, skipWords);
                break;
        }
    }

    /**
     * Starts the hunting for the most frequent words.
     *
     * @param urlList
     * @param skipWords
     * @throws IOException
     */
    private static void checkFrequency(List<URL> urlList, Set<String> skipWords) throws IOException, Exception {
        WordStore wordStoreFreq = new SorterByFrequency();
        WordCollection wordCollectionFreq = new WordCollection(urlList, wordStoreFreq, skipWords);
        wordCollectionFreq.runThreads();
        wordCollectionFreq.print(10);
    }

    /**
     * Starts the hunting for the longest words.
     *
     * @param urlList
     * @param skipWords
     * @throws IOException
     */
    private static void checkLongest(List<URL> urlList, Set<String> skipWords) throws IOException, Exception {
        WordStore wordStoreLen = new SorterByLength();
        WordCollection wordCollectionLen = new WordCollection(urlList, wordStoreLen, skipWords);
        wordCollectionLen.runThreads();
        wordCollectionLen.print(10);
    }

    /**
     * Starts the hunting for the words with highest frequency of vowels.
     *
     * @param urlList
     * @param skipWords
     * @throws IOException
     */
    private static void checkVowelFreq(List<URL> urlList, Set<String> skipWords) throws IOException, Exception {
        WordStore wordStoreVowel = new SorterByVowelFreq();
        WordCollection wordCollectionVowel = new WordCollection(urlList, wordStoreVowel, skipWords);
        wordCollectionVowel.runThreads();
        wordCollectionVowel.print(10);
    }

}
