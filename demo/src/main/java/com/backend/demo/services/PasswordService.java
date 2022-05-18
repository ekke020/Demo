package com.backend.demo.services;

import com.backend.demo.config.Properties;
import com.backend.demo.models.User;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


@Service
public class PasswordService {

    private final Properties properties;

    public PasswordService(Properties properties) {
        this.properties = properties;
    }

    public boolean matches(String password, String storedHash) {
        String[] parts = storedHash.split("\\.");
        String saltHex = decodeHex(parts[1]);
        String hash = generateHashedPassword(password, saltHex);
        return parts[0].equals(hash);
    }

    public String hashPassword(String password) {
        String salt = generateSalt();
        String saltHex = decodeHex(salt);
        String hash = generateHashedPassword(password, saltHex);
        return combineHash(hash, salt);
    }

    private String combineHash(String hash, String salt) {
        return hash + "." + salt;
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

}
