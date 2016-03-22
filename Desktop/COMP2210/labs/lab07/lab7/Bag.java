import java.util.Iterator;

/**
 * Simple collection that implements bag or multiset behavior.
 * Method signatures consistent with java.util.Collection.
 *
 * @parm    <T>  the type of elements in this collection.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2013-09-19
 * @version 2012-09-12
 * @created 2011-09-16
 *
 */

public interface Bag<T> {

/**
 * Ensures the collection contains the specified element.
 * No specific order is maintained, duplicates are allowed.
 *
 * @param  element  The element whose presence is to be ensured.
 * @return true if collection is changed, false otherwise
 *
 */
   boolean add(T element);
    

/**
 * Removes a single instance of the specified element from this collection.
 *
 * @param   element  the element to be removed, if present
 * @return  true if element was removed
 *
 */
   boolean remove(T element);
 
 
/**
 * Searches for specified element in this collection.
 *
 * @param   element  element whose presence in this collection is to be tested
 * @return  true if this collection contains the specified element
 *
 */
   boolean contains(T element);
    
    
/**
 * Returns the current size of this collection.
 *
 * @return  the number of elements in this collection.
 *
 */
   int size();
 

/**
 * Returns true if this collection contains no elements.
 *
 * @return  true if this collection contains no elements.
 *
 */
   boolean isEmpty();


/**
 * Returns an iterator over the elements in this collection.
 *
 * @return  an iterator over the elements in this collection
 *
 */ 
   Iterator<T> iterator();

}