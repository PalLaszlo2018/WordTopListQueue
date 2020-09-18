/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistqueue;



/**
 *
 * @author laszlop
 */
public interface WordStore {
    
    public void store(String word);
    
    public void addSkipWord(String word);
    
    public void print();
    
    public void print(int n);   

    
}
