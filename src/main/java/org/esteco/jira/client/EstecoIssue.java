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

    String getPriorityIconUrl();

    IssueType getIssueType();

    String getIssueTypeIconUrl();

    User getAssignee();

    User getReporter();

    List<String> getLabels();

    List<Component> getComponents();
}
