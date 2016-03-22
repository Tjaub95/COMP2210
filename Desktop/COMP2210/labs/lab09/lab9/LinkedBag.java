import java.util.Iterator;
import java.util.NoSuchElementException;
 
/**
 * Node-based implementation of the Bag interface.
 *
 * @param   <T>   type of elements in this collection
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2013-09-29
 * 
 */
public class LinkedBag<T> implements Bag<T> {

   private Node front;
   private int size;

/**
 * Instantiates an empty bag.
 *
 */
   public LinkedBag() {
      front = null;
      size = 0;
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
      Node n = new Node(element);
      n.next = front;
      if (front != null) {
         front.prev = n;
      }
      front = n;
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
      Node n = locate(element);         
      if (n == null) {
         return false;
      }
      
      if (n == front) {
         front = front.next;
         front.prev = null;
      }
      else {
         n.prev.next = n.next;
         if (n.next != null) {
            n.next.prev = n.prev;
         }
      }
      size--;
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
      return locate(element) != null;
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
      return size == 0;
   }

/**
 * Returns an iterator over the elements in this collection.
 *
 * @return  an iterator over the elements in this collection
 *
 */ 
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }

/**
 * Creates a string representation of this collection.
 * 
 * @return  string representation of this collection.
 *
 */
   @Override public String toString() {
      String result = "";
      Node n = front;
      while (n != null) {
         result += n.element + " ";
         n = n.next;
      }
      return result + "\n";
   }

/**
 * Locates the Node that contains element.
 * Can be used in contains for singly-linked nodes,
 * and in both contains and removes for doubly-linked nodes.
 */
   private Node locate(T element) {
      Node n = front;
      while (n != null) {
         if (n.element.equals(element)) {
            return n;
         }
         n = n.next;
      }
      return null;
   }




   private class Node {
      private T element;
      private Node next;
      private Node prev;
   
      public Node(T e) {
         element = e;
      }
   
   }

   private class LinkedIterator implements Iterator<T> {
      private Node current = front;
   
      public boolean hasNext() {
         return current != null;
      }
   
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
      
         T result = current.element;
         current = current.next;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }
}