import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-04-07
 */
public class Doublets implements WordLadderGame {

    ////////////////////////////////////////////
    // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
    ////////////////////////////////////////////

    // A word ladder with no words. Used as the return value for the ladder methods
    // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;
   
   Node word;

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
                ////////////////////////////////////////////////
                // Add code here to store str in the lexicon. //
                ////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

    ///////////////////////////////////////////////////////////////////////////////
    // Fill in implementations of all the WordLadderGame interface methods here. //
    ///////////////////////////////////////////////////////////////////////////////
   public int getHammingDistance(String str1, String str2) {   
      char[] cArray1 = str1.toCharArray();
      char[] cArray2 = str2.toCharArray();
      int hammingDist = 0;
           
      if (cArray1.length != cArray2.length) {
         return -1;
      }
      
      for (int i = 0; i < cArray1.length; i++) {
         if (cArray1[i] != cArray2[i]) {
            hammingDist++;
         }
      }
      
      return hammingDist;
   }
   //stack DFS
   public List<String> getLadder(String start, String end) {
   
      List<String> ladderList = new ArrayList<String>();
      TreeSet<String> ladderListTree = new TreeSet<String>();      
      Deque<String> arrayDeque = new ArrayDeque<String>();
      
      if (start.equals(end)) {
         ladderList.add(start);
         return ladderList;
      }
      
      if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
      
      if (getHammingDistance(start, end) == -1) {
         return EMPTY_LADDER;
      }
      
      if (getNeighbors(start).isEmpty() || getNeighbors(end).isEmpty()) {
         return EMPTY_LADDER;
      }
     
      arrayDeque.add(start);
      ladderListTree.add(start);
      
      while (!arrayDeque.isEmpty()) {
         String word = arrayDeque.peekLast();
         List<String> neighbors = getNeighbors(word);
         
         word = neighbors.get(0);
         
         int inc = 0;
         
         while (ladderListTree.contains(word)) {         
            if (inc < neighbors.size()) {
               word = neighbors.get(inc++);
            }
            else if (inc >= neighbors.size()) {
               inc = 0;
               arrayDeque.remove(arrayDeque.peekLast());
               neighbors = getNeighbors(arrayDeque.peekLast());
               if (neighbors.isEmpty()) {
                  return neighbors;
               }
               word = neighbors.get(inc);
            }
         }
         arrayDeque.add(word);
         ladderListTree.add(word);
         
         if (arrayDeque.contains(end)) {
            break;
         }
      }
      
      for (String key : arrayDeque) {
         ladderList.add(key);
      }  
   
      return ladderList;
   }
   
   //queue BFS
   public List<String> getMinLadder(String start, String end) {
      
      Deque<Node> arrayQueue = new ArrayDeque<Node>();
      List<String> minLadderListBFS = new ArrayList<String>();
      TreeSet<String> visited = new TreeSet<String>();
      
      Node n = new Node(start, null);
      
      if (start.equals(end)) {
         return n.returnNode(n);
      }
      
      if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
      
      if (getHammingDistance(start, end) == -1) {
         return EMPTY_LADDER;
      }
      
      arrayQueue.addLast(n);
      
      while (!arrayQueue.isEmpty()) {
      
         word = arrayQueue.removeFirst();
         visited.add(word.element);             
      
         List<String> neighborsList = getNeighbors(word.element);
         
         for (String a : neighborsList) {
            if (!visited.contains(a)) {
               Node newNode = new Node(a, word);
               arrayQueue.addLast(newNode);           
            
               if (a.compareTo(end) == 0) {
                  break;
               }
            }
         }
         if (neighborsList.contains(end)) {
            break;
         }    
      }
         
      minLadderListBFS = n.returnNode(word);
      List<String> theListOfDestiny = new ArrayList<String>();
      for (int z = minLadderListBFS.size() - 1; z >= 0; z--) {
         theListOfDestiny.add(minLadderListBFS.get(z));
      }
      
      theListOfDestiny.add(end);
      
      if (!isWordLadder(theListOfDestiny)) {
         return EMPTY_LADDER;
      }
      
      return theListOfDestiny;
   }
   
   public List<String> getNeighbors(String word) {
      List<String> neighborsList = new ArrayList<String>();
      if (word == null) {
         return neighborsList;
      }
      for (int i = 0; i < word.length(); i++) {
         char[] lexArray = word.toCharArray();
         for (char lett = 'a'; lett <= 'z'; lett++) {
            lexArray[i] = lett;
            String s = new String(lexArray);
            if (isWord(s) && getHammingDistance(s, word) == 1) {
               neighborsList.add(s);
            }        
         }
      }
      return neighborsList;
   }
   
   public int getWordCount() {
      return lexicon.size();
   }
   
   public boolean isWord(String str) {
      if (lexicon.contains(str.toLowerCase())) {
         return true;
      } 
      else {
         return false;
      }
   }
   
   public boolean isWordLadder(List<String> sequence) {
   
      if (sequence.isEmpty()) {
         return false;
      }
   
      for (String word : sequence) {
         if (!isWord(word)) {
            return false;
         }
      }
      
      for (int i = 0; i < sequence.size(); i++) {
         if (i + 1 < sequence.size()) {
            if (getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1) {
               return false;
            }
         }
      }
      
      return true;
   }
   
   
   private class Node {
      private String element;
      private Node nex;
   
      public Node(String a, Node n) {
         nex = n;
         element = a;
      }
      
      public List<String> returnNode(Node nodeList) {
         List<String> returnList = new ArrayList<String>();
         while (nodeList != null) {
            returnList.add(nodeList.element);
            nodeList = nodeList.nex;
         }
         
         return returnList;
      }
   }
}

