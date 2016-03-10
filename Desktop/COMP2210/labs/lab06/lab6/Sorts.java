import java.util.Arrays;
import java.util.Comparator;

/**************************************************************
 *
 * File:    Sorts.java
 * Author:  Dean Hendrix (dh@auburn.edu)
 * Credits: Sedgewick and Wayne
 *
 * Version: 2015-09-11
 *
 * This class contains the following sorting algorithms.
 *   - insertion sort
 *   - selection sort
 *   - recursive mergesort
 *   - recursive quicksort with worst-case exposed
 *   - recursive quicksort with shuffling
 *
 *************************************************************/
public class Sorts<T extends Comparable<? super T>> {


  /** 
   * Exercises all the sorting methods as well as
   * partioning and merging separately.
   */
   public static void main(String[] args) {
   
      Integer[] a = {2, 16, 4, 8, 12, 6, 4, 10, 16, 8, 18, 14};
      Sorts<Integer> st = new Sorts<Integer>();
      
      /** selection sort */
      st.shuffle(a);
      st.selectionSort(a);
      assert st.isSorted(a);
   
      /** insertion sort */
      st.shuffle(a);
      st.insertionSort(a);
      assert st.isSorted(a);
   
      /** quicksort */
      st.shuffle(a);
      st.quicksort(a);
      assert st.isSorted(a);
      
      /** partition */
      a = new Integer[]{2, 16, 4, 8, 12, 6, 4, 10, 16, 8, 18, 14};
      st.partitionOnPivot(a, 0, a.length - 1, 5);
   
      /** merge sort */
      st.shuffle(a);
      st.mergesort(a);
      assert st.isSorted(a);
      
      /** merge */
      a = new Integer[]{4, 8, 10, 14, 16, 18, 2, 4, 6, 8, 12, 16};
      int lo = 0;
      int hi = a.length - 1;
      int mid = (lo + hi) / 2;
      st.merge(a, lo, mid, hi);
      
   }



/********************************************************/
// insertion sort
   public void insertionSort(T[] a) {
      int N = a.length;
      for (int i = 0; i < N; i++)
         for (int j = i; j > 0; j--)
            if (less(a[j], a[j-1]))
               swap(a, j, j-1);
            else
               break;
   }


/********************************************************/
// selection sort
   public void selectionSort(T[] a) {
      int N = a.length;
      for (int i = 0; i < N; i++) {
         int min = i;
         for (int j = i + 1; j < N; j++)
            if (less(a[j], a[min]))
               min = j;
         swap(a, i, min);
      }
   }


/********************************************************/
// recursive mergesort
   private T[] aux;
   public void mergesort(T[] a) {
      aux = Arrays.copyOf(a, a.length);
      Arrays.fill(aux, null);
      msort(a, 0, a.length - 1);
      Arrays.fill(aux, null);
   }


   private void msort(T[] a, int lo, int hi) {
      if (hi <= lo)
         return;
      int mid = lo + (hi - lo) / 2;
      msort(a, lo, mid);
      msort(a, mid + 1, hi);
      merge(a, lo, mid, hi);
   }

   private void merge(T[] a, int lo, int mid, int hi) {
      for (int k = lo; k <= hi; k++)
         aux[k] = a[k];
   
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
         if (i > mid)                    a[k] = aux[j++];
         else if (j > hi)                a[k] = aux[i++];
         else if (less(aux[j], aux[i]))  a[k] = aux[j++];
         else                            a[k] = aux[i++];
      }
   }

  // mergesort using client-supplied ordering via Comparator
   public void mergesort(T[] a, Comparator<T> comp) {
      aux = Arrays.copyOf(a, a.length);
      Arrays.fill(aux, null);
      msort(a, 0, a.length - 1, comp);
   }


   private void msort(T[] a, int lo, int hi, Comparator<T> comp) {
      if (hi <= lo)
         return;
      int mid = lo + (hi - lo) / 2;
      msort(a, lo, mid, comp);
      msort(a, mid + 1, hi, comp);
      merge(a, lo, mid, hi, comp);
   }

   private void merge(T[] a, int lo, int mid, int hi, Comparator<T> comp) {
      for (int k = lo; k <= hi; k++)
         aux[k] = a[k];
   
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
         if (i > mid)                                a[k] = aux[j++];
         else if (j > hi)                            a[k] = aux[i++];
         else if (comp.compare(aux[j], aux[i]) < 0)  a[k] = aux[j++];
         else                                        a[k] = aux[i++];
      }
   }


/********************************************************/
// recursive quicksort with worst-case exposed
   public void quicksort_wc(T[] a) {
      quick(a, 0, a.length -1 );
   }


/********************************************************/
// recursive quicksort with shuffling ("randomized" quicksort)
   public void quicksort(T[] a) {
      shuffle(a);
      quick(a, 0, a.length - 1);
   }


   private void quick(T[] a, int lo, int hi) {
      if (hi <= lo)
         return;
      //int j = partition(a, lo, hi);
      int j = partitionOnPivot(a, lo, hi, lo);
      quick(a, lo, j-1);
      quick(a, j+1, hi);
   }


   private int partition(T[] a, int lo, int hi) {
      int i = lo;
      int j = hi + 1;
      while (true) {
         while (less(a[++i], a[lo]))
            if (i == hi)
               break;
      
         while (less(a[lo], a[--j]))
            if (j == lo)
               break;
      
         if (i >= j)
            break;
         swap(a, i, j);
      }
      swap(a, lo, j);
      return j;
   }

   /**
    * An in-place partition algorithm shown on Wikipedia.
    * Used to generate lecture examples for partitioning.
    */
   private int partitionOnPivot(T[] a, int left, int right, int pivotIndex) {
      T pivot = a[pivotIndex];
      swap(a, pivotIndex, right); // move pivot to the end
      int p = left; // p will become the final index of pivot
      for (int i = left; i < right; i++) {
         if (less(a[i], pivot)) {
            swap(a, i, p);
            p++;
         }
      }
      swap(a, p, right); // move pivot to its correct location
      return p;
   }


  // quicksort with client-supplied ordering via Comparator
   public void quicksort(T[] a, Comparator<T> comp) {
      shuffle(a);
      quick(a, 0, a.length - 1, comp);
   }


   private void quick(T[] a, int lo, int hi, Comparator<T> comp) {
      if (hi <= lo)
         return;
      int j = partition(a, lo, hi, comp);
      quick(a, lo, j-1, comp);
      quick(a, j+1, hi, comp);
   }


   private int partition(T[] a, int lo, int hi, Comparator<T> comp) {
      int i = lo;
      int j = hi + 1;
      while (true) {
         while (comp.compare(a[++i], a[lo]) < 0)
            if (i == hi)
               break;
      
         while (comp.compare(a[lo], a[--j]) < 0)
            if (j == lo)
               break;
      
         if (i >= j)
            break;
         swap(a, i, j);
      }
      swap(a, lo, j);
      return j;
   }




/********************************************************/
  // Knuth shuffle
   public void shuffle(T[] a) {
      int N = a.length;
      java.util.Random rng = new java.util.Random();
      for (int i = N - 1; i > 0; i--) {
         int j = rng.nextInt(i + 1);
         swap(a, i, j);
      }
   }


   private boolean less(T x, T y) {
      return x.compareTo(y) < 0;
   }

   private void swap(T[] a, int i, int j) {
      T temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }


   public boolean isSorted(T[] a) {
      for (int i = 0; i < a.length - 1; i++) {
         if (less(a[i+1], a[i]))
            return false;
      }
      return true;
   }



}
