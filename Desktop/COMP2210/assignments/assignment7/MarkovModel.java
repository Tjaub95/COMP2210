import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Tyler Jewell (tjj0013@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2016-04-19
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;
   
   // add other fields as you need them ...   
   private int orderOfK;
   
   private String source;
   
   private Random rndm;
   
   private ArrayList<String> kGrams;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      int order = K;
      orderOfK = K;
      kGrams = new ArrayList<String>();
      source = sourceText;
      
      for (int i = 0; order < sourceText.length(); i++) {
         String kgram = sourceText.substring(i, order);
         String nextChar = String.valueOf(sourceText.charAt(order));
         kGrams.add(kgram);
         if (model.containsKey(kgram)) {
            String val = model.get(kgram);
            val += nextChar;
            model.put(kgram, val);
         } 
         else {
            model.put(kgram, nextChar);
         
         }
         order++;
      }     
      
   }

   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {    
      return kGrams.get(0);
   }

   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      rndm = new Random();
      int randomNum = rndm.nextInt(source.length());
      
      while ((randomNum + orderOfK) > source.length()) {
         randomNum = rndm.nextInt(source.length());
      }
      
      return source.substring(randomNum, randomNum + orderOfK);
   }

   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      rndm = new Random();
      String nextChar = model.get(kgram);
      int randomNum = rndm.nextInt(nextChar.length());
      
      return nextChar.charAt(randomNum);
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
