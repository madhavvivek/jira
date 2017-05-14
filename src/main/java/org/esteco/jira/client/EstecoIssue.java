package org.esteco.jira.client;

import net.rcarz.jiraclient.Component;
import net.rcarz.jiraclient.IssueType;
import net.rcarz.jiraclient.Priority;
import net.rcarz.jiraclient.User;

import java.util.List;

public interface EstecoIssue {

    String getKey();

    String getSummary();

    Priority getPriority();

    String getPriorityName();

    String getPriorityIconUrl();

    IssueType getIssueType();

    String getIssueTypeName();

    String getIssueTypeDescription();

    String getIssueTypeIconUrl();

    User getAssignee();

    String getAssigneeName();

    String getAssigneeDisplayName();

    String getAssigneeEmail();

    User getReporter();

    String getReporterName();

    String getReporterDisplayName();

    String getReporterEmail();

    List<String> getLabels();

    List<Component> getComponents();
}
