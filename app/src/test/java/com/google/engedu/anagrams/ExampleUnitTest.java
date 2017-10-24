package com.google.engedu.anagrams;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
       // System.out.println(AnagramDictionary.sortLetters("stupid"))
       // System.out.println(AnagramDictionary.isGoodWord("goodman", "good"));
        //System.out.println(AnagramDictionary.isGoodWord("doogy", "good"));
      /*  System.out.println("nonstop".contains("post"));
        System.out.println("poster".contains("post"));
        System.out.println("lamp post".contains("post"));*/
        ArrayList<String> practice = new ArrayList<>();
        practice.add("haha");
        practice.add("stupid");
        practice.add("laugh");
        ArrayList<String>practice2 = new ArrayList<>();
        practice2.add("haha2");
        practice2.add("stupid2");
        practice2.add("laugh2");
        practice2.addAll(practice);

        for(int x = 0; x < practice2.size(); x++){
            System.out.println(practice2.get(x));
        }




    }
}