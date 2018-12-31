package br.com.stelo.batch.helper;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class StringCrypto {

	private final static String alg = "AES";
	private final static String cI = "AES/CBC/PKCS5Padding";
	private final static String key = "HUJE5LXCH8SLEIS1";
	private final static String iv = "0123456789ABCDEF";

	public static String encrypt(String cleartext) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(cI);
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
			byte[] encrypted = cipher.doFinal(cleartext.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String decrypt(String encrypted) {
		try {
			Cipher cipher = Cipher.getInstance(cI);
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			byte[] enc = Base64.getDecoder().decode(encrypted);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
			byte[] decrypted = cipher.doFinal(enc);
			return new String(decrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
