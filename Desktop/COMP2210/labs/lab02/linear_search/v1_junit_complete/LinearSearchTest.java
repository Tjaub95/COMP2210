import org.junit.Assert;
import org.junit.Ignore;
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
   int[] a1  = {2};                     // length 1
   int[] a2u = {2, 4};                  // length 2 no duplicates
   int[] a2d = {2, 2};                  // length 2 with duplicates
   int[] atu = {2, 4, 6, 8, 10};        // typical length no duplicates
   int[] atd = {2, 4, 6, 8, 10, 2, 8};  // typical length with duplicates

   int target;
   int expected;
   int actual;
   StringBuilder feedback;

   /** 
    * Tests with a null array reference.
    */
   @Ignore
   @Test(expected = IllegalArgumentException.class)
   public void testSearch_illegal_a_null() {
      target = 1;   // irrelevant
      expected = 0; // not really
      coreTest(null, target, expected);
   }

   /** 
    * Tests with an array of zero length.
    */
   @Ignore
   @Test(expected = IllegalArgumentException.class)
   public void testSearch_illegal_a_length0() {
      target = 1;   // irrelevant
      expected = 0; // not really
      int[] empty = new int[0];
      coreTest(empty, target, expected);
   }

   /** 
    * Tests with an array of length one with the target found.
    */
   @Test
   public void testSearch_length1_found() {
      target = a1[0];
      expected = 0;
      coreTest(a1, target, expected);
   }

   /** 
    * Tests with an array of length one with the target not found.
    */
   @Test
   public void testSearch_length1_not_found() {
      target = 1;
      expected = -1;
      coreTest(a1, target, expected);
   }

   /** 
    * Tests with an array of length two, unique values, with
    * target found at the first index.
    */
   @Test
   public void testSearch_length2_unique_found_front() {
      target = a2u[0];
      expected = 0;
      coreTest(a2u, target, expected);
   }

   /** 
    * Tests with an array of length two, unique values, with
    * target found at the last index.
    */
   @Test
   public void testSearch_length2_unique_found_rear() {
      target = a2u[1];
      expected = 1;
      coreTest(a2u, target, expected);
   }

   /** 
    * Tests with an array of length two, duplicate values, with
    * target found at the first index.
    */
   @Test
   public void testSearch_length2_dup_found_front() {
      target = a2d[1];
      expected = 0;
      coreTest(a2u, target, expected);
   }

   /** 
    * Tests with an array of length two, unique values, with
    * target less than all values in the array.
    */
   @Test
   public void testSearch_length2_unique_not_found_ltall() {
      target = 1;
      expected = -1;
      coreTest(a2u, target, expected);
   }

   /** 
    * Tests with an array of length two, unique values, with
    * target greater than all values in the array.
    */
   @Test
   public void testSearch_length2_unique_not_found_gtall() {
      target = 5;
      expected = -1;
      coreTest(a2u, target, expected);
   }

   /** 
    * Tests with an array of length two, unique values, with
    * target not found but in the range of values present.
    */
   @Test
   public void testSearch_length2_unique_not_found_midrange() {
      target = 3;
      expected = -1;
      coreTest(a2u, target, expected);
   }

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

   /** 
    * Tests with an array of typical length, duplicate values, with
    * target found at the first index.
    */
   @Test
   public void testSearch_lengthN_dup_found_front() {
      target = 2;
      expected = 0;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests with an array of typical length, duplicate values, with
    * target found near the middle index.
    */
   @Test
   public void testSearch_lengthN_dup_found_mid() {
      target = 8;
      expected = 3;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests with an array of typical length, unique values, with
    * target less than all values in the array.
    */
   @Test
   public void testSearch_lengthN_unique_not_found_ltall() {
      target = 1;
      expected = -1;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests with an array of typical length, unique values, with
    * target greater than all values in the array.
    */
   @Test
   public void testSearch_lengthN_unique_not_found_gtall() {
      target = 11;
      expected = -1;
      coreTest(atu, target, expected);
   }

   /** 
    * Tests with an array of typical length, unique values, with
    * target not found but in the range of values.
    */
   @Test
   public void testSearch_lengthN_unique_not_found_midrange() {
      target = 5;
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
