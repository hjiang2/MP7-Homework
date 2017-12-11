import java.io.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Result {
    // Initializes dictionary ArrayList.
    public static ArrayList<String> dictionary = new ArrayList<String>();
    private String input;
    private String totalResult = "";

    /**
    * Sees if the word in the dictionary can be made using the letters
    * put in by the user
    * @param testWord word in the dictionary to test
    * @param userInput letters the user has
    * @return whether the user can create any word with those letters
    */
    public boolean possible(String testWord, String userInput) {
        for (char letter: testWord.toCharArray()) {
            int isPresent = userInput.indexOf(letter);
            if (isPresent == -1) {
                return false;
            }
            userInput = userInput.substring(0, isPresent) + userInput.substring(isPresent + 1);
        }
        return true;
    }

    public int findMax(HashMap<String, Integer> values) {
        int maxVal = 0;
        for (String word: values.keySet()) {
            if (values.get(word) > maxVal) {
                maxVal = values.get(word);
            }
        }
        return maxVal;
    }
    
    public String getKeyFromValue(HashMap<String, Integer> map, int value) {
        for (String word: map.keySet()) {
            if (map.get(word) == value) {
                return word;
            }
        }
        return "";
    }
    
    public void setInput(String a) {
    	this.input = a;
    }
    
    public String getInput() {
    	return input;
    }
    
    public String getMaxPoints(String inputLetters) throws FileNotFoundException {
    	/**
         * Scans text file of words and adds all the words to an ArrayList dictionary
         */
         File file = new File("src/words.txt");
         Scanner scan = new Scanner(file);
         
         while (scan.hasNext()) {
             dictionary.add(scan.next());
         }
         scan.close();
         
         
         /**
         * For each word in the dictionary, checks if it can be made with
         * our letters, and if it can, adds it to an array of possible words
         */
         ArrayList<String> possibleWords = new ArrayList<String>();
         
         for (int i = 0; i < dictionary.size(); i++) {
             String tester = dictionary.get(i).toLowerCase();
             if (possible(tester, inputLetters)) {
                 possibleWords.add(tester);
             }
         }

         /**
         * Creates a Hash Map of scrabble letter values
         */
         HashMap<Character, Integer> letterValues = new HashMap<Character,Integer>();
         letterValues.put('a', 1);
         letterValues.put('b', 3);
         letterValues.put('c', 3);
         letterValues.put('d', 2);
         letterValues.put('e', 1);
         letterValues.put('f', 4);
         letterValues.put('g', 2);
         letterValues.put('h', 4);
         letterValues.put('i', 1);
         letterValues.put('j', 8);
         letterValues.put('k', 5);
         letterValues.put('l', 1);
         letterValues.put('m', 3);
         letterValues.put('n', 1);
         letterValues.put('o', 1);
         letterValues.put('p', 3);
         letterValues.put('q', 10);
         letterValues.put('r', 1);
         letterValues.put('s', 1);
         letterValues.put('t', 1);
         letterValues.put('u', 1);
         letterValues.put('v', 4);
         letterValues.put('w', 4);
         letterValues.put('x', 8);
         letterValues.put('y', 4);
         letterValues.put('z', 10);
     
         /**
         * Creates a new hash map of words and their point values
         */
         HashMap<String, Integer> wordValues = new HashMap<String,Integer>();
         
         for (String tester: possibleWords) {
             int points = 0;
             for (char letter: tester.toCharArray()) {
                 points += letterValues.get(letter);
             }
             wordValues.put(tester, points);
         }
         
         /**
         * Outputs possible words by highest point value
         */
               
         for (int i = 0; i < wordValues.size(); i++) {
             int currentMax = findMax(wordValues);
             String currentMaxWord = getKeyFromValue(wordValues, currentMax);
             String result = "Word: " + currentMaxWord + "\t\tPoint Value: " + currentMax;
             totalResult += result;
             totalResult += "\n";
             wordValues.remove(currentMaxWord);             
         } 
         
         return this.totalResult;
    }
    public static void main(String[] args){
    	Result a = new Result();
    	try {
			System.out.println(a.getMaxPoints("helloworld"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
       
}


