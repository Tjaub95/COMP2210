/**
 * LinearSearch.java
 * Implements a simple linear search method on arrays of ints.
 *
 * @author   YOUR NAME (you@auburn.edu)
 * @author:  Dean Hendrix (dh@auburn.edu)
 * @version: 2014-08-18
 *
 */

public final class LinearSearch {

   /**
    * Uses the linear search algorithm to return
    * the index of the first occurence of target in a.
    * If target is not present, returns -1.
    *
    * @param   a  the array to be searched through
    * @param   target  the value to be searched for
    * @return  the index of the first occurence of target in a
    *          or -1 if a does not contain target
    */
   public static int search(int[] a, int target) {
      int i = 0;
      while ((i < a.length) && (a[i] != target)) {
         i++;
      }
      if (i < a.length) {
         return i;
      } 
      else {
         return -1;
      }
   }

}
