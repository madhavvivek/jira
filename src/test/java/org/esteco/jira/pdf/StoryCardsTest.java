package org.esteco.jira.pdf;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class StoryCardsTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testCreateEmptyStoryCard() throws Exception {
        StoryCards storyCards = new StoryCards();
        File outFile = temporaryFolder.newFile("output.pdf");
        storyCards.createEmpty(outFile.getAbsolutePath());

        assertTrue(outFile.exists());
    }
}
