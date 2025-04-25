package xyz.jdynb.dymovies.common.utils;

import org.springframework.stereotype.Component;
import xyz.jdynb.dymovies.common.config.AESConfigProperties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class AesEncryption {

    private static final String ALGORITHM = "AES";
    private final String TRANSFORMATION;
    private final int KEY_SIZE; // AES key size in bits
    private final int ITERATIONS; // Number of iterations for PBKDF2
    private final String SALT; // A unique salt value for PBKDF2

    private final String IV;

    private final String PASSWORD;

    public AesEncryption(AESConfigProperties aesConfigProperties) {
        TRANSFORMATION = aesConfigProperties.getTransformation();
        KEY_SIZE = aesConfigProperties.getKeySize();
        ITERATIONS = aesConfigProperties.getIterations();
        SALT = aesConfigProperties.getSalt();
        IV = aesConfigProperties.getIv();
        PASSWORD = aesConfigProperties.getPassword();
    }

    public String encrypt(String plaintext) {
        return encrypt(PASSWORD, plaintext);
    }

    public String decrypt(String plaintext) {
        return decrypt(PASSWORD, plaintext);
    }

    public String encrypt(String password, String plaintext) {
        try {
            // Derive a key from the password
            SecretKey secretKey = deriveKey(password);

            // Initialize cipher for encryption
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes()));

            // Encrypt the plaintext
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            // Return the Base64 encoded encrypted bytes
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception ignored) {
        }
        return null;
    }

    public String decrypt(String password, String encryptedText) {
        try {
            // Derive a key from the password
            SecretKey secretKey = deriveKey(password);

            // Decode the encrypted text
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Initialize cipher for decryption
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes()));

            // Decrypt the encrypted bytes
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Return the decrypted plaintext
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }
        return null;
    }

    private SecretKey deriveKey(String password) throws Exception {
        // Create a PBEKeySpec with the password, salt, iteration count, and key length
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), SALT.getBytes(StandardCharsets.UTF_8), ITERATIONS,
                KEY_SIZE);

        // Create a SecretKeyFactory and generate a SecretKey
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey secretKey = factory.generateSecret(keySpec);

        // Convert the SecretKey to a SecretKeySpec for AES
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }

}