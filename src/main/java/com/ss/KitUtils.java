package com.ss;

import java.util.HashMap;
import java.util.List;

/**
 * Adds a quality of life library for commonly used static methods
 *
 * @author Joshua Tyler
 */
public class KitUtils {

  public static final String ANSI_RESET = "\033[0m";
  public static final String ANSI_TEST_NAME = "\033[33;4m";
  public static final String ANSI_TEST_PASS = "\033[32;1m";
  public static final String ANSI_TEST_FAIL = "\033[31;1m";

  public static final String ANSI_HIGH_INTENSITY = "\033[1m";
  public static final String ANSI_LOW_INTENSITY = "\033[2m";

  public static final String ANSI_ITALIC = "\033[3m";
  public static final String ANSI_UNDERLINE = "\033[4m";
  public static final String ANSI_BLINK = "\033[5m";
  public static final String ANSI_RAPID_BLINK = "\033[6m";

  public static final String ANSI_BLACK = "\033[30m";
  public static final String ANSI_RED = "\033[31m";
  public static final String ANSI_GREEN = "\033[32m";
  public static final String ANSI_YELLOW = "\033[33m";
  public static final String ANSI_BLUE = "\033[34m";
  public static final String ANSI_PURPLE = "\033[35m";
  public static final String ANSI_CYAN = "\033[36m";
  public static final String ANSI_WHITE = "\033[37m";

  /**
   * A thread which sleeps for the specific time (ms), and then performs the
   * passed Runnable
   *
   * @param runnable - runnable to be executed after delay
   * @param delay    - int time in milliseconds to delay the runnable
   */
  public static void setTimeout(Runnable runnable, int delay) {
    new Thread(
      () -> {
        try {
          Thread.sleep(delay);
          runnable.run();
        } catch (Exception e) {
          System.err.println(e);
        }
      }
    )
      .start();
  }

  public static String toString(List<String> list) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i) + (i < list.size() - 1 ? ", " : ""));
    }
    return sb.toString();
  }

  public static String toString(Double value) {
    return value + "";
  }

  public static String toString(Double[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      sb.append(array[i].toString() + (i < array.length - 1 ? ", " : ""));
    }
    return sb.toString();
  }

  public static String toString(HashMap<Integer, Integer> map) {
    StringBuilder sb = new StringBuilder();
    for (Integer key : map.keySet()) {
      sb.append(key + ":" + map.get(key) + ", ");
    }
    if(sb.length() > 2) {
      sb.setLength(sb.length()-2);
    }
    return sb.toString();
  }

  public static String toString(Integer[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      sb.append(array[i].toString() + (i < array.length - 1 ? ", " : ""));
    }
    return sb.toString();
  }

  public static String toString(String[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      sb.append(array[i] + (i < array.length - 1 ? ", " : ""));
    }
    return sb.toString();
  }
}
