import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArraySet.java.
 *
 * Provides an implementation of the Set interface using an
 * array as the underlying data structure. Values in the array
 * are kept in ascending natural order and, where possible,
 * methods take advantage of this. Many of the methods with parameters
 * of type ArraySet are specifically designed to take advantage
 * of the ordered array implementation.
 *
 * @author Tyler Jewell (tjj0013@auburn.edu)
 * @author	Dean Hendrix (dh@auburn.edu)
 * @version	2016-03-01
 *
 */
public class ArraySet<T extends Comparable<? super T>> implements Set<T> {

   ////////////////////////////////////////////
   // DO NOT CHANGE THE FOLLOWING TWO FIELDS //
   ////////////////////////////////////////////
   T[] elements;
   int size;

   ////////////////////////////////////
   // DO NOT CHANGE THIS CONSTRUCTOR //
   ////////////////////////////////////
   /**
    * Instantiates an empty set.
    */
   @SuppressWarnings("unchecked")
   public ArraySet() {
      elements = (T[]) new Comparable[1];
      size = 0;
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
    * @return  true if this collection contains no elements,
    *               false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this ArraySet.
    *
    * @return a string representation of this ArraySet
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
    * Ensures the collection contains the specified element.
    * No specific order can be assumed. Neither duplicate nor null
    * values are allowed.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
   
      int temp = 0;
   
      if (element == null) {
         return false;
      }
      
      if (this.contains(element)) {
         return false;
      }
      
      if (isFull()) {
         resize(elements.length * 2);
      }
      
      for (int i = 0; i < size(); i++) {
         if (elements[i] != null && elements[i].compareTo(element) > 0) {
            temp = i;
            break;
         }
         temp = i + 1; 
      }
      
      int pos = temp;
      for (int i = size(); i > pos; i--) {           // move bigger ones one up.
         elements[i] = elements[i - 1];
      }
      elements[pos] = element;                            // insert value
      size++;
      
      return true;
   }
      
   private boolean isFull() {
      return size == elements.length;
   }
   
   @SuppressWarnings("unchecked")
   private void resize(int cap) {
      T[] a = (T[]) new Comparable[cap];
      
      for (int i = 0; i < size(); i++) {
         a[i] = elements[i];
      }
      elements = a;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
   
      if (element == null) {
         return false;
      }
   
      int i = locate(element);
      
      if (i < 0 || i >= size) {
         return false;
      }
      
      shiftLeft(i);
      size--;
      
      if (size() > 0 && size() < elements.length / 4) {
         resize(elements.length / 2);
      }
      
      return true;
   }
   
   private void shiftLeft(int pos) {
      for (int i = pos; i < size; i++) {
         if (size - i == 1) {
            elements[size - 1] = null;
            break;
         } 
         else {
            elements[i] = elements[i + 1];
         }
      }
   }
   
