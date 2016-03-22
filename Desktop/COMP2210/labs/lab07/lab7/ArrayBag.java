import java.util.Iterator;
import java.util.Arrays;
 
/**
 * Array-based implementation of the Bag interface.
 *
 * @param   <T>   type of elements in this collection
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2015-02-23
 * 
 */
public class ArrayBag<T> implements Bag<T>, Iterable<T> {

   private static final int DEFAULT_CAPACITY = 1;

   private T[] elements;
   private int size;

/**
 * Instantiates a collection of a given capacity.
 * Not available to clients.
 *
 * @param capacity   maximum capacity of this collection
 *
 */
   @SuppressWarnings("unchecked")
   public ArrayBag(int capacity) {
      elements = (T[]) new Object[capacity];
      size = 0;
   }
   
/**
 * Default constructor.
 *
 */
   public ArrayBag() {
      this(DEFAULT_CAPACITY);
   }  
   
/**
 * Ensures the collection contains the specified element.
 * No specific order is maintained, duplicates are allowed.
 *
 * @param  element  The element whose presence is to be ensured.
 * @return true if collection is changed, false otherwise
 *
 */
   public boolean add(T element) {
      if (isFull()) {
         resize(elements.length * 2); 
      }
      elements[size] = element;
      size++;
      return true;
   }
   
/**
 * Removes a single instance of the specified element from this collection.
 *
 * @param   element  the element to be removed, if present
 * @return  true if element was removed
 *
 */
   public boolean remove(T element) {
      // attempt to locate the element
      int i = locate(element);
            
      // unable to locate
      if (i < 0) {
         return false;
      }
      
      // located at i, so remove it
      elements[i] = elements[--size];
      elements[size] = null;
      
      // resize if needed
      if (size > 0 && isSparse()) {
         resize(elements.length / 2);
      }
      
      return true;
   }
   
   public boolean removeWithShift(T element) {
      // attempt to locate the element
      int i = locate(element);
            
      // unable to locate
      if (i < 0) {
         return false;
      }
      
      // located at i, so remove it by
      // shifting the remaining elements one
      // position to the left
      shiftLeft(i);
      elements[size] = null;
      size--;
      
      // resize if needed
      if (size > 0 && isSparse()) {
         resize(elements.length / 2);
      }
      
      return true;
   }

/**
 * Searches for specified element in this collection.
 *
 * @param   element  element whose presence in this collection is to be tested
 * @return  true if this collection contains the specified element
 *
 */
   public boolean contains(T element) {
      return locate(element) >= 0;
   }

/**
 * Returns the current size of this collection.
 *
 * @return  the number of elements in this collection.
 *
 */
   public int size() {
      return size;
   }

/**
 * Returns true if this collection contains no elements.
 *
 * @return  true if this collection contains no elements.
 *
 */
   public boolean isEmpty() {
      return size() == 0;
   }


/**
 * Returns an iterator over the elements in this collection.
 *
 * @return  an iterator over the elements in this collection
 *
 */ 
   public Iterator<T> iterator() {
      return new ArrayIterator<T>(elements, size());
   }

/**
 * Creates a string representation of this collection.
 * 
 * @return  string representation of this collection.
 *
 */
   @Override 
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }
   
/**
 * Returns true if this collection is full.
 *
 * @return  true if this collection is full.
 *
 */   
   private boolean isFull() {
      return size == elements.length;
   }  
   
/**
 * Returns true if the array is less than
 * 25% full.
 *
 * @return  true if the array is less than 25% full.
 *
 */   
   private boolean isSparse() {
      return size < elements.length/4;
   }  
   
   /**
    * Resize the backing array.
    */
   private void resize(int capacity) {
      T[] a = Arrays.<T>copyOf(elements, capacity);
      elements = a;
   }
  
   /**
    * Shift elements[loc+1] through elements[size-1] to the left
    * by one position.
    */
   private void shiftLeft(int loc) {
      for (int i = loc+1; i < size; i++) {
         elements[i-1] = elements[i];
      }
      elements[size-1] = null;
   }

/**
 * Utility search method. Returns the index of target
 * in the array elements if it is present, -1 otherwise.
 * Provides common code to contains() and remove().
 *
 * @param   target   value to be located in elements
 * @return  index of target if present, -1 otherwise
 *
 */
   private int locate(T element) {
      for (int i = 0; i < size; i++) {
         if (elements[i].equals(element))
            return i;
      }
      return -1;
   }

}
