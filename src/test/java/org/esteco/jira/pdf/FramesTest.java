package org.esteco.jira.pdf;

import net.rcarz.jiraclient.Component;
import net.rcarz.jiraclient.IssueType;
import net.rcarz.jiraclient.Priority;
import net.rcarz.jiraclient.User;
import org.esteco.jira.client.EstecoIssue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FramesTest {

    @Test
    public void testStoryIcon() throws Exception {
        EstecoIssue issue1 = new EstecoIssueTestStub("MF-10827", "Blocker", "Story");
        EstecoIssue issue2 = new EstecoIssueTestStub("MF-123456", "Major", "Story");
        ArrayList<EstecoIssue> issuesList = new ArrayList<>();
        issuesList.add(0, issue1);
        issuesList.add(1, issue2);
        issuesList.add(2, null);
        issuesList.add(3, null);

        Frames frames = new Frames();
        frames.createCards(issuesList);
    }

    private class EstecoIssueTestStub implements EstecoIssue {

        private final String key;
        private final String priority;
        private final String issueType;

        public EstecoIssueTestStub(String key, String priority, String issueType) {
            this.key = key;
            this.priority = priority;
            this.issueType = issueType;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getSummary() {
            return "FMI - Message explaining because compiler option is disabled";
        }

        @Override
        public Priority getPriority() {
            return null;
        }

        @Override
        public String getPriorityName() {
            return priority;
        }

        @Override
        public String getPriorityIconUrl() {
            return null;
        }

        @Override
        public IssueType getIssueType() {
            return null;
        }

        @Override
        public String getIssueTypeName() {
            return issueType;
        }

        @Override
        public String getIssueTypeDescription() {
            return null;
        }

        @Override
        public String getIssueTypeIconUrl() {
            return null;
        }

        @Override
        public User getAssignee() {
            return null;
        }

        @Override
        public String getAssigneeName() {
            return "assigneeuserid";
        }

        @Override
        public String getAssigneeDisplayName() {
            return "Assignee Full Name";
        }

        @Override
        public String getAssigneeEmail() {
            return "assignee@xyz.com";
        }

        @Override
        public User getReporter() {
            return null;
        }

        @Override
        public String getReporterName() {
            return "reporteruserid";
        }

        @Override
        public String getReporterDisplayName() {
            return "Reporter Display Name";
        }

        @Override
        public String getReporterEmail() {
            return "reporter@abc@xyz.com";
        }

        @Override
        public List<String> getLabels() {
            return null;
        }

        @Override
        public List<Component> getComponents() {
            return null;
        }
    }
}