package com.backend.demo.services;

import com.backend.demo.config.Properties;
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

    public String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(salt);
        return String.valueOf(Hex.encodeHex(salt));
    }

    public String getHashedPassword(String password, String salt) {
        String saltHex = decodeHex(salt);
        byte[] hashedBytes = generateHashedPassword(password, saltHex);
        return String.valueOf(Hex.encodeHex(hashedBytes));
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

    private byte[] generateHashedPassword(String password, String saltHex) {
        String combined = password + saltHex + properties.getPepper();
        byte[] passwordBytes = combined.getBytes();
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(passwordBytes);
            return sha256.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}
