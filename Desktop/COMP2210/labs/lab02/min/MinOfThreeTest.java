import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   int a;
   int b;
   int c;
   int actual;
   int expected;
    
   @Test
    public void a_lt_b_and_c() {
      a = 1;
      b = 2;
      c = 3;
      
      actual = MinOfThree.min1(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void b_lt_a_and_c() {
      a = 2;
      b = 1;
      c = 3;
      
      actual = MinOfThree.min1(a, b, c);
      expected = b;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void c_lt_a_and_b() {
      a = 3;
      b = 2;
      c = 1;
      
      actual = MinOfThree.min1(a, b, c);
      expected = c;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void all_equal() {
      a = 1;
      b = 1;
      c = 1;
      
      actual = MinOfThree.min1(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void a_lt_b_and_c2() {
      a = 1;
      b = 2;
      c = 3;
      
      actual = MinOfThree.min2(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void b_lt_a_and_c2() {
      a = 2;
      b = 1;
      c = 3;
      
      actual = MinOfThree.min2(a, b, c);
      expected = b;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void c_lt_a_and_b2() {
      a = 3;
      b = 2;
      c = 1;
      
      actual = MinOfThree.min2(a, b, c);
      expected = c;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void all_equal2() {
      a = 1;
      b = 1;
      c = 1;
      
      actual = MinOfThree.min2(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void two_equal() {
      a = 1;
      b = 1;
      c = 3;
      
      actual = MinOfThree.min1(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void two_equal2() {
      a = 1;
      b = 1;
      c = 3;
      
      actual = MinOfThree.min2(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void two_equal3() {
      a = 1;
      b = 2;
      c = 1;
      
      actual = MinOfThree.min2(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
   
   @Test
    public void two_equal4() {
      a = 1;
      b = 2;
      c = 1;
      
      actual = MinOfThree.min1(a, b, c);
      expected = a;
      
      Assert.assertEquals(actual, expected);
   }
    
    // private void coreTest1(int a, int b, int c) {
//       feedback = new StringBuilder();
//       actual = MinOfThree.min1(a, b, c);
//       feedback.append("First int = " + a + " second int = " + b);
//       feedback.append(" third int = " + c + " mininum = " + actual);
//       Assert.assertEquals(feedback.toString(), actual, actual);
//    }
// 
    
}
