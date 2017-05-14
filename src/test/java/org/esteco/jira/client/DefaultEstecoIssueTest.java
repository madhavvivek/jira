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

public class DefaultEstecoIssueTest {

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
        DefaultEstecoIssue defaultEstecoIssue = new DefaultEstecoIssue(issue);

        assertEquals("MF-10241", defaultEstecoIssue.getKey());
        assertEquals("Story", defaultEstecoIssue.getIssueType().getName());
        assertEquals("Major", defaultEstecoIssue.getPriority().getName());
        assertEquals("Vivek Madhav", defaultEstecoIssue.getAssignee().getDisplayName());
        assertEquals("Marco Carriglio", defaultEstecoIssue.getReporter().getDisplayName());
        assertEquals("Integration Nodes", defaultEstecoIssue.getComponents().get(0).getName());
        assertNotNull(defaultEstecoIssue.getSummary());
        assertNotNull(defaultEstecoIssue.getIssueTypeIconUrl());
        assertNotNull(defaultEstecoIssue.getPriorityIconUrl());
    }
}
