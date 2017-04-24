package org.esteco.jira.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;

public class StoryCards {

    public void createEmpty(String outfile) {
        createFourCardsLandscapeMode(outfile);
    }

    private void createFourCardsLandscapeMode(String outfile) {
        PDDocument doc = null;
        try {
            doc = new PDDocument();

            PDFont font = PDType1Font.HELVETICA;
            PDPage page = new PDPage(PDRectangle.A4);
            page.setRotation(90);
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            //Setting the non stroking color
            contentStream.setNonStrokingColor(Color.ORANGE);

            // Four story cards
            contentStream.addRect(25, 25, 272, 395);
            // For icon
            contentStream.addRect(30, 30, 75, 385);
            contentStream.addRect(30, 30, 75, 75);
            contentStream.addRect(105, 30, 187, 385);

            contentStream.addRect(25, 420, 272, 395);
            // For icon
            contentStream.addRect(30, 425, 75, 385);
            contentStream.addRect(30, 425, 75, 75);
            contentStream.addRect(105, 425, 187, 385);

            contentStream.addRect(297, 25, 272, 395);
            // For icon
            contentStream.addRect(302, 30, 75, 385);
            contentStream.addRect(302, 30, 75, 75);
            contentStream.addRect(105, 30, 187, 385);

            contentStream.addRect(297, 420, 272, 395);
            // For icon
            contentStream.addRect(302, 425, 75, 385);
            contentStream.addRect(302, 425, 75, 75);
            contentStream.addRect(105, 425, 187, 385);

            //Drawing a rectangle
            contentStream.fillAndStroke();

            //Closing the ContentStream object
            contentStream.close();

            //Saving the document
            doc.save(outfile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
