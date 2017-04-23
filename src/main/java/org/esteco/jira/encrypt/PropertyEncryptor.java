package org.esteco.jira.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PropertyEncryptor {

    private final StandardPBEStringEncryptor encryptor;

    private PropertyEncryptor() {
        encryptor = null;
    }

    public PropertyEncryptor(StandardPBEStringEncryptor encryptor, final String password) {
        this.encryptor = encryptor;
        this.encryptor.setPassword(password);
    }

    public String encrypt(final String message) {
        return encryptor.encrypt(message);
    }

    public String decrypt(final String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage);
    }

    private void usage() {
        System.err.println("usage: " + this.getClass().getName() + " <salt-password> <plain-text-message>");
    }

    public static void main(String[] args) {
        try {
            PropertyEncryptor propertyEncryptor = new PropertyEncryptor(new StandardPBEStringEncryptor(), args[0]);
            System.out.println("Encrypted message: ENC(" + propertyEncryptor.encrypt(args[1]) + ")");
        } catch (Exception e) {
            new PropertyEncryptor().usage();
        }
    }
}
