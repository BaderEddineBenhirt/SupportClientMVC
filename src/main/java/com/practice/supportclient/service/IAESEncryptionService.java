package com.practice.supportclient.service;

import javax.crypto.SecretKey;

public interface IAESEncryptionService {
    public static SecretKey generateKey() {
        return null;
    }

    // Encrypt data.
    public static String encrypt(String data, SecretKey key) {
        return null;
    }

    // Decrypt data.
    public static String decrypt(String encryptedData, SecretKey key) {
        return null;
    }
}
