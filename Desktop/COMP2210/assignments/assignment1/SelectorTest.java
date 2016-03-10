import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   int[] aNull = null;
   int[] aEmp = {};
   int[] a1 = {2}; //array with one element
   int[] a2u = {2, 1}; //array with two unique elements
   int[] a2d = {2, 2}; //array with two duplicates
   int[] a3u = {1, 2, 5, 6, 10}; //array with typical length
   int[] a3d = {3, 3, 20, 1, 7, 9, 20, 7, 1}; //array with duplicates at typical length
   
   int actual;
   int expected;
   
   @Test(expected = IllegalArgumentException.class)
   public void testSearch_illegal_a_null() {
      actual = Selector.min(aNull);
      Assert.assertEquals(actual, expected);
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testSearch_illegal_a_empty() {
      actual = Selector.min(aEmp);
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void one_element_min() {
      actual = Selector.min(a1);
      expected = 2;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void two_element_min() {
      actual = Selector.min(a2u);
      expected = 1;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void two_dup_element_min() {
      actual = Selector.min(a2d);
      expected = 2;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void typ_element_min() {
      actual = Selector.min(a3u);
      expected = 1;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void typ_dup_element_min() {
      actual = Selector.min(a3d);
      expected = 1;
      
      Assert.assertEquals(actual, expected);
   }


   @Test(expected = IllegalArgumentException.class)
   public void testSearch_illegal_a_null2() {
      actual = Selector.max(aNull);
      Assert.assertEquals(actual, expected);
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testSearch_illegal_a_empty2() {
      actual = Selector.max(aEmp);
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void one_element_max() {
      actual = Selector.max(a1);
      expected = 2;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void two_element_max() {
      actual = Selector.max(a2u);
      expected = 2;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void two_dup_element_max() {
      actual = Selector.max(a2d);
      expected = 2;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void typ_element_max() {
      actual = Selector.max(a3u);
      expected = 10;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void typ_dup_element_max() {
      actual = Selector.max(a3d);
      expected = 20;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void typ_dup_element_kmin() {
      actual = Selector.kmin(a3d, 4);
      expected = 9;
      
      Assert.assertEquals(actual, expected);
   }


}
