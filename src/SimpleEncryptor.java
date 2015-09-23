/**
 * @author Hunter Quant <quanthd@clarkson.edu>
 *
 * Prompts user to encrypt or decrypt an input message.
 */

import java.util.Scanner;

public class SimpleEncryptor extends Encryptor {
	
	/**
	 * Usage:
	 * To run without any arguments. Will prompt the user for input.
	 *  - java SimpleEncryptor
	 * To bypass the prompt to encrypt a message.
	 *  - java SimpleEncryptor -e "<message>"
	 * To Bypass the prompt to decrypt a message.
	 *  - java SimpleEncryptor -d "<message>" <key>
	 * 
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		
		Encryptor encryptor = new SimpleEncryptor();
		try {
			if (args.length == 0) {
				prompt(encryptor);
			// If the chose to bypass the prompt to encrypt.
			} else if (args[0].equals("-e")) {
				encryptor.setClearText(args[1]);
				encryptor.textEncrypt();
				System.out.println("Your encrypted message is: " + encryptor.getEncryptedMessage());
				System.out.println("The encryption key for your encrypted message is: " 
									+ encryptor.getEncryptionKey() + '\n');
			// If they chose to bypass the prompt to decrypt.
			} else if (args[0].equals("-d")) {
				encryptor.setEncryptedMessage(args[1]);
				encryptor.setEncryptionKey(Byte.parseByte(args[2]));
				encryptor.textDecrypt();
				System.out.println("The decrypted message is: " + encryptor.getClearText());
			} else {
				System.out.println("You seem to have entered invalid arguments.");
			}
		// Catch the exception if the user entered too many arguments.
		} catch (ArrayIndexOutOfBoundsException aie) {
			// Tell the user of their mistake and terminate.
			System.out.println("You seem to have entered invalid arguments.");
		// Catch any other exception.
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println("You seem to have entered invalid arguments.");
		}
		System.out.println("Terminating the program.");
	}
	
	/**
	 * Prompts the user for whether they want to encrypt or decrypt.
 	 *
	 * @param encryptor - used to encrypt/decrypt messages.
	 */
	public static void prompt(Encryptor encryptor) {
	
		Scanner in = new Scanner(System.in);
		System.out.print("Would you like to encrypt or decrypt? E/D: ");
		switch (in.nextLine().toLowerCase()) {
			case "e": {
				System.out.print("\nEnter the message you would like to encrypt: ");
				encryptor.setClearText(in.nextLine());
				encryptor.textEncrypt();
				System.out.println("\nYour encrypted message is: " + encryptor.getEncryptedMessage());
				System.out.println("The encryption key for your encrypted message is: " 
									+ encryptor.getEncryptionKey() + '\n');
				break;
			}
			case "d": {
				System.out.print("\nEnter the message you would like to decrypt: ");
				encryptor.setEncryptedMessage(in.nextLine());
				System.out.print("Enter the encryption key for your message: ");
				encryptor.setEncryptionKey(in.nextByte());
				encryptor.textDecrypt();
				System.out.println("\nThe decrypted message is: " + encryptor.getClearText());
				break;
			}
			default: {
				System.out.println("You entered invalid input.");
			}
		}
		in.close();
	}
		
	/**
	 * Default constructor.
	 */
	public SimpleEncryptor() {
		
	}
	
	/**
	 * Encrypts the input message using specified encryption format. 
	 */
	@Override
	public void textEncrypt() {
		
		// Get the sum off all characters.
		char[] msgChars = getClearText().toCharArray();
		int sum = 0;
		for (char c : msgChars) {
			sum += c;
		}
		
		// Sum mod 128 is the encryption key for the message.
		byte key = (byte)(sum % 128);
		// Add 4 to each char in the message.
		for (int i = 0; i < msgChars.length; i++) {
			msgChars[i] += 4;
		}
		
		String encryptedMsg = "";
		// Our encrypted message is each character xor the key.
		for (char c : msgChars) {
			encryptedMsg += (char)(c ^ key);
		}
		setEncryptedMessage(encryptedMsg);
		setEncryptionKey(key);
	}
	
	/**
	 * Encrypts the input message using specified encryption format. 
	 */
	@Override
	public void textDecrypt() {
		
		// Xor each char with the key to yield the clear text char + 4.
		char[] msgChars = getEncryptedMessage().toCharArray();
		for (int i = 0; i < msgChars.length; i++) {
			msgChars[i] = (char)(msgChars[i] ^ getEncryptionKey());
		}
		
		// Subtract 4 from each character and concatenate them to get the clear text message.
		String decrypted = "";
		for (char c : msgChars) {
			decrypted += (char)(c - 4);
		}
		setClearText(decrypted);
	}
}
