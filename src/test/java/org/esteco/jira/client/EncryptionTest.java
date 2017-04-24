package org.esteco.jira.client;

import org.esteco.jira.encrypt.PropertyEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EncryptionTest {

    private static final String SALT = "saltTest";
    private static final String PLAIN_TEXT = "Plain text";

    private PropertyEncryptor propertyEncryptor;

    @Before
    public void setUp() throws Exception {
        propertyEncryptor = new PropertyEncryptor(new StandardPBEStringEncryptor(), SALT);
    }

    @Test
    public void testEncryption() throws Exception {
        String encrypted = propertyEncryptor.encrypt(PLAIN_TEXT);

        assertThat(PLAIN_TEXT, not(encrypted));
    }

    @Test
    public void testThatEncryptionDecryptinResultsInOriginalString() {
        String encryptedMessage = propertyEncryptor.encrypt(PLAIN_TEXT);
        String decryption = propertyEncryptor.decrypt(encryptedMessage);

        assertEquals(PLAIN_TEXT, decryption);
    }
}
