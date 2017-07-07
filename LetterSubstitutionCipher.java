/*
 * File: LetterSubstitutionCipher.java
 * -----------------------------------
 * This program translates a line of text using a letter-substitution cipher.
 * This code is equivalent to what we wrote in lecture.  All you need to do
 * is add code to check that the key is legal, which means that it is
 * 26 letters long and contains every uppercase letter.
 *
 * // Remember to correct this comment when you write the actual code
 */

import acm.program.*;

public class LetterSubstitutionCipher extends ConsoleProgram {

   public void run() {
      println("Letter-substitution cipher.");
      String key;
      // int offset;
      while (true) {
    	  key = readLine("Enter 26-letter key: ");
    	  if (checkKey(key)) break;
    	  println("That key is illegal.");
      }
      while (true) {
    	  // offset = readInt("Enter rotor offset: ");
          String plaintext = readLine("Plaintext:  ");
          if (plaintext.isEmpty()) break;
          String ciphertext = encrypt(plaintext, key);
          println("Ciphertext: " + ciphertext);
          println("New Key: " + advanceKey(key));
      }
      
      
   }

/**
 * Encrypts a string according to the key.  All letters in the string
 * are converted to uppercase.  Any character that is not a letter is
 * copied to the output unchanged.
 *
 * @param str The string to be encrypted
 * @param key The encryption key
 * @return The encrypted string
 */

   private String encrypt(String str, String key) {
      String result = "";
      str = str.toUpperCase();
      key = key.toUpperCase();
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (Character.isLetter(ch)) {
            ch = key.charAt(ch - 'A');
         }
         result += ch;
      }
      return result;
   }
   
   private boolean checkKey(String s) {
	   s = s.toUpperCase();
	   for (int i=0; i<26; i++) {
		   if (s.indexOf(i+'A') == -1)
			   return false;
	   }
	   return true;
   }
   
   private String advanceKey(String keyIn) {
	   String keyShift = "";
	   String keyOut = "";
	   keyIn = keyIn.toUpperCase();
	   char ch;
	   for (int i=0; i<26; i++) {
		   ch = keyIn.charAt((i + 1) % 26);
		   keyShift += ch;		   
	   }
	   for (int i=0; i<26; i++) {
		   ch = keyShift.charAt(i);
		   int c = ((ch - 'A' + 25) % 26) + 'A';
		   ch = (char)c;
		   keyOut += ch;
	   }
	   
	   return keyOut;
   }

}
