import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Tyler Jewell (tjj0013@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2016-01-15
*
*/
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


/**
 * Selects the minimum value from the array a. This method
 * throws IllegalArgumentException if a is null or has zero
 * length. The array a is not changed by this method.
 *
 * @param a     the array to be searched through
 * @return      minimum value in a
 *
 */
   public static int min(int[] a) {
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
      if (a.length == 1) {
         return a[0];
      }
   
      int min = a[0];
   
      for (int i = 1; i < a.length; i++) {
         if (a[i] <= min) {
            min = a[i];
         }
      }
   
      return min;
   }


/**
 * Selects the maximum value from the array a. This method
 * throws IllegalArgumentException if a is null or has zero
 * length. The array a is not changed by this method.
 *
 *
 * @param a     the array to be searched through
 * @return      maximum value in a
 *
 */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
      if (a.length == 1) {
         return a[0];
      }
   
      int max = a[0];
   
      for (int i = 1; i < a.length; i++) {
         if (max <= a[i]) {
            max = a[i];
         }
      }
   
      return max;
   }
   
/**
 * Finds the number of unique values found in
 * the array and returns it.
 *
 *
 * @param a     the array to be iterated through
 * @return      the number of distunct values
 */   

   private static int distinctNums(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length);
      int duplicates = 0;
      int i = 1;
      Arrays.sort(b);
      for (int num : b) {
         if (!(i < b.length)) {
            break;
         }
         if (num == b[i]) {
         
            duplicates++;
         }
         i++;
      }
      return (b.length - duplicates);
   }


/**
 * Selects the kth minimum value from the array a. This method
 * throws IllegalArgumentException if a is null, has zero length,
 * or if there is no kth minimum value. Note that there is no kth
 * minimum value if k < 1, k > a.length, or if k is larger than
 * the number of distinct values in the array. The array a is not
 * changed by this method.
 *
 *
 *
 * @param a     the array to be searched through
 * @param k     the selection value
 * @return      the kth minimum value in a
 */
   public static int kmin(int[] a, int k) {
   
      if (a == null || a.length == 0 || k < 1 || k > a.length || k > distinctNums(a)) {
      
         throw new IllegalArgumentException();
      }
   
      if (a.length == 1 && k == 1 ) {
         return a[0];
      }
   
      if (a.length == 2 && k == 2 && a[0] != a[1]) {
         int[] b = Arrays.copyOf(a, a.length);
         Arrays.sort(b);
         return b[1];
      }
   
      int[] aSorted = Arrays.copyOf(a, a.length);
      Arrays.sort(aSorted);
      int[][] aRanked = new int[aSorted.length][2];
      for (int i = 0; i < aSorted.length; i++) {
         aRanked[i][0] = aSorted[i];
      }
   
      aRanked[0][1] = 1;
      int var = 1;
      int varRank = 1;
      int target = 0;
   
      if (k == 1) {
         target = aRanked[0][0];
      }
      else {
         for (int j: aSorted) {
            if (var < aSorted.length) {
               if (j == aSorted[var]) {
                  aRanked[var][1] = varRank;
                  if (varRank == k) {
                     target = aRanked[var][0];
                     break;
                  }
               }
               else {
                  ++varRank;
                  aRanked[var][1] = varRank;
                  if (varRank == k) {
                     target = aRanked[var][0];
                     break;
                  }
               }
               var++;
            }
         }
      }
   
   
      return target;
   }
   
/**
 * Sorts the array from descending order.
 * 
 *
 * @param a     the array to be iterated through
 * @return      the array sorted from high to low
 */
   private static int[] highToLow(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
      int[] aSorted = Arrays.copyOf(a, a.length);
      Arrays.sort(aSorted);
   
      int[] highLowArray = new int[aSorted.length];
   
      int highLowCount = aSorted.length - 1;
   
      for (int i: aSorted) {
      
         highLowArray[highLowCount] = i;
         highLowCount--;
      
      }
      return highLowArray;
   }


