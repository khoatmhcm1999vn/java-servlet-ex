package com.webmvc.todo.utils;

import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtils {
	private static final SecureRandom RAND = new SecureRandom();
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 256;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	private static byte[] ivBytes;

	public static Optional<String> generateSalt (final int length) {
		if (length < 1) {
			System.err.println("error in generateSalt: length must be > 0");
			return Optional.empty();
	    }

	    byte[] salt = new byte[length];
	    RAND.nextBytes(salt);

	    return Optional.of(Base64.getEncoder().encodeToString(salt));
	}

	public static Optional<String> hashPassword (String password, String salt) {
		char[] chars = password.toCharArray();
		byte[] bytes = salt.getBytes();

		PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

		Arrays.fill(chars, Character.MIN_VALUE);

		try {
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			return Optional.of(Base64.getEncoder().encodeToString(securePassword));

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			System.err.println("Exception encountered in hashPassword()");
			return Optional.empty();

		} finally {
			spec.clearPassword();
		}
	}
	
	public static String encrypt(String password, String salt) {
		try {
	    	char[] chars = password.toCharArray();
			byte[] bytes = salt.getBytes();
	 
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
			
			SecretKey secretKey = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
	 
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			
			AlgorithmParameters params = cipher.getParameters();
	        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
	        
			return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
			
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
	    }
		
	    return null;
	  }
	
	public static String decrypt(String password, String salt) {
	    try {
	    	
	    	char[] chars = password.toCharArray();
			byte[] bytes = salt.getBytes();
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
			
			SecretKey secretKey = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
	 
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));
			
			return new String(cipher.doFinal(Base64.getDecoder().decode(password)));
			
	    } catch (Exception e) {
	    	System.out.println("Error while decrypting: " + e.toString());
	    }
	    
	    return null;
	  }
	
	public static boolean correctPassword (String password, String key, String salt) {
	    Optional<String> optEncrypted = hashPassword(password, salt);
	    System.out.println(optEncrypted.get());
	    if (!optEncrypted.isPresent()) return false;
	    return optEncrypted.get().equals(key);
	}

}
