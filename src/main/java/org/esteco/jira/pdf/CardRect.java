package org.esteco.jira.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import rst.pdfbox.layout.shape.Rect;
import rst.pdfbox.layout.text.Position;

import java.io.IOException;

public class CardRect extends Rect {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public CardRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void add(PDDocument pdDocument, PDPageContentStream contentStream, Position upperLeft, float width, float height) throws IOException {
        contentStream.addRect(x, y, this.width, this.height);
    }
}
