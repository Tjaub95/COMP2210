import org.junit.Assert;
import org.junit.Test;

/**
 * LinearSearchTestWithJUnit.java
 * Tests LinearSearch.search using JUnit.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2015-08-17
 *
 */

public final class LinearSearchTest {

   // test fixtures
   private int[] a1  = {2};                     // length 1
   private int[] a2u = {2, 4};                  // length 2 no duplicates
   private int[] a2d = {2, 2};                  // length 2 with duplicates
   private int[] atu = {2, 4, 6, 8, 10};        // typical length no duplicates
   private int[] atd = {2, 4, 6, 8, 10, 2, 8};  // typical length with duplicates

   private int target;
   private int expected;
   private int actual;
   private StringBuilder feedback;

   /** 
    * Tests with an array of typical length, unique values, with
    * target found at the first index.
    */
   @Test
   public void testSearch_lengthN_unique_found_front() {
      target = 2;
      expected = 0;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests with an array of typical length, unique values, with
    * target found near the middle index.
    */
   @Test
   public void testSearch_lengthN_unique_found_mid() {
      target = 6;
      expected = 2;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests with an array of typical length, unique values, with
    * target found at the last index.
    */
   @Test
   public void testSearch_lengthN_unique_found_rear() {
      target = 10;
      expected = 4;
      coreTest(atu, target, expected);
   }
   
   @Test
   public void testSearch_lengthN_target_not_found() {
      target = 11;
      expected = -1;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests that the return value of LinearSearch.search is
    * equal to the expected value when called with a given
    * array and target. 
    */
   private void coreTest(int[] a, int target, int expected) {
      feedback = new StringBuilder();
      actual = LinearSearch.search(a, target);
      feedback.append("a = " + toString(a) + " target = " + target);
      feedback.append(" expected = " + expected + " actual = " + actual);
      Assert.assertEquals(feedback.toString(), expected, actual);
   }

   /** 
    * Returns a string representation of an array.
    */
   private String toString(int[] a) {
      StringBuilder s = new StringBuilder();
      s.append("[");
      for (int i : a) {
         s.append(i + ",");
      }
      s.delete(s.length() - 1, s.length());
      s.append("]");
      return s.toString();
   }

}
