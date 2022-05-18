package com.backend.demo.services;

import com.backend.demo.config.Properties;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;


@Service
public class PasswordService {

    private final Properties properties;

    public PasswordService(Properties properties) {
        this.properties = properties;
    }

    public boolean matches(String password, String storedHash) {
        PasswordHandler.unObfuscate(storedHash);
        String saltHex = decodeHex(PasswordHandler.storedSalt);
        String hash = generateHashedPassword(password, saltHex);
        return PasswordHandler.storedHash.equals(hash);
    }

    public String hashPassword(String password) {
        String salt = generateSalt();
        String saltHex = decodeHex(salt);
        String hash = generateHashedPassword(password, saltHex);
        return PasswordHandler.obfuscate(hash + salt);
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(salt);
        return String.valueOf(Hex.encodeHex(salt));
    }

    private String decodeHex(String salt) {
        try {
            byte[] bytes = Hex.decodeHex(salt.toCharArray());
            return new String(bytes, StandardCharsets.ISO_8859_1);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateHashedPassword(String password, String saltHex) {
        String combined = password + saltHex + properties.getPepper();
        byte[] passwordBytes = combined.getBytes();
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(passwordBytes);
            byte[] hashedBytes = sha256.digest();
            return String.valueOf(Hex.encodeHex(hashedBytes));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private final static class PasswordHandler {

        private static String storedHash;
        private static String storedSalt;

        private static String obfuscate(String hash) {
            Random rand = new Random();
            int loops = rand.nextInt(100);
            int targetIndex = rand.nextInt(hash.length());

            for (int i = 0; i < loops; i++) {
                for (int j = 0; j < hash.length(); j++) {
                    hash = switchChars(hash, j, targetIndex);
                }
            }
            return targetIndex + " " + loops + "|" + hash;
        }

        private static void unObfuscate(String hash) {
            String[] parts = hash.split("\\|");
            int[] numbers = Arrays.stream(parts[0].split(" ")).mapToInt(Integer::valueOf).toArray();
            int targetIndex = numbers[0];
            int loops = numbers[1];
            hash = parts[1];

            for (int i = 0; i < loops; i++) {
                for (int j = hash.length() - 1; j >= 0; j--) {
                    hash = switchChars(hash, j, targetIndex);
                }
            }
            storedHash = hash.substring(0, 64);
            storedSalt = hash.substring(64);
        }

        private static String switchChars(String hash, int start, int end) {
            char[] chars = hash.toCharArray();
            int nextPos = calculatePosition(start + end, chars.length - 1);
            char moving = chars[start];
            char target = chars[nextPos];
            chars[start] = target;
            chars[nextPos] = moving;
            return String.valueOf(chars);
        }

        private static int calculatePosition(int targetIndex, int totalIndex) {
            return targetIndex > totalIndex
                    ? targetIndex - totalIndex
                    : targetIndex;
        }
    }
}
