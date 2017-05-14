package org.esteco.jira.client;

import net.rcarz.jiraclient.*;

import java.util.List;

public class DefaultEstecoIssue implements EstecoIssue {

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

    public DefaultEstecoIssue(Issue issue) {
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

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String getPriorityName() {
        return priority.getName();
    }

    @Override
    public String getPriorityIconUrl() {
        return priorityIconUrl;
    }

    @Override
    public IssueType getIssueType() {
        return issueType;
    }

    @Override
    public String getIssueTypeName() {
        return issueType.getName();
    }

    @Override
    public String getIssueTypeDescription() {
        return issueType.getDescription();
    }

    @Override
    public String getIssueTypeIconUrl() {
        return issueTypeIconUrl;
    }

    @Override
    public User getAssignee() {
        return assignee;
    }

    @Override
    public String getAssigneeName() {
        return assignee.getName();
    }

    @Override
    public String getAssigneeDisplayName() {
        return assignee.getDisplayName();
    }

    @Override
    public String getAssigneeEmail() {
        return assignee.getEmail();
    }

    @Override
    public User getReporter() {
        return reporter;
    }

    @Override
    public String getReporterName() {
        return reporter.getName();
    }

    @Override
    public String getReporterDisplayName() {
        return reporter.getDisplayName();
    }

    @Override
    public String getReporterEmail() {
        return reporter.getEmail();
    }

    @Override
    public List<String> getLabels() {
        return labels;
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }
}
