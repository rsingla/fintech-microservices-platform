package com.techcode.encryption;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Encryption {

	final String keyHex = "00000000000008000000000000896843";

	private SecretKey key;
	private Cipher cipher;
	
	private Encryption() throws NoSuchAlgorithmException, NoSuchPaddingException {
		key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "AES");
		 cipher  = Cipher.getInstance("AES/ECB/NoPadding");;
	}
	
	public String encryt(String args) throws Exception {

		final String plaintextHex = toHex(args);

		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(plaintextHex.getBytes());
		
		
		
		return asHex(encrypted);
		
		

	}

	public static void main(String[] args) throws Exception{
		Encryption encryption = new Encryption();
		String encryptedKey = encryption.encryt("rajeev12");
		
		String encryptedKey2 = encryption.encryt("rajeev12");
		
		System.out.println("encrypted string: " + encryptedKey);
		System.out.println(encryptedKey2);
		
		if(encryptedKey.equals(encryptedKey2)) {
			System.out.println("HELLO");
		}
		
		//System.out.println(encryption.decrypt(encryptedKey)); 
	}

	/*private String decrypt(String encrypted) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		
		String encryptedFromHex = fromHex(encrypted);
 		
		byte[] encrypted2 = encryptedFromHex.getBytes();

		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] original = cipher.doFinal(encrypted2);
		String originalString = new String(original);

		return fromHex(originalString);

	}*/

	private static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	private static String toHex(String arg) {
		return String.format("%032x", new BigInteger(arg.getBytes()));
	}
	
	

	private static String fromHex(String arg) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < arg.length() - 1; i += 2) {
			// grab the hex in pairs
			String output = arg.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}

		return sb.toString();
	}

}