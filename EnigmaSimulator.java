/*
 * File: EnigmaSimulator.java
 * --------------------------
 * This is the starter file for an Enigma simulator that uses the console to
 * enter the rotor order, rotor settings, and plaintext.
 *
 * // Remember to correct this comment when you write the actual program
 */

import acm.program.*;

public class EnigmaSimulator extends ConsoleProgram {

   public void run() {
      EnigmaModel e = new EnigmaModel();
      String setting = e.getRotorSetting();
      int order = e.getRotorOrder();
      println("Default enigma rotor setting is: " + setting);
      println("Default Rotor order is: " + order);
      
      boolean didSetOrder, didSetSetting;
      while (true) {
    	  order = readInt("Enter new rotor order: ");
    	  didSetOrder = e.setRotorOrder(order);
    	  if (didSetOrder) {
    		  println("New rotor order is: "+ e.getRotorOrder());
    		  break;
    	  }
      }
      while (true) {
    	  setting = readLine("Enter new rotor setting: ");
    	  didSetSetting = e.setRotorSetting(setting);
    	  if (didSetSetting) {
    		  println("New rotor setting is: " + e.getRotorSetting());
    		  break;
    	  }
      } 
      String text = readLine("Enter text to encode: ");
      String result = e.encrypt(text);
      println("The encoded text:");
      println(result);
      

   }

}
