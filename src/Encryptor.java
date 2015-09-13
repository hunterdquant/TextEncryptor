/**
 * @author Hunter Quant <quanthd@clarkson.edu>
 *
 * Provides features for encryptor classes.
 */

public abstract class Encryptor{
	
	private byte encryptionKey;
	private String clearText;
	private String encryptedMessage;
	
	protected abstract void textEncrypt();
	protected abstract void textDecrypt();
	
	/**
	 * Sets the value of the encryption key.
	 * 
	 * @param key - the value to be set as the encryption key.
	 */
	public void setEncryptionKey(byte key) {
		encryptionKey = key;
	}
	
	/**
	 * Sets the clear text message to the passed string.
	 * Sets encryptedMessage to null.
	 * 
	 * @param message - The message to be assigned to clearText.
	 */
	public void setClearText(String message) {
		clearText = message;
		encryptedMessage = null;
	}
	
	/**
	 * Sets the encrypted message to the passed string.
	 * Sets clearText to null.0
	 * 
	 * @param message - the message to be assigned to the encryptedMessage
	 */
	public void setEncryptedMessage(String message) {
		encryptedMessage = message;
		clearText = null;
	}
	
	/**
	 * @return The byte value of encryptionKey
	 */
	public byte getEncryptionKey() {
		return encryptionKey;
	}
	
	/**
	 * @return The string value of encryptedMessage.
	 */
	public String getEncryptedMessage() {
		return encryptedMessage;
	}
	
	/**
	 * @return The string value of clearText.
	 */
	public String getClearText() {
		return clearText;
	}
}
