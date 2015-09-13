/**
 * @author Hunter Quant <quanthd@clarkson.edu>
 *
 * Provides features for encryptor classes.
 */

public abstract class Encryptor{

	/**
	 * Encrypts the input message using specified encryption format. 
	 * 
	 * @param message - A user input message to be encrypted.
	 * @return A string array containing the encrypted message and the key.
	 */
	public String[] textEncrypt(String message) {
		
		// Get the sum off all characters.
		char[] msgChars = message.toCharArray();
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
		// Our encrypted message is each character xor the key.
		String encryptedMsg = "";
		for (char c : msgChars) {
			encryptedMsg += (char)(c ^ key);
		}
		// Return the encrypted message and the encryption key.
		String[] msgAndKey = {encryptedMsg, "" + key};
		return msgAndKey;
	}
	
	/**
	 * 
	 * @param message - the encrypted message to be decrypted.
	 * @param key - the encryption key used to decrypt the message.
	 * @return The clear text version of the encrypted message.
	 */
	public String textDecrypt (String message, byte key) {
		return "";
	}
}
