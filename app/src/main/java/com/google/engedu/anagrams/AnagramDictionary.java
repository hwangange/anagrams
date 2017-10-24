/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 3;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private HashSet<String> wordSet = new HashSet<String>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);

            String scrambled = sortLetters(word);
            if(lettersToWord.containsKey(scrambled)) {
                lettersToWord.get(scrambled).add(word);
            }
            else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(word);
                lettersToWord.put(scrambled, temp);
            }

        }
    }

    public boolean isGoodWord(String word, String base) {
        return wordSet.contains(word) && !word.contains(base);
        //return !word.contains(base);
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String scrambledTarget = sortLetters(targetWord);


        Iterator<String> iterator = wordSet.iterator();

        while(iterator.hasNext()) {
            String temp = iterator.next();
            String scrambledTemp = sortLetters(temp);
            if (scrambledTarget.length() == scrambledTemp.length() && scrambledTarget.equals(scrambledTemp))
                result.add(temp);
        }


      /*  for(int x = 0; x < wordSet.size(); x++) {
            String scrambledTemp = sortLetters(wordSet.);
            if(scrambledTarget.length() == scrambledTemp.length() && scrambledTarget.equals(scrambledTemp))
                result.add(wordList.get(x));
        } */
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for(char ascii = 97; ascii <=122; ascii++) //lowercase a to lowercase z
        {
            String alpha_scramble = sortLetters(word + String.valueOf(ascii));
            ArrayList<String> words = lettersToWord.get(alpha_scramble);
            if(words !=null)
                result.addAll(words);

        }
        return result;
    }

    public String pickGoodStarterWord() {
        int length = wordList.size();
        int index = (int)(Math.random() * length);
        for(int i = index; i < wordList.size(); i++) {
            String temp = sortLetters(wordList.get(i));
            if(lettersToWord.get(temp).size() >= MIN_NUM_ANAGRAMS)
                return lettersToWord.get(temp).get(0);
        }
        for(int i = 0; i < index; i++) {
            String temp = sortLetters(wordList.get(i));
            if(lettersToWord.get(temp).size() >= MIN_NUM_ANAGRAMS)
                return lettersToWord.get(temp).get(0);
        }
        return "stop";
    }

    public String sortLetters(String word) {
        char[] array = new char[word.length()];
        for(int x = 0; x < word.length(); x++)
            array[x] = word.charAt(x);

        for(int x = 0; x < array.length; x++) {
            int first = x;
            //first is the index number of the 'first' letter

            for(int y = x+1; y < array.length; y++) {

                int first_ascii = (int)array[first];
                int y_ascii = (int)array[y];

                if(first_ascii > y_ascii) {
                   /* char temp = first;
                    first = array[y];
                    array[y] = temp; */
                   first = y;

                   // System.out.println(first_ascii + " is greater than " + y_ascii);
                }
              //  else
                 //   System.out.println(first_ascii + " is less than " + y_ascii);

            }
            char temp = array[x];
            array[x] = array[first];
            array[first] = temp;

        }

        String finalWord = "";
        for(int x = 0; x < array.length; x++) {
            finalWord+=array[x];
        }

        return finalWord;
    }
}