   //Needs to be rewritten to employ Binary Search
   private int locate(T element) {
   
      if (element == null) {
         return -1;
      }
   
      if (size == 0) {
         return -1;
      }
   
      int lower = 0;
      int upper = size - 1;
      
      while (lower <= upper) {
         int mid = lower + (upper - lower) / 2;     
         if (element != null && element.compareTo(elements[mid]) < 0) {
            upper = mid - 1; // its in the upper
         }
         else if (element != null && element.compareTo(elements[mid]) > 0) {
            lower = mid + 1;
         } 
         else {
            return mid;
         }
      }
      return -1; 
   } 

   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection
    *                   is to be tested.
    * @return  true if this collection contains the specified element,
    *               false otherwise.
    */
   public boolean contains(T element) {
      return locate(element) >= 0;
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      boolean same = false;
      if (s.size() != size()) {
         return false;
      }
      
      for (T a : s) {
         if (this.contains(a)) {
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
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(ArraySet<T> s) {
      boolean same = false;
      if (s.size() != this.size()) {
         return false;
      }
      
      for (int i = size - 1; i >= 0; i--) {
         if (elements[i] == s.elements[i]) {
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
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(Set<T> s) {
      if (this.equals(s) || s.isEmpty()) {
         return this;
      } 
      else if (this.isEmpty()) {
         return s;
      } 
      else {
         Set<T> unionSet = new ArraySet<T>();
         for (T sElement : s) {
            unionSet.add(sElement);
         }
         
         for (T thisElement : this) {
            unionSet.add(thisElement);
         }
         
         return unionSet;
      }
   }

   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(ArraySet<T> s) {
      if (this.equals(s) || s.isEmpty()) {
         return this;
      } 
      else if (this.isEmpty()) {
         return s;
      } 
      else {
         Set<T> unionSet = new ArraySet<T>();
         for (T sElement : s) {
            unionSet.add(sElement);
         }
         
         for (T thisElement : this) {
            unionSet.add(thisElement);
         }
         
         return unionSet;
      }
   }
   
   private ArraySet(T[] a, int start, int end) {
      elements = a;
      start = 0;
      size = end;
   }


   /**
    * Returns a set that is the intersection of this set
    * and the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if (this.equals(s)) {
         return this;
      } 
      else if (this.isEmpty()) {
         return this;
      } 
      else if (s.isEmpty()) {
         return s;
      }
      else {
      
         ArraySet<T> intersectionSet = new ArraySet<T>();
         
         for (T sElement : s) {
            if (this.contains(sElement)) {
               intersectionSet.add(sElement);
            }
         } 
                  
         return intersectionSet; 
      } 
      
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   @SuppressWarnings("unchecked")
   public Set<T> intersection(ArraySet<T> s) {
      if (this.equals(s)) {
         return this;
      } 
      else if (this.isEmpty()) {
         return this;
      } 
      else if (s.isEmpty()) {
         return s;
      }
      int smallest = 0;
      if (this.size() < s.size()) {
         smallest = this.size();
      } 
      else {
         smallest = s.size();
      }
      
      T[] a = (T[]) new Comparable[smallest];
   
      int jellyBeans = 0;
      int kitKat = 0;        
      int j = 0;
      for (int i = 0; i <= smallest; i++) {
         if (kitKat == this.size())
            break;
         if (jellyBeans == s.size())
            break;
         if (this.elements[kitKat] == s.elements[jellyBeans]) {
            a[j++] = elements[kitKat];
            kitKat++;
            jellyBeans++;
         } 
         else if (this.elements[kitKat].compareTo(s.elements[jellyBeans]) < 0) {
            kitKat++;
         } 
         else {
            jellyBeans++;
         }
      }
      
      ArraySet<T> intersectionSet = new ArraySet(a, 0, j);   
                  
      return intersectionSet; 
   
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      Set<T> complementSet = new ArraySet<T>();
      if (this.equals(s)) {
         return complementSet;
      } 
      else if (this.isEmpty() || s.isEmpty()) {
         return this;
      }
      else {
         for (T sElement : s) {
            this.remove(sElement);
         }
         
         for (int i = size() - 1; i >= 0; i--) {
            complementSet.add(this.elements[i]);
         }       
         
         return complementSet;
      }
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   @SuppressWarnings("unchecked")
   public Set<T> complement(ArraySet<T> s) {
      ArraySet<T> complementSet = new ArraySet<T>();
      if (this.equals(s)) {
         return complementSet;
      } 
      else if (this.isEmpty() || s.isEmpty()) {
         return this;
      }
      
      int smallest = this.size();
      
      T[] a = (T[]) new Comparable[smallest];
   
      int jellyBeans = 0;
      int kitKat = 0;        
      int j = 0;
      for (int i = 0; i < smallest; i++) {
         if (kitKat == this.size())
            break;
         if (jellyBeans == s.size())
            break;
         if (this.elements[kitKat] == s.elements[jellyBeans]) {
            kitKat++;
            jellyBeans++;
         } 
         else if (this.elements[kitKat].compareTo(s.elements[jellyBeans]) < 0) {
            a[j++] = elements[kitKat];
            kitKat++;
         } 
         else if (this.elements[kitKat].compareTo(s.elements[jellyBeans]) > 0) {
            a[j++] = elements[kitKat];
            jellyBeans++;
         }
      }
      
      ArraySet<T> complement2Set = new ArraySet(a, 0, j);   
                  
      return complement2Set; 
      
   }


   /**
    * Returns an iterator over the elements in this ArraySet.
    * No specific order can be assumed.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> iterator() {
      return new ArraySetItr();
   }
   
   class ArraySetItr implements Iterator<T> {
      private int value = 0;
      private int count = size();
      private T[] items = elements;
      
      public boolean hasNext() {
         return (value < count);
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         } 
         else {
            return items[value++];
         }
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   /**
    * Returns an iterator over the elements in this ArraySet.
    * The elements are returned in descending order.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> descendingIterator() {
      return new DesArrayItr();
   }
   
   class DesArrayItr implements Iterator<T> {
      private int value = size() - 1;
      private int count = 0;
      private T[] items = elements;
      
      public boolean hasNext() {
         return (value >= count);
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         } 
         else {
            return items[value--];
         }
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   /**
    * Returns an iterator over the members of the power set
    * of this ArraySet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      
      Iterator<Set<T>> itr = 
         new Iterator<Set<T>>() {
            int value = 0;
         
            public boolean hasNext() {
               return (value < (1 << size));
            }
         
            public Set<T> next() {
               if (!hasNext()) {
                  throw new NoSuchElementException();
               } 
               else {
                  Set<T> powSet = new ArraySet<T>();
                  for (int i = 0; i <= size - 1; i++) {
                  
                     if (((value >> 1) & 1) == 1) {
                     
                        powSet.add(elements[i]);
                     }
                  }
                  value++;              
                  return powSet;
               
               }
            }
         
            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      return itr;
   }
}
