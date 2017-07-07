/*
 * File: EnigmaModel.java
 * ----------------------
 * This is the starter file for a class that models the German Enigma
 * machine from World War II.
 *
 * // Remember to correct this comment when you implement the class
 */

public class EnigmaModel {

/**
 * Creates a new object that models the operation of an Enigma machine.
 * By default, the rotor order is initialized to 123, which indicates
 * that stock rotors 1, 2, and 3 are used for the slow, medium, and
 * fast rotor positions, respectively.  The rotor setting is initialized
 * to "AAA".
 */

   public EnigmaModel() {
      setRotorOrder(123);
      setRotorSetting("AAA");
   }
   
/**
 * Create a new Enigma model object by specifying the rotor order
 * and rotor setting configurations.   
 * @param Ro a three-digit integer specifying which rotors to use
 * @param Rs a three-character String specifying the starting rotor positions
 */
   public EnigmaModel(int Ro, String Rs) {
	   setRotorOrder(Ro);
	   setRotorSetting(Rs);
   }

/**
 * Sets the rotor order for the Enigma machine.  The rotor order is
 * specified as a three-digit integer giving the numbers of the stock
 * rotors to use.  For example, calling setRotorOrder(513) uses stock
 * rotor 5 as the slow rotor, stock rotor 1 as the medium rotor, and
 * stock rotor 3 as the fast rotor.  This method returns true if the
 * argument specifies a legal rotor order (three digits in the range
 * 1 to 5 with no duplication) and false otherwise.
 *
 * @param order A three-digit integer specifying the rotor order
 * @return A Boolean value indicating whether the rotor order is legal
 */

   public boolean setRotorOrder(int order) {
	   int one, two, three;
	   one = order / 100;
	   two = (order - one * 100) / 10;
	   three = (order - (order / 10) * 10);
	   if (one == two || two == three || one == three) {
		   return false;
	   } else {
		   rotorOrder = order;
		   // println("New Rotor Order is: "+ order);
		   slowRotor = selectRotor(one);
		   medRotor = selectRotor(two);
		   fastRotor = selectRotor(three);
		   return true;
	   }
	}
 
/**
* Gets the current rotor order for the Enigma model.
* @return A three-digit integer.
*/
   public int getRotorOrder() {
	   return rotorOrder;
   }

/**
 * Establishes the rotor setting for the Enigma machine.  A legal rotor
 * setting must be a string of three uppercase letters.  This method
 * returns true if the argument is a legal setting and false otherwise.
 *
 * @param str The rotor settings
 * @return A Boolean value indicating whether the rotor setting is legal
 */

   public boolean setRotorSetting(String setting) {
	   char ch;
	   rotorSetting = "";
	   if (setting.length() != 3) return false; // setting must be three characters long
	   for (int i=0; i<3; i++) {
		   ch = setting.charAt(i);		   
		   if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
			   setRotorPosition(i, ch);
			   rotorSetting += setting.charAt(i);
		   } else return false;
	   }
	   // println("New Rotor Setting is: " + rotorSetting);
      return true;   
   }

/**
 * Gets the current rotor setting for the Enigma machine.
 *
 * @return The current rotor setting
 */
   public String getRotorSetting() {
      return rotorSetting;
   }

/**
 * Encrypts a string by passing each letter through the various rotors
 * of the Enigma machine.  All letters in the string are converted to
 * uppercase, and the rotors of the Enigma machine are advanced before
 * translating the letter.  If a character in the plaintext string is
 * not a letter, the rotors do not advance and the character is simply
 * copied to the output unchanged.
 *
 * @param plaintext The input plaintext string
 * @return The output ciphertext string
 */

   public String encrypt(String plaintext) {
	   String result = "";	// resulting cipher text
	   plaintext = plaintext.toUpperCase();
	   char l0 = 0;		// each letter to be encrypted/decrypted
	   for (int i=0; i < plaintext.length(); i++) {
		   if (Character.isLetter(plaintext.charAt(i))) {
			   fastRotor = advanceRotor(fastRotor);
			   rotor3++;
			   if (rotor3 == 0) {
				   medRotor = advanceRotor(medRotor);
				   rotor2++;
				   if (rotor2 == 0) {
					   slowRotor = advanceRotor(slowRotor);
					   rotor1++;
				   }
			   }
			   l0 = plaintext.charAt(i);
			   char l1 = encryptThru(l0, fastRotor);
			   System.out.println(l0+"->"+l1);
			   char l2 = encryptThru(l1, medRotor);
			   System.out.println(l1+"->"+l2);
			   char l3 = encryptThru(l2, slowRotor);
			   System.out.println(l2+"->"+l3);
			   char l4 = encryptThru(l3, REFLECTOR);
			   System.out.println(l3+"->"+l4);
			   char l5 = encryptThru(l4, invertKey(slowRotor));
			   System.out.println(l4+"->"+l5);
			   char l6 = encryptThru(l5, invertKey(medRotor));
			   System.out.println(l5+"->"+l6);
			   char l7 = encryptThru(l6, invertKey(fastRotor));
			   System.out.println(l6+"->"+l7);
			   result += l7;
		   } else result += plaintext.charAt(i);
	   }
      return result;   // Replace this line with the actual implementation
   }

