import java.util.Iterator;

/**
 * A simple client of the Bag interface and the
 * LinkedBag class.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2015-09-30
 *
 */    
public class LinkedBagClient {

/**
 * Exercises Bag and LinkedBag methods.
 *
 * @param args command line arguments not used.
 *
 */
   public static void main(String[] args) {
   
   ////
      Bag<String> sb = new LinkedBag<String>();
      sb.add("A"); 
      sb.add("B"); 
      sb.add("C");
      sb.add("D"); 
      sb.add("E");
      System.out.println(sb);
      sb.remove("A"); 
      sb.remove("B");
      sb.remove("C");
      sb.remove("D");
      System.out.println(sb);
      
   ////
      Bag<Book> bookBag = new LinkedBag<Book>();
      bookBag.add(new Book("Knuth", 
                           "The Art of Computer Programming Vol. 3, Sorting and Searching", 
                           723));
      bookBag.add(new Book("Knuth", 
                           "The Art of Computer Programming Vol. 1, Fundamental Algorithms", 
                           634));
      bookBag.add(new Book("Weiss", 
                           "Data Structures and Algorithm Analysis in Java", 
                           614));
      bookBag.add(new Book("Sedgewick", 
                           "Algorithms in Java", 
                           737));
      System.out.println(bookBag);
   
      Book target = new Book("Knuth", 
                              "The Art of Computer Programming Vol. 4A, Combinatorial Algorithms", 
                              883);
      System.out.println(bookBag.contains(target));
   
   ////
      Bag<String> strBag = new LinkedBag<String>();
   
      strBag.add("red");
      strBag.add("orange");
      strBag.add("yellow");
      strBag.add("green");
      strBag.add("blue");
      strBag.add("indigo");
      strBag.add("violet");
      strBag.add("orange");
      strBag.add("green");
      strBag.add("green");
      
      System.out.println(strBag);
      
      System.out.println(strBag.contains("orange"));      
      strBag.remove("orange");
      System.out.println(strBag.contains("orange"));  
      
      System.out.println(strBag.contains("blue"));    
      strBag.remove("orange");
      System.out.println(strBag.contains("orange"));  
      
      int dup = 0;
      String current;
      Iterator<String> itr = strBag.iterator();
      while (itr.hasNext()) {
         current = itr.next();
         if (current.compareTo("green") == 0) {
            dup++;
         }
      }
      System.out.println(dup);
   
      System.out.println(strBag);
   
   }
}
