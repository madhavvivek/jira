package org.esteco.jira.pdf;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.esteco.jira.client.EstecoIssue;
import rst.pdfbox.layout.elements.*;
import rst.pdfbox.layout.elements.Frame;
import rst.pdfbox.layout.elements.render.VerticalLayoutHint;
import rst.pdfbox.layout.shape.Stroke;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.BaseFont;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Frames {

    private static final int FONT_SIZE = 50;
    private static final String COLOUR_RED_MARKUP = "{color:#CD3333}";
    private static final String PRIORITY_BLOCKER = "Blocker";
    private static final Color BLACK = Color.black;
    private static final String SUMMARY_LABEL = "Summary:";
    private static final String STORY = "Story";
    private static final String BOLD_MARKUP = "*";
    private static final int FONT_SIZE_NORMAL = 20;
    public static final String PRIORITY_LABEL = "Priority:";


    private Document createFirstCard(Document document, EstecoIssue estecoIssue) throws IOException {
        Stroke stroke = new Stroke();
        Paragraph paragraph = new Paragraph();
        Frame frame = new Frame(paragraph);

        frame.setShape(new CardRect(15, 300, 405, 275));
        frame.setBorder(BLACK, stroke);
        frame.setPadding(10, 10, 5, 5);
        frame.setMargin(40, 40, FONT_SIZE_NORMAL, 10);
        document.add(frame, VerticalLayoutHint.CENTER);

        ImageElement image;
        if (estecoIssue.getIssueTypeName().equals(STORY)) {
            image = new ImageElement(Frames.class.getResourceAsStream("story.gif"));
        } else {
            String issueTypeIconUrl = estecoIssue.getIssueTypeIconUrl();
            image = new ImageElement(Frames.class.getResourceAsStream("story.gif"));
        }
        image.setWidth(image.getWidth() / 10);
        image.setHeight(image.getHeight() / 10);
        document.add(new VerticalSpacer(-14));
        document.add(image, new VerticalLayoutHint(Alignment.Left, 22, 0, 0, 30, true));
        Paragraph storyIconText = new Paragraph();
        paragraph.setAlignment(Alignment.Center);
        Frame icon = new Frame(storyIconText);
        icon.setShape(new CardRect(25, 490, 75, 75));
        icon.setBorder(BLACK, stroke);
        icon.setPadding(5, 5, 0, 0);
        icon.setMargin(15, 0, -5, 0);
        document.add(icon, new VerticalLayoutHint());

        Paragraph storyNumber = new Paragraph();
        String issuePriority = estecoIssue.getPriorityName();
        if (PRIORITY_BLOCKER.equals(issuePriority)) {
            storyNumber.addMarkup(COLOUR_RED_MARKUP + BOLD_MARKUP + estecoIssue.getKey() + BOLD_MARKUP, FONT_SIZE, BaseFont.Helvetica);
        } else {
            storyNumber.addText(estecoIssue.getKey(), FONT_SIZE, PDType1Font.HELVETICA_BOLD);
        }
        paragraph.setAlignment(Alignment.Right);
        paragraph.setMaxWidth(310);
        Frame key = new Frame(storyNumber);
        key.setShape(new CardRect(100, 490, 310, 75));
        key.setMaxWidth(305);
        key.setBorder(Color.BLACK, stroke);
        key.setPadding(50, 5, 0, 0);
        key.setMargin(100, 0, 0, 0);
        document.add(key, VerticalLayoutHint.LEFT);

        Paragraph summaryLabel = new Paragraph();
        summaryLabel.addText(SUMMARY_LABEL, FONT_SIZE_NORMAL, BaseFont.Helvetica.getItalicFont());
        paragraph.setAlignment(Alignment.Left);
        Frame body = new Frame(summaryLabel);
        body.setShape(new CardRect(25, 310, 385, 180));
        body.setBorder(BLACK, stroke);
        body.setPadding(5, 5, 0, 0);
        body.setMargin(15, 0, 15, 0);
        document.add(body, VerticalLayoutHint.LEFT);

        String issueSummary = estecoIssue.getSummary();
        Paragraph summaryParagraph = new Paragraph();
        Frame summaryBody = new Frame(summaryParagraph);
        summaryBody.setShape(new CardRect(25, 310, 385, 180));
        summaryBody.setMaxWidth(380);
        summaryBody.setBorder(BLACK, stroke);
        summaryParagraph.addText(issueSummary, FONT_SIZE_NORMAL, PDType1Font.HELVETICA_BOLD);
        document.add(summaryBody, new VerticalLayoutHint(Alignment.Left, FONT_SIZE_NORMAL, 0, 0, 0));

        Paragraph priorityLabel = new Paragraph();
        priorityLabel.addText(PRIORITY_LABEL, FONT_SIZE_NORMAL, BaseFont.Helvetica.getItalicFont());
        paragraph.setAlignment(Alignment.Left);
        Frame pbody = new Frame(priorityLabel);
        pbody.setShape(new CardRect(25, 310, 385, 180));
        pbody.setBorder(BLACK, stroke);
        pbody.setPadding(5, 5, 0, 0);
        pbody.setMargin(15, 0, 40, 0);
        document.add(pbody, VerticalLayoutHint.LEFT);

        Paragraph priorityParagraph = new Paragraph();
        Frame priorityBody = new Frame(priorityParagraph);
        priorityBody.setShape(new CardRect(25, 310, 385, 180));
        priorityBody.setMaxWidth(380);
        priorityBody.setBorder(BLACK, stroke);

        if (PRIORITY_BLOCKER.equals(issuePriority)) {
            priorityParagraph.addMarkup(COLOUR_RED_MARKUP + BOLD_MARKUP + issuePriority + BOLD_MARKUP, 26, BaseFont.Helvetica);
        } else {
            priorityParagraph.addText(issuePriority, FONT_SIZE_NORMAL, PDType1Font.HELVETICA_BOLD);
        }
        document.add(priorityBody, new VerticalLayoutHint(Alignment.Left, FONT_SIZE_NORMAL, 0, 0, 0));

        return document;
    }

    private void save(Document document) throws IOException {
        final OutputStream outputStream = new FileOutputStream("d:/frames.pdf");
        document.save(outputStream);
    }

    private Document createSecondCard(Document document, EstecoIssue issue2) {
        return document;
    }

    public void createCards(ArrayList<EstecoIssue> issuesList) throws IOException {
        PageFormat a4Landscape = PageFormat.with().A4().landscape().margins(10, 10, 10, 10).build();
        Document document = new Document(a4Landscape);

        for (int counter = 0; counter < 4; counter++) {
            document = createIndividualCards(issuesList, document, counter);
        }
        save(document);
    }

    private Document createIndividualCards(ArrayList<EstecoIssue> issuesList, Document document, int counter) throws IOException {
        EstecoIssue issue = issuesList.get(counter);
        switch (counter) {
            case 0:
                document = createFirstCard(document, issue);
                break;
            case 1:
                document = createFirstCard(document, issue);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return document;
    }
}
