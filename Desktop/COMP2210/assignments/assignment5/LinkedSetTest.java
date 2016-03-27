import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedSetTest {

   LinkedSet<Integer> a = new LinkedSet<Integer>();
   Set<Integer> b = new LinkedSet<Integer>();
   LinkedSet<Integer> c = new LinkedSet<Integer>();
   
   boolean expected;
   boolean actual;
   
   @Test
   public void add_test1() {
      actual = a.add(1);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test2() {
      actual = a.add(1);
      actual = a.add(1);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test3() {
      actual = a.add(null);
      expected = false;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test4() {
      a.add(1);
      a.add(2);
      a.add(3);
      a.add(4);
      actual = a.remove(2);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test7() {
      a.add(1);
      a.add(2);
      a.add(3);
      a.add(4);
      actual = a.remove(1);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test8() {
      a.add(1);
      a.add(2);
      a.add(3);
      a.add(4);
      actual = a.remove(4);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test5() {
      a.add(1);
      a.add(2);
      a.add(3);
      a.add(4);
      actual = a.contains(2);
      expected = true;
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test6() {
      a.add(1);
      a.add(4);
      a.add(3);
      a.add(5);
      
      b.add(4);
      b.add(3);
      b.add(5);
      b.add(1);
      
      System.out.println(a);
      System.out.println(b);
      
            
      actual = a.equals(b);
      expected = true;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test9() {
      a.add(1);
      a.add(4);
      a.add(3);
      a.add(5);
      
      c.add(4);
      c.add(3);
      c.add(5);
      c.add(1);
      c.add(2);
      
      System.out.println(a);
      System.out.println(c);
      
      c.remove(2);
      
            
      actual = a.equals(c);
      expected = true;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test10() {
      a.add(1);
      a.add(4);
      a.add(3);
      a.add(5);
      
      b.add(4);
      b.add(3);
      b.add(5);
      b.add(1);
      b.add(2);
      
      System.out.println(a);
      System.out.println(b);
      
      System.out.println(a.union(b));
      
            
      actual = a.equals(c);
      expected = false;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test11() {
      a.add(1);
      a.add(4);
      a.add(3);
      a.add(5);
      
      b.add(4);
      b.add(3);
      b.add(5);
      b.add(1);
      b.add(2);
      
      System.out.println(a);
      System.out.println(b);
      
      System.out.println(a.intersection(b));
      
            
      actual = a.equals(c);
      expected = false;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
   public void add_test12() {
      a.add(1);
      a.add(4);
      a.add(3);
      a.add(5);
      // a.add(6);
      
      b.add(4);
      b.add(3);
      b.add(5);
      b.add(1);
      b.add(2);
      
      System.out.println(a);
      System.out.println(b);
      
      System.out.println(a.complement(b));
      
            
      actual = a.equals(c);
      expected = false;
      
      Assert.assertEquals(actual, expected);
   }
   
}
