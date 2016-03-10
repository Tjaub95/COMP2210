/**
 * TimeComplexity.java
 * Illustrates basic approach to characterizing a method's
 * time complexity.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2015-09-01
 *
 */
public class TimeComplexity {

   private static final double SECONDS = 1_000_000_000d;
   private static final int NUM_RUNS = 10;

  /** Drives execution. */
   public static void main(String[] args) {
      long start;
      long elapsedTime;
      double avgTime = 0d;
      int N = 8;
      System.out.printf("%4s%8s\n", "N", "Time");
      for (int i = 0; i < NUM_RUNS; i++) {
         start = System.nanoTime();
         methodToTime(N);
         elapsedTime = System.nanoTime() - start;
         System.out.printf("%4d %8.3f\n", N, (elapsedTime / SECONDS));
         N *= 2;
      }
   }

  /** Provides enough work to measure computation time. */
   private static void methodToTime(int N) {
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            foo();
         }
      }
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            foo();
         }
      }
   }

  /** Something that will hopefully take time >= 0.001 seconds. */
   private static void foo() {
      for (int i = 0; i < 100_000; i++) {
         String s1 = "War";
         String s2 = "Eagle";
         String s3 = s1 + s2;
         s1 = null;
         s2 = null;
         s3 = null;
      }
   }

}
