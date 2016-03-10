import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Lab3CGenerics.java
 * Used to illustrate basic principles of generic types
 * and type safety in Java.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2015-08-24
 *
 */

 ////////////////////////////////////////////////
 //
 // Add appropriate type parameters and arguments
 // to eliminate all unchecked warnings. That is,
 // make this code type safe.
 //
 ///////////////////////////////////////////////

public class Lab3CGenerics<T> {

   private List<T> al;

 /** Builds a new instance of this class. */
   public Lab3CGenerics() {
      al = new ArrayList<T>();
   }
 
 /** Adds all the values in c to this object. */
   public void addAll(Collection<T> c) {
      for (T o : c) {
         al.add(o);
      }
   }

 /** Returns a string representation of this object. */
   public String toString() {
      StringBuilder s = new StringBuilder();
      Iterator itr = al.iterator();
      while (itr.hasNext()) {
         s.append(itr.next());
         s.append(" ");
      }
      return s.toString();
   }

 /** Drives execution. */
   public static void main(String[] args) {
      Collection<Integer> c = new ArrayList<Integer>();
      for (int i = 1; i < 12; i += 2) {
         c.add(i);
      }
   
      Lab3CGenerics<Integer> lab = new Lab3CGenerics<Integer>();
      lab.addAll(c);
      System.out.println(lab.toString());
   }


}
