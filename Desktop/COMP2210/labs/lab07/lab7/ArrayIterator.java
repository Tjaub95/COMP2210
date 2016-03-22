import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an iterator over the elements of an array.
 *
 * @param   <T>   the type of elements in the array
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @author  John Lewis (dh@auburn.edu)
 * @author  Joseph Chase (dh@auburn.edu)
 * @version 2010-09-08
 *
 */
public class ArrayIterator<T> implements Iterator<T> {

/** the array of elements to be iterated over. */
   public T[] items; // public for jGRASP Canvas use

/** the number of elements in the array. */
   public int count; // public for jGRASP Canvas use
   
/** the current position in the iteration. */   
   public int current; // public for jGRASP Canvas use
   

/**
 * Initializes the iterator on a given array with
 * a given number of elements.
 *
 * @param collection the array to e iterated over
 * @param size       the number of elements in the array
 * 
 */   
   public ArrayIterator(T[] elements, int size) {
      items = elements;
      count = size;
      current = 0;
   }


/**
 * Returns true if this iterator has at least one more element
 * to deliver in the iteration.
 *
 * @return  true if at least one more element, false otherwise
 *
 */
   public boolean hasNext() {
      return (current < count);
   }


/**
 * Returns the next element in the iteration. If there are no
 * more elements in this iteration, a NoSuchElementException is
 * thrown.
 *
 * @return  the next element in the iteration
 *
 */
   public T next() {
      if (!hasNext()) {
         throw new NoSuchElementException();
      }
         
      return items[current++];
   }


/**
 * Remove is not supported in this iteration.
 *
 */
   public void remove() {
      throw new UnsupportedOperationException();
   }
}