/**
 * Selects the kth maximum value from the array a. This method
 * throws IllegalArgumentException if a is null, has zero length,
 * or if there is no kth maximum value. Note that there is no kth
 * maximum value if k < 1, k > a.length, or if k is larger than
 * the number of distinct values in the array. The array a is not
 * changed by this method.
 *
 *
 *
 * @param a     the array to be searched through
 * @param k     the selection value
 * @return      the kth maximum value in a
 */
   public static int kmax(int[] a, int k) {
   
      if (a == null || a.length == 0 || k < 1 || k > a.length || k > distinctNums(a)) {
      
         throw new IllegalArgumentException();
      }
   
      if (a.length == 1 && k == 1) {
         return a[0];
      }
   
      if (a.length == 2 && k == 2 && a[0] != a[1]) {
         return (highToLow(a))[1];
      }
   
      int[] highLowA = highToLow(a);
      int[][] highLowRank = new int[a.length][2];
      for (int i = 0; i < highLowA.length; i++) {
         highLowRank[i][0] = highLowA[i];
      }
   
      highLowRank[0][1] = 1;
      int var = 1;
      int varRank = 1;
      int target = 0;
   
      if (k == 1) {
         target = highLowRank[0][0];
      }
      else {
         for (int j: highLowA) {
            if (var < highLowA.length) {
               if (j == highLowA[var]) {
                  highLowRank[var][1] = varRank;
                  if (varRank == k) {
                     target = highLowRank[var][0];
                     break;
                  }
               }
               else {
                  ++varRank;
                  highLowRank[var][1] = varRank;
                  if (varRank == k) {
                     target = highLowRank[var][0];
                     break;
                  }
               }
               var++;
            }
         }
      }
   
   
      return target;
   }

/**
 * Adds the value to the array and returns it with
 * with a new array that contains it.
 * 
 *
 * @param a     the array to be iterated through
 * @param value the value to be added to the array
 * @return      the new array containing the value
 */
   private static int[] arrayAdder(int[] a, int value) {
      if (a == null) {
         throw new IllegalArgumentException();
      }
      int[] temporary = new int[(a.length + 1)];
   
      int var = 0;
      for (int numVal : a) {
         temporary[var] = numVal;
         var++;
      }
      temporary[var] = value;
   
      return temporary;
   }


/**
 * Returns an array containing all the values in a in the
 * range [low..high]; that is, all the values that are greater
 * than or equal to low and less than or equal to high,
 * including duplicate values. The length of the returned array
 * is the same as the number of values in the range [low..high].
 * If there are no qualifying values, this method returns a
 * zero-length array. Note that low and high do not have
 * to be actual values in a. This method throws an
 * IllegalArgumentException if a is null or has zero length.
 * The array a is not changed by this method.
 *
 *
 *
 * @param a     the array to be searched through
 * @param low   the low value
 * @param high  the high value
 * @return      an array containing a range of elements
 */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
      int[] rangeArray = new int[0];
      if (low <= high) {
         if (a.length == 1 && low >= a[0] && high <= a[0]) {
            return a;
         }
      
         for (int rangeVal : a) {
            if (rangeVal >= low && rangeVal <= high) {
               rangeArray = Selector.arrayAdder(rangeArray, rangeVal);
            }
         }
      }
    
      return rangeArray;
   }


/**
 * Returns the smallest value in a that is greater than or equal to
 * the given key. This method throws an IllegalArgumentException if
 * a is null or has zero length, or if there is no qualifying
 * value. Note that key does not have to be an actual value in a.
 * The array a is not changed by this method.
 *
 *
 *
 * @param a     the array to be searched through
 * @param key   the reference value
 * @return      the the next greatest value from key
 */
   public static int ceiling(int[] a, int key) {
      
      int min = Selector.min(a);
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (a.length == 1 && a[0] >= key) {
         return a[0];
      }
            
      int found = -1;
   
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            if (found == -1) {
               found = i;
            } 
            else if (a[i] <= a[found]) {
               found = i;
            }
         }
      }
      
      if (found == -1) {
         throw new IllegalArgumentException();
      }
      else {
         return a[found];
      }
      
               
   }


/**
 * Returns the largest value in a that is less than or equal to
 * the given key. This method throws an IllegalArgumentException if
 * a is null or has zero length, or if there is no qualifying
 * value. Note that key does not have to be an actual value in a.
 * The array a is not changed by this method.
 *
 *
 *
 * @param a     the array to be searched through
 * @param key   the reference value
 * @return      the the next smallest value from key
 */
   public static int floor(int[] a, int key) {
   
      int min = Selector.min(a);
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (a.length == 1 && a[0] <= key) {
         return a[0];
      }
      
         
      int found = -1;
   
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            if (found == -1) {
               found = i;
            } 
            else if (a[i] >= a[found]) {
               found = i;
            }
         }
      }
      
      if (found == -1) {
         throw new IllegalArgumentException();
      }
      else {
         return a[found];
      }
   }
   
}
