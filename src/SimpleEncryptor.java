/**
 * @author Hunter Quant <quanthd@clarkson.edu>
 *
 * Prompts user to encrypt or decrypt an input message.
 */

import java.util.Scanner;

public class SimpleEncryptor extends Encryptor {
	
	/**
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		
		SimpleEncryptor encryptor = new SimpleEncryptor();;
		try {
			// If the chose to bypass the prompt to encrypt.
			if (args.length != 0 && args[0].equals("-e")) {
				encryptor.setClearText(args[1]);
				encryptor.textEncrypt();
			// If they chose to bypass the prompt to decrypt.
			} else if (args.length != 0 && args[0].equals("-d")) {
				encryptor.setEncryptedMessage(args[1]);
				encryptor.setEncryptionKey(Byte.parseByte(args[2]));
				encryptor.textDecrypt();
			}
		// Catch the exception if the user entered too many arguments.
		} catch (ArrayIndexOutOfBoundsException aie) {
			// Tell the user of their mistake and prompt them for input.
			System.out.println("You seem to have entered invalid arguments.\n");
		// Catch any other exception.
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		encryptor.prompt();
	}
		
	/**
	 * Default constructor.
	 */
	public SimpleEncryptor() {
		
	}
	
	/**
	 * @param message - The message to be encrypted.
	 */
	public SimpleEncryptor(String message) {
		setClearText(message);
	}
	
	/**
	 * 
	 * @param message - The message to be decrypted.
 	 * @param key - the key used for decryption.
	 */
	public SimpleEncryptor(String message, byte key) {
		setEncryptedMessage(message);
		setEncryptionKey(key);
	}
	
	/**
	 * Encrypts the input message using specified encryption format. 
	 * 
	 * @param message - A user input message to be encrypted.
	 */
	protected void textEncrypt() {
		
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
		System.out.println("Your encrypted message is: " + getEncryptedMessage());
		System.out.println("The encryption key for your encrypted message is: " + getEncryptionKey() + '\n');
	}
	
	/**
	 * @param message - the encrypted message to be decrypted.
	 */
	protected void textDecrypt() {
		
		char[] msgChars = getEncryptedMessage().toCharArray();
		for (int i = 0; i < msgChars.length; i++) {
			msgChars[i] = (char)(msgChars[i] ^ getEncryptionKey());
		}
		String decrypted = "";
		for (char c : msgChars) {
			decrypted += (char)(c - 4);
		}
		setClearText(decrypted);
		System.out.println("Your decrypted message is: " + getClearText());
	}
	
	public void prompt() {
		
		Scanner in = new Scanner(System.in);
		System.out.print("\nWould you like to encrypt or decrypt a message? Y/N: ");
		while (in.hasNext()) {
			
			String input = in.nextLine().toLowerCase();
			if (input.equals("y") || input.equals("yes")) {
				System.out.print("\nWhich action would you like to preform? E/D: ");
				input = in.nextLine().toLowerCase();
				if (input.equals("e") || input.equals("encrypt")) {
					System.out.println("\nEnter the message you would like to encrypt: ");
					input = in.nextLine();
					setClearText(input);
					textEncrypt();
				} else if (input.equals("d") || input.equals("decrypt")) {
					System.out.println("\nEnter the message you would like to decrypt: \n");
					setEncryptedMessage(in.nextLine());
					try {
						System.out.println("\nEnter the encryption key for this message: ");
				    	setEncryptionKey(Byte.parseByte(in.next()));
						textDecrypt();
					} catch (NumberFormatException nfe) {
						System.out.println("You've entered an invalid encryption key.");
					}
				} else {
					System.out.println("\nYou seem to have entered invalid input.");
				}
			} else if (input.equals("n") || input.equals("no")) {
				System.out.println("\nThanks for using the Cryptomatic 2000!");
				break;
			} else {
				System.out.println("\nYou seem to have entered invalid input.\n");
			}
			System.out.print("\nWould you like to encrypt or decrypt a message? Y/N: ");
		}
		in.close();
	}
}
