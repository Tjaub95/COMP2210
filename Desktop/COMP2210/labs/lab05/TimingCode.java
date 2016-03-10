/**
 * TimingCode.java
 * Illustrates basic approach to measuring a method's
 * running time.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2015-09-01
 *
 */
public class TimingCode {

   private static final double SECONDS = 1_000_000_000d;
   private static final int NUM_RUNS = 10;

  /** Drives execution. */
   public static void main(String[] args) {
      long start;
      long elapsedTime;
      double avgTime = 0d;
      for (int i = 0; i < NUM_RUNS; i++) {
         start = System.nanoTime();
         foo();
         elapsedTime = System.nanoTime() - start;
         avgTime = avgTime +  elapsedTime;
      }
      avgTime = avgTime / NUM_RUNS;
      avgTime = avgTime / SECONDS;
      System.out.printf("%s%4.3f%s\n", "Method foo's running time: ", avgTime, " seconds");
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
