import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-03-13
 *
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
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


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      //Needs to be O(N)
      if (element == null || this.contains(element)) {
         return false;
      }
      
      if (front == null) {
         front = new Node(element);
         rear = front;
      }
      
      else if (front.element.compareTo(element) > 0) {
         Node temp = front;
         Node newNode = new Node(element);
         front = newNode;
         front.next = temp;
         temp.prev = newNode;
      }
      
      else {
         Node tempN = front;
         
         while (tempN.next != null && tempN.next.element.compareTo(element) < 0) {
            tempN = tempN.next;
         }
         
         Node prevT = tempN;
         Node insertN = new Node(element);
         
         if (tempN.next != null) {
            Node nextT = tempN.next;
            insertN.next = nextT;
            nextT.prev = insertN;
         }
         prevT.next = insertN;
         insertN.prev = prevT;
         if (insertN.element.compareTo(prevT.element) > 0) {
            rear = insertN;
         }
      }
            
      size++;
      
      return true;
   
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
   //Needs to be O(N)
      Node n = locate(element);         
      if (n == null) {
         return false;
      }
      /*Need to work on remove
      for complement if I remove all
      elements in this. */
      if (n == front) {
         front = front.next;
         if (front != null) {
            front.prev = null;
         }
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
   
   private Node locate(T element) {
   //Is O(N)
      Node n = front;
      while (n != null) {
         if (n.element.equals(element)) {
            return n;
         }
         n = n.next;
      }
      return null;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
   //Is O(N)
      return locate(element) != null;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      boolean same = false;
   
      if (this.isEmpty() || s.isEmpty()) {
         return false;
      }
   
      if (this.size() != s.size()) {
         return false;     
      }
     
      
      for (T element : s) {
         if (this.contains(element)) {
            same = true;
         } 
         else {
            return false;
         } 
      }
      return same; 
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      boolean same = false;
   
      if (this.isEmpty() || s.isEmpty()) {
         return false;
      }
   
      if (this.size() != s.size()) {
         return false;     
      }
      
      Node n = this.front;
      Node e = s.front;
      
      for (T element : s) {
         if (n.element.equals(e.element)) {
            same = true;
         }
         else {
            return false;
         }
      } 
   
      return same;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s) {
      //O(N X M)
      if (this.equals(s)) {
         return this;
      }
      
      if (this.isEmpty()) {
         return s;
      } 
      else if (s.isEmpty()) {
         return this;
      }
      
      Set<T> unionSet = new LinkedSet<T>();
   
      for (T element : this) {
         unionSet.add(element);
      }
      
      for (T sElement : s) {
         unionSet.add(sElement);
      }
      
      
      return unionSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s) {
      //O(max(N, M))
      if (this.size() == s.size()) {
         return this;
      }
      
      if (this.isEmpty()) {
         return s;
      } 
      else if (s.isEmpty()) {
         return this;
      }
      
     
      
      return null;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      //O(N X M)
      Set<T> intersectionSet = new LinkedSet<T>();
      
      if (this.equals(s)) {
         return this;
      }
      
      if (this.isEmpty() || s.isEmpty()) {
         return intersectionSet;
      }
      
      for (T sElement : s) {
         if (this.contains(sElement)) {
            intersectionSet.add(sElement);
         }
      }
      
      return intersectionSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      //O(max(N,M))
      if (this.equals(s)) {
         return this;
      } 
      else if (this.isEmpty()) {
         return this;
      } 
      else if (s.isEmpty()) {
         return s;
      }
      return null;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      //O(N X M)
      Set<T> complementSet = new LinkedSet<T>();
      if (this.equals(s)) {
         return complementSet;
      }
      
      if (this.isEmpty() || s.isEmpty()) {
         return this;
      }
      
      for (T sElement : s) {
         this.remove(sElement);
      }
      
      for (T thisElement : this) {
         complementSet.add(thisElement);
      }
      
      
      return complementSet;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      //O(max(N,M))      
      Set<T> complementSet = new LinkedSet<T>();
      if (this.equals(s)) {
         return complementSet;
      }
      
      if (this.isEmpty() || s.isEmpty()) {
         return this;
      }
      
      return null;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedSetItr();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return null;
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      // Iterator<Set<T>> itr = new Iterator<Set<T>>() {
   //             int value = 0;
   //          
   //             public boolean hasNext() {
   //                return (value < (1 << size));
   //             }
   //          
   //             public Set<T> next() {
   //                if (!hasNext()) {
   //                   throw new NoSuchElementException();
   //                } 
   //                else {
   //                   Set<T> powSet = new LinkedSet<T>();
   //                   Node n = front.next;
   //                   while (n != null) {
   //                   
   //                      if (((value >> 1) & 1) == 1) {
   //                      
   //                         powSet.add(n.element);
   //                      }
   //                   }
   //                   value++;              
   //                   return powSet;
   //                
   //                }
   //             }
   //          
   //             public void remove() {
   //                throw new UnsupportedOperationException();
   //             }
   //          };
   //       return itr;
      return null;
   }




   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }
   
   class LinkedSetItr implements Iterator<T> {
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
