/**
 * SortingLabClient.java
 * 
 * Provides a simple example client of SortingLab.java.
 *
 * NOTE: The generic type of SortingLab must be bound
 *       to a Comparable type. The sorting methods in
 *       SortingLab use the natural ordering of the
 *       elements in the parameter array.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @version   2016-02-06
 *
 */
public final class SortingLabClient {

   /** Drives execution. */
   public static void main(String[] args) {

      // instantiate the SortingLab class
      // using your group number as the key value
      int key = 28;
      SortingLab<String> sls = new SortingLab<String>(key);

      // run each sort a few times before trying to
      // collect timing data
      String[] as = {"D", "B", "E", "C", "A"};
      for (int i = 0; i < 10; i++) {
         sls.sort1(as);
         sls.sort2(as);
         sls.sort3(as);
         sls.sort4(as);
         sls.sort5(as);
      }
   
      // generate timing data for one sort method using
      // the "doubling strategy" from lecture and lab
      SortingLab<Integer> sli = new SortingLab<Integer>(key);
      int M = 2000000; // max capacity for array //Change it
      int N = 10000;   // initial size of array // Change it
      double start;
      double elapsedTime;
      for (; N < M; N *= 2) {
         Integer[] ai = getIntegerArray(N, Integer.MAX_VALUE);
         start = System.nanoTime();
         sli.sort1(ai);
         elapsedTime = (System.nanoTime() - start) / 1000000000d;
         System.out.print(N + "\t");
         System.out.printf("%4.3f\n", elapsedTime);
      }
   }

   /** return an array of random integer values. */
   private static Integer[] getIntegerArray(int N, int max) {
      Integer[] a = new Integer[N];
      java.util.Random rng = new java.util.Random();
      for (int i = 0; i < N; i++) {
         a[i] = rng.nextInt(max);
      }
      return a;
   }

}
