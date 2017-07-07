/*
 * File: InvertKey.java
 * --------------------
 * This is the starter file for a program that tests the invertKey method.
 *
 * // Remember to correct this comment when you write the actual program
 */

import acm.program.*;

public class InvertKey extends ConsoleProgram {

   public void run() {
      String key = readLine("Enter 26-letter key: ");
      println("Key:\t" + key);
      String invKey = invertKey(key);
      println("Inverted Key:\t" + invKey);
      String origKey = invertKey(invKey);
      println("Original Key:\t" + origKey);
   }

/**
 * Inverts a key for a letter-substitution cipher, where a key is a
 * 26-letter string that shows how each letter in the alphabet is
 * translated into the encrypted message.  For example, if the key is
 * "LZDRXPEAJYBQWFVIHCTGNOMKSU", that means that 'A' (the first letter
 * in the alphabet) translates to 'L' (the first letter in the key),
 * 'B' translates to 'Z', 'C' translates to 'D', and so on.  The inverse
 * of a key is a 26-letter that translates in the opposite direction.
 * As an example, the inverse of "LZDRXPEAJYBQWFVIHCTGNOMKSU" is
 * "HKRCGNTQPIXAWUVFLDYSZOMEJB".
 *
 * @param key The original key
 * @return The key that translates in the opposite direction
 */

   private String invertKey(String key) {
	   String result = "";
	   char ch;
	   for (int i=0; i<key.length(); i++) {
		   ch = (char) ('A' + key.indexOf(i+'A'));
		   result = result + ch;
	   }
      return result;
   }

}
