package com.ss;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestUtils {

  private static AtomicBoolean abortTesting = new AtomicBoolean(false);

  // prettier-ignore
  public static boolean runTest(Runnable test, String testName, Integer iterations) {
    
    // Setup
    Boolean isPassing = true;
    Boolean testComplete = false;

    // Await Test, checking if testing has been aborted
    while (!testComplete && testingStatus()) {
      for (int testCount = 0; testCount < iterations; testCount++) {
        System.out.println("\n" + KitUtils.ANSI_TEST_NAME + "[TEST] " + testName + " (#" + (testCount+1) + ")");
        System.out.println(KitUtils.ANSI_WHITE + "TestNum | Expected | Actual");
        try {
          test.run();
        } catch (Exception | AssertionError e) {
          isPassing = false;
          System.err.println("[ERROR]: " + e);
        } finally {
          if (testCount == iterations - 1) {
            testComplete = true;
          }
        }
      }
    }
    if (testingStatus()) {
      System.out.println(
        isPassing 
        ? KitUtils.ANSI_TEST_PASS + "Passed" 
        : KitUtils.ANSI_TEST_FAIL + "Failed"
      );
      return isPassing;
    }
    return false;
  }

  public static boolean testingStatus() {
    return !abortTesting.get();
  }

  public static void abortTesting() {
    abortTesting.set(true);
  }
}
