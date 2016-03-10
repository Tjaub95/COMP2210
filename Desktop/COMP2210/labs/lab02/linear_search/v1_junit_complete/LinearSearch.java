/**
 * LinearSearch.java
 * Implements a simple linear search method on arrays of ints.
 *
 * @author   YOUR NAME (you@auburn.edu)
 * @author:  Dean Hendrix (dh@auburn.edu)
 * @version: 2015-08-11
 *
 */

public final class LinearSearch {

   /**
    * This method uses the linear search algorithm to return
    * the index of the first occurence of target in a.
    *
    * @param   a  the array to be searched through
    * @param   target  the value to be searched for
    * @return  the index of the first occurence of target in a
    *          or -1 if a does not contain target
    */
   public static int search(int[] a, int target) {
      for (int i = 0; i < a.length; i++) {
         if (a[i] == target) {
            return i;
         }
      }
      return -1;
   }

}
