package org.esteco.jira.client;

import net.rcarz.jiraclient.*;

public class Example {

    public static void main(String[] args) {
        BasicCredentials creds = new BasicCredentials("vmadhav", "Sept@2010");
        JiraClient jira = new JiraClient("https://jira.esteco.com/", creds);

        try {
            /* Retrieve issue TEST-123 from JIRA. We'll get an exception if this fails. */
            Issue issue = jira.getIssue("MF-10241");

            /* Print the issue key. */
            System.out.println(issue);

            /* You can also do it like this: */
            System.out.println(issue.getKey());

            /* Print the summary. */
            System.out.println("Summary: " + issue.getSummary());

            /* Print the reporter's username and then the display name */
            System.out.println("Reporter: " + issue.getReporter());
            System.out.println("Reporter's Name: " + issue.getReporter().getDisplayName());

            System.out.println("Assignee: " + issue.getAssignee());
            System.out.println("Assignee's Name: " + issue.getAssignee().getDisplayName());

            /* Print existing labels (if any). */
            for (String l : issue.getLabels())
                System.out.println("Label: " + l);

            /* Print the priority. */
            Priority priority = issue.getPriority();
            System.out.println("Priority: " + priority);
            System.out.println("Priority Icon URL: " + priority.getIconUrl());

            /* Print the issue type. */
            IssueType issueType = issue.getIssueType();
            System.out.println("Issue Type: " + issueType.getName());
            System.out.println("Issue Type Icon URL: " + issueType.getIconUrl());

            /* Print the components */
            for (Component c : issue.getComponents())
                System.out.println("Components: " + c.getName());

            /* Print the description. */
            System.out.println("Description: " + issue.getDescription());
        } catch (JiraException e) {
            e.printStackTrace();
        }
    }
}
