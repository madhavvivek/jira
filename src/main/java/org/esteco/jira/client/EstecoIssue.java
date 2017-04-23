package org.esteco.jira.client;

import net.rcarz.jiraclient.*;

import java.util.List;

public class EstecoIssue {

    private final String key;
    private final String summary;
    private final Priority priority;
    private final String priorityIconUrl;
    private final IssueType issueType;
    private final String issueTypeIconUrl;
    private final User assignee;
    private final User reporter;
    private final List<String> labels;
    private final List<Component> components;

    public EstecoIssue(Issue issue) {
        key = issue.getKey();
        summary = issue.getSummary();
        priority = issue.getPriority();
        priorityIconUrl = priority.getIconUrl();
        assignee = issue.getAssignee();
        reporter = issue.getReporter();
        labels = issue.getLabels();
        components = issue.getComponents();
        issueType = issue.getIssueType();
        issueTypeIconUrl = issueType.getIconUrl();
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getPriorityIconUrl() {
        return priorityIconUrl;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public String getIssueTypeIconUrl() {
        return issueTypeIconUrl;
    }

    public User getAssignee() {
        return assignee;
    }

    public User getReporter() {
        return reporter;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Component> getComponents() {
        return components;
    }
}
