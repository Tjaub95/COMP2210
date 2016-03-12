import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArraySetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   ArraySet<Integer> a = new ArraySet<Integer>();
   ArraySet<Integer> b = new ArraySet<Integer>();
   Set<Integer> c = new ArraySet<Integer>();
   Set<Integer> d = new ArraySet<Integer>();
   boolean expected;
   boolean actual;

   //Test with one element
   @Test
   public void add_test1() {
      actual = a.add(1);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   //Test adding null
   @Test
   public void add_test2() {
      actual = a.add(null);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   //Test adding a duplicate value
   @Test
   public void add_test3() {
      a.add(2);
      actual = a.add(2);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   //Test adding a varying number of params w/ null
   @Test
   public void add_test4() {
      a.add(1);
      a.add(3);
      a.add(5);
      actual = a.add(null);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   //Test adding a varying number w/ duplicate
   @Test
   public void add_test5() {
      a.add(1);
      a.add(4);
      a.add(5);
      a.add(6);
      a.add(10);
      a.add(3);
      actual = a.add(5);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   //Test adding a varying number
   @Test
   public void add_test6() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      actual = a.add(1);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test7() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.add(1);
      System.out.print(a.toString());
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test8() {
      a.add(4);
      a.add(1);
      a.add(5);
      a.add(3);
      a.add(1);
      a.add(2);
      a.remove(4);
      a.remove(1);
      a.remove(5);
      actual = a.remove(2);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void contains_test1() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.contains(1);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void contains_test2() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.contains(null);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void contains_test3() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.contains(-100);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void contains_test4() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.contains(8);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void contains_test5() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.contains(90);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void remove_test1() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.remove(1);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void remove_test2() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.remove(null);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void remove_test3() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.remove(-100);
      expected = false;
      Assert.assertEquals(actual, expected);
   }

   @Test
   public void remove_test4() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.remove(90);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void remove_test5() {
      a.add(90);
      a.add(10);
      a.add(5);
      a.add(6);
      a.add(7);
      a.add(4);
      a.add(20);
      a.add(65);
      a.add(11);
      a.add(9);
      a.add(8);
      actual = a.remove(4);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void equals_test1() {
      a.add(1);
      a.add(2);
      a.add(3);
      b.add(2);
      b.add(3);
      b.add(4);
      a.intersection(b);
      actual = a.equals(b);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void equals_test2() {
      a.add(1);
      a.add(2);
      a.add(3);
      a.add(6);
      b.add(5);
      b.add(4);
      b.add(2);
      b.add(3);
      // c.add(1);
      d = a.union(b);
      System.out.println(d);
      actual = a.equals(b);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
}