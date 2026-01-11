import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Typing {
  
  static final ArrayList<String> punctuation = new ArrayList<String>(Arrays.asList("!", "?", ".", ";", ":", ","));
  static final double[] defaultDelays = {0.01, 0.02, 0.035, 0.05, 0.075, 0.09, 0.15};
  static final String[] speeds = {"extremely fast", "very fast", "fast", "normal", "slow", "very slow", "extremely slow"};
  
  public Typing() {
    ;
  }
  
  public static double[] getDefaultDelays() {
    return defaultDelays;
  }
  
  public static String[] getSpeeds() {
    return speeds;
  }
  
  public static void typeString(String str, double delay, boolean grammar, int lineEnd, boolean newLine) throws InterruptedException {
    
    /**
     * Makes output look cooler by typing each character out one-by-one, with a very short delay between each character -- thus simulating a typewriter.  
     * 
     * @return: void
     * @param str: The string to type out. 
     * @param delay: The amount of time, in seconds, to pause after each character. 
     * @param grammar: If true, this will enable natural pauses for punctuation marks. 
     * @param lineEnd: Specifies the maximum number of characters per line. The text automatically moves to the next line if a word were to exceed the max length of a line.  
     * @param newLine: If true, adds a new line after all text has been printed. Otherwise, continues typing on the current line. 
     */
     
    if (delay <= 0.0) {
       System.err.println("Invalid delay argument for: 'public static void typeString(String str, double delay, boolean grammar, int lineEnd, boolean newLine) throws InterruptedException' \ndelay must be positive. ");
       throw new IllegalArgumentException();
    }
    
    if (lineEnd <= 0) {
      System.err.println("Invalid lineEnd argument for: 'public static void typeString(String str, double delay, boolean grammar, int lineEnd, boolean newLine) throws InterruptedException' \nlineEnd must be positive. ");
      throw new IllegalArgumentException();
    }
    
    long milliseconds = (long) (delay * 1000);
    
    int currentLineLen = 0;  
    String[] words = str.split(" ");
    
    for (String word : words) {
      
      // checks word by word 
      // maybe change it to check each character
      currentLineLen += word.length() + 1;
      if (currentLineLen > lineEnd) {
        System.out.println();
        currentLineLen = 0;
        currentLineLen += word.length() + 1;
      }
      
      for (int index = 0; index < word.length(); index++) {
        String character = word.substring(index, index + 1);
        System.out.print(character);
        
        if (grammar && punctuation.contains(character)) {
          
          if (character.equals(",")) {
            Thread.sleep(milliseconds + 500);
          }
          
          else {
            Thread.sleep(milliseconds + 800);
          }
          
        }
        
        Thread.sleep(milliseconds);
      }
      
      System.out.print(" ");
      Thread.sleep(milliseconds);
    }
  
  if (newLine) {
   System.out.println(); 
  }  

  }
  
  public static void typeString(String str, double delay, boolean grammar, int lineEnd) throws InterruptedException {
    typeString(str, delay, grammar, lineEnd, true);
  }
  
  public static void typeString(String str, double delay, boolean grammar) throws InterruptedException {
    typeString(str, delay, grammar, 50);
  }
  
  public static void typeString(String str, String speed, boolean grammar) throws InterruptedException {
    
    Double delay = null;
    boolean validSpeed = false;
    // using parallel lists
    for (int index = 0; index < speeds.length; index++) {
      if (speed.equals(speeds[index])) {
        delay = defaultDelays[index];
        validSpeed = true;
        break;
      }
    }
    
    if (!validSpeed) {
      System.err.println("Invalid typing speed for 'public static void typeString(String str, String speed) throws InterruptedException'. ");
      throw new IllegalArgumentException();
    }
    
    if (delay != null) {
      typeString(str, delay, grammar);
    }

  }
  
  public static void typeString(String str, double delay) throws InterruptedException {
    typeString(str, delay, true);
  }
  
  public static void typeString(String str, String speed) throws InterruptedException {
    typeString(str, speed, true);
  }
  
  public static void typeString(String str) throws InterruptedException {
    typeString(str, "normal");
  }
  
}
