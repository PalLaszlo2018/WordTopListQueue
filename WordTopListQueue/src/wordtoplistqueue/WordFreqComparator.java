/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistqueue;

import java.util.Comparator;
import java.util.Map;

/**
 * This comparator can help sorting the word-frequency entries. It checks the frequency first (higher values prioritized), in case
 * of equal frequencies alphabetical order will be used.
 * @author laszlop
 */
public class WordFreqComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> wordFreq1, Map.Entry<String, Integer> wordFreq2) {
        Integer freq1 = wordFreq1.getValue();
        Integer freq2 = wordFreq2.getValue();
        if (freq1 != freq2) {
            return - freq1.compareTo(freq2);
        }
        return wordFreq1.getKey().compareTo(wordFreq2.getKey());
    }
    
}