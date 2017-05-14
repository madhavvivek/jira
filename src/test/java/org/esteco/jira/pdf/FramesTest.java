package org.esteco.jira.pdf;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import org.esteco.jira.client.DefaultEstecoIssue;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class FramesTest {

    @Test
    public void testStoryIcon() throws Exception {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("Salt@2010@#$%^&*!012");
        Properties props = new EncryptableProperties(encryptor);
        InputStream inputStream = getClass().getResourceAsStream("../connection.properties");
        props.load(inputStream);
        String userName = props.getProperty("jira.username");
        String password = props.getProperty("jira.password");
        BasicCredentials credentials = new BasicCredentials(userName, password);
        JiraClient jiraClient = new JiraClient("https://jira.esteco.com/", credentials);
        // Issue issue = jiraClient.getIssue("MF-10241");
        Issue issue = jiraClient.getIssue("MF-10827");
        DefaultEstecoIssue defaultEstecoIssue = new DefaultEstecoIssue(issue);

        Frames frames = new Frames(defaultEstecoIssue);
        frames.createCard();
    }
}