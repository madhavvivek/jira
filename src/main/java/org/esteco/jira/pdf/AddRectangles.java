package org.esteco.jira.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;

public class AddRectangles {

    public void doIt(String message, String outfile) throws Exception {
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

            //Drawing a rectangle
            // contentStream.addRect(200, 650, 10000, 10000);
            // contentStream.addRect(200, 650, 100, 100);

            // This is for landscape with one bug rectangle
            // contentStream.addRect(25, 25, 545, 790);

            /*
            This is for landscape with four rectangles
            // second quadrant
            contentStream.addRect(25, 25, 272, 395);
            // first quadrant
            contentStream.addRect(25, 420, 272, 395);
            // third quadrant
            contentStream.addRect(297, 25, 272, 395);
            // fourth quadrant
            contentStream.addRect(297, 420, 272, 395);
            */

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
            savePDF(outfile, doc);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
    }

    public PDDocument doItSecondQuadrant(String message, String outfile) throws Exception {
        PDDocument document = null;
        try {
            document = new PDDocument();
            PDFont font = PDType1Font.HELVETICA;
            PDPage page = new PDPage(PDRectangle.A4);
            page.setRotation(90);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            //Setting the non stroking color
            contentStream.setNonStrokingColor(Color.ORANGE);


            // One story cards

            contentStream.addRect(25, 25, 272, 395);
            // For icon
            contentStream.addRect(30, 30, 75, 385);
            contentStream.addRect(30, 30, 75, 75);
            contentStream.addRect(105, 30, 187, 385);

            // contentStream.addRect(25, 420, 190, 395);

            //Drawing a rectangle
            contentStream.fillAndStroke();

            writeStoryNumber(document, page, contentStream);

            //Closing the ContentStream object
            contentStream.close();

            document.save(outfile);

        } finally {
            if (document != null) {
                document.close();
            }
        }
        return document;
    }

    private void writeStoryNumber(PDDocument document, PDPage page, PDPageContentStream contentStream) throws IOException {
        PDRectangle pageSize = page.getMediaBox();
        float pageWidth = pageSize.getWidth();
        contentStream.concatenate2CTM(0, 1, -1, 0, pageWidth, 0);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 30);
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.newLineAtOffset(300, 530);
        // contentStream.showText("MF-12345");
        contentStream.endText();
    }

    private void savePDF(String outfile, PDDocument doc) throws IOException {
        //Saving the document
        doc.save(outfile);
    }

    public static void main(String[] args) throws Exception {
        AddRectangles app = new AddRectangles();
        // app.doIt("This is a message!", "d:/test-rectangle.pdf");
        PDDocument document = app.doItSecondQuadrant("This is a message!", "d:/test-rectangle-second-quadrant.pdf");
    }
}