/* Private methods */

   private String advanceRotor(String keyIn) {
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
   
   private void setRotorPosition(int r, char ch) {
	   switch(r) {
	   case 1:
		   rotor1 = (ch - 'A');
	   case 2:
		   rotor2 = (ch - 'A');
	   case 3:
		   rotor3 = (ch - 'A');
	   }
   }
   
   private String selectRotor(int i) {
	   switch (i) {
	   case 1:
		   return STOCK_ROTOR_1;
	   case 2:
		   return STOCK_ROTOR_2;
	   case 3:
		   return STOCK_ROTOR_3;
	   case 4:
		   return STOCK_ROTOR_4;
	   case 5:
		   return STOCK_ROTOR_5;
	   default:
		   return "";
	   }
   }
   
   private char encryptThru(char ch, String key) {
	   System.out.println(ALPHABET);
	   System.out.println(PASS_THRU);
      System.out.println(key);	      
         if (Character.isLetter(ch)) {
            ch = key.charAt(ch - 'A');
         }
      return ch;
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
* @return result The key that translates in the opposite direction
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

/* Private instance variables */

   private String rotorSetting;
   private int rotorOrder;
   private int rotor1, rotor2, rotor3;
   private String fastRotor, medRotor, slowRotor;

/* Private constants */

/**
 * The German Enigma machines were supplied with a stock of five rotors,
 * although the required part of the assignment uses only the first three.
 * Each rotor is represented by a string of 26 letters that shows how the
 * letters in the alphabet are mapped to new letters as the current in the
 * Enigma machine flows across the rotor from right to left.  For example,
 * the STOCK_ROTOR_1 string ("EKMFLGDQVZNTOWYHXUSPAIBRCJ") indicates the
 * following mapping when it is in its initial position:
 *
 *    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *    | | | | | | | | | | | | | | | | | | | | | | | | | |
 *    E K M F L G D Q V Z N T O W Y H X U S P A I B R C J
 *
 * As the rotor advances, the permutation shifts by one position.  For
 * example, after this rotor advances, the bottom line of this transformation
 * is shifted one position to the left, with the E wrapping around to the
 * other side, as follows:
 *
 *    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *    | | | | | | | | | | | | | | | | | | | | | | | | | |
 *    K M F L G D Q V Z N T O W Y H X U S P A I B R C J E
 *
 * Whenever the rotor setting advances past Z, the next rotor advances
 * one position.
 */

   private static final String STOCK_ROTOR_1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
   private static final String STOCK_ROTOR_2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
   private static final String STOCK_ROTOR_3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
   private static final String STOCK_ROTOR_4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
   private static final String STOCK_ROTOR_5 = "VZBRGITYUPSDNHLXAWMJQOFECK";
   private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYX";
   private static final String PASS_THRU = "||||||||||||||||||||||||||";

/*
 * The Enigma reflector is also a 26-character string that works just like
 * the rotors except for the fact that it stays in one position and never
 * advances.  The reflector setting of "IXUHFEZDAOMTKQJWNSRLCYPBVG"
 * therefore means that a signal coming into the reflector on the wire
 * shown at the top of the following translation table will go out again
 * on the letter at the bottom:
 *
 *    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *    | | | | | | | | | | | | | | | | | | | | | | | | | |
 *    I X U H F E Z D A O M T K Q J W N S R L C Y P B V G
 *
 * Note that the reflector is symmetric.  If A is transformed to I, then
 * I is transformed to A.
 */

   private static final String REFLECTOR = "IXUHFEZDAOMTKQJWNSRLCYPBVG";

}
