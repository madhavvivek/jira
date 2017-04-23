package org.esteco.jira.client;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EstecoIssueTest {

    private BasicCredentials credentials;
    private JiraClient jiraClient;

    @Before
    public void setUp() throws Exception {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("VivekMadhav");
        Properties props = new EncryptableProperties(encryptor);
        InputStream inputStream = getClass().getResourceAsStream("../connection.properties");
        props.load(inputStream);
        String userName = props.getProperty("jira.username");
        String password = props.getProperty("jira.password");
        credentials = new BasicCredentials(userName, password);
        jiraClient = new JiraClient("https://jira.esteco.com/", credentials);
    }

    @Test
    public void testIssueParameters() throws Exception {
        Issue issue = jiraClient.getIssue("MF-10241");
        EstecoIssue estecoIssue = new EstecoIssue(issue);

        assertEquals("MF-10241", estecoIssue.getKey());
        assertEquals("Story", estecoIssue.getIssueType().getName());
        assertEquals("Major", estecoIssue.getPriority().getName());
        assertEquals("Vivek Madhav", estecoIssue.getAssignee().getDisplayName());
        assertEquals("Marco Carriglio", estecoIssue.getReporter().getDisplayName());
        assertEquals("Integration Nodes", estecoIssue.getComponents().get(0).getName());
        assertNotNull(estecoIssue.getSummary());
        assertNotNull(estecoIssue.getIssueTypeIconUrl());
        assertNotNull(estecoIssue.getPriorityIconUrl());
    }
}
