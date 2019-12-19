package com.keeplogin;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptUtil {
	private static final String AES = "AES";
	private static final String AES_CBC = "AES/CBC/PKCS5Padding";
	private static final String HMACSHA1 = "HmacSHA1";
	private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; // RFC2401
	private static final int DEFAULT_AES_KEYSIZE = 128;
	private static final int DEFAULT_IVSIZE = 16;
//	public static void main(String[] args) throws Exception {
//		byte[] ivBase64 = Base64.decodeBase64("ac80PYOAkewM6OrceBSc7g==");
//		byte[] key128Base64 = Base64.decodeBase64("Ege8HbbmsSz0RlKR/Ynjsw==");
//		byte[] enc = encryptAES("你123b".getBytes(), key128Base64);
//		byte[] src = decryptAES(enc, key128Base64);
//		System.out.println(Base64.encodeBase64String(enc));
//		System.out.println(new String(src));
//	}

	public static byte[] ivBase64=Base64.decodeBase64("zyWBt7ss9Wm1DmQ0AmOgqA==");
	public static byte[] key128Base64=Base64.decodeBase64("qOLmGqNk2zqOFPHASLRRMg==");

	public static class AESKeyGen {
		private static SecureRandom random = new SecureRandom();

		public static void main(String[] args) {
			System.out.println("ivBase64=Base64.decodeBase64(\"" + new String(Base64.encodeBase64(generateIV())) + "\");");
			System.out.println("key128Base64=Base64.decodeBase64(\"" + new String(Base64.encodeBase64(generateAesKey())) + "\");");
		}

		public static byte[] generateAesKey() {
			return generateAesKey(DEFAULT_AES_KEYSIZE);
		}

		public static byte[] generateAesKey(int keysize) {
			try {
				KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
				keyGenerator.init(keysize);
				SecretKey secretKey = keyGenerator.generateKey();
				return secretKey.getEncoded();
			} catch (GeneralSecurityException e) {
				throw new RuntimeException(e);
			}
		}

		public static byte[] generateIV() {
			byte[] bytes = new byte[DEFAULT_IVSIZE];
			random.nextBytes(bytes);
			return bytes;
		}
	}

	// -- AES funciton --//
	public static byte[] encryptAES(byte[] input, byte[] key) {
		return aes(input, key, Cipher.ENCRYPT_MODE);
	}

	public static byte[] encryptAES(byte[] input, byte[] key, byte[] iv) {
		return aes(input, key, iv, Cipher.ENCRYPT_MODE);
	}
	
	public static byte[] decryptAES(byte[] input, byte[] key) {
		return aes(input, key, Cipher.DECRYPT_MODE);
	}

	public static String decryptAESString(byte[] input, byte[] key) {
		byte[] decryptResult = aes(input, key, Cipher.DECRYPT_MODE);
		return new String(decryptResult);
	}

	public static String decryptAES(byte[] input, byte[] key, byte[] iv) {
		byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
		return new String(decryptResult);
	}

	public static byte[] aes(byte[] input, byte[] key, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, AES);
			Cipher cipher = Cipher.getInstance(AES);
			cipher.init(mode, secretKey);
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, AES);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(AES_CBC);
			cipher.init(mode, secretKey, ivSpec);
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}
}
