import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2015-01-26
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      if (coll.size() == 1) {
         return coll.iterator().next();
      }
      
      T smallest = coll.iterator().next();
      for (T var : coll) {
         if (comp.compare(var, smallest) < 0) {
            smallest = var;
         }
      }
      
      return smallest;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      if (coll.size() == 1) {
         return coll.iterator().next();
      }
      
      T highest = coll.iterator().next();
      for (T var : coll) {
         if (comp.compare(var, highest) > 0) {
            highest = var;
         }
      }
      
      return highest;
   }
   
   private static <T> int disValues(Collection<T> coll, Comparator<T> comp) {   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Collection<T> tempArray = range(coll, min(coll, comp), max(coll, comp), comp);
      
      int val = 0;
      for (T valT : coll) {
         tempArray.remove(valT);
         if (tempArray.contains(valT)) {
            val++;
         }
      }
      
      int dis = (coll.size() - val);
      
      return dis;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || k < 1 || k > coll.size() || k > disValues(coll, comp)) {
         throw new NoSuchElementException();
      }
      
      if (coll.size() == 1 && k == 1) {
         return coll.iterator().next();
      }
      
      if (k == 1 && disValues(coll, comp) == 1) {
         return coll.iterator().next();
      }
      
      ArrayList<T> kMinCopy = new ArrayList<T>(coll);
      java.util.Collections.sort(kMinCopy, comp);
   
      int ranVal = 0;
      int nexVal = 1;
   
      while (ranVal < kMinCopy.size() - 1 && nexVal < k) {
         if (comp.compare(kMinCopy.get(ranVal), kMinCopy.get(ranVal + 1)) != 0) {
            nexVal++;
         }
         ranVal++;
      }
   
      if (nexVal == k) {
         return kMinCopy.get(ranVal);
      }
      else {
         throw new NoSuchElementException();
      }
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || k < 1 || k > coll.size() || k > disValues(coll, comp)) {
         throw new NoSuchElementException();
      }
      
      if (coll.size() == 1 && k == 1) {
         return coll.iterator().next();
      }
      
      if (k == 1 && disValues(coll, comp) == 1) {
         return coll.iterator().next();
      }
      
      ArrayList<T> kMaxCopy = new ArrayList<T>(coll);
      java.util.Collections.sort(kMaxCopy, java.util.Collections.reverseOrder(comp));
   
      int ranVal = 0;
      int nexVal = 1;
   
      while (ranVal < kMaxCopy.size() - 1 && nexVal < k) {
         if (comp.compare(kMaxCopy.get(ranVal), kMaxCopy.get(ranVal + 1)) != 0) {
            nexVal++;
         }
         ranVal++;
      }
   
      if (nexVal == k) {
         return kMaxCopy.get(ranVal);
      }
      else {
         throw new NoSuchElementException();
      }
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> collCopy = new ArrayList<T>(coll);
      ArrayList<T> rangeArray = new ArrayList<T>(0);
      
      if (comp.compare(low, high) <= 0) {
      
         for (T val : collCopy) {
            if (comp.compare(val, low) >= 0 && comp.compare(val, high) <= 0) {
               rangeArray.add(val);     
            }
         }
      }
      
      if (rangeArray.size() == 0) {
         throw new NoSuchElementException();
      }                                    
                                         
      return rangeArray;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Collection<T> ceilingRan = range(coll, key, (max(coll, comp)), comp);
      
      if (ceilingRan.isEmpty()) {
         throw new NoSuchElementException();   
      }
      
      T ceilingVal = min(ceilingRan, comp);
      
      return ceilingVal;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Collection<T> floorRan = range(coll, (min(coll, comp)), key, comp);
      
      if (floorRan.isEmpty()) {
         throw new NoSuchElementException();   
      }
      
      T floorVal = max(floorRan, comp);
      
      return floorVal;
   }
 
}
