package com.kmsb.flights.web.view;

import com.kmsb.flights.persistence.entity.StateVector;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;


@Component
public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        document.add(new Paragraph("flights.pdf"));

        List<StateVector> flights = (List<StateVector>) model.get("flights");

        PdfPTable table = new PdfPTable(4);
        table.setSpacingBefore(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Origin Country", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("On Ground", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Call Sign", font));
        table.addCell(cell);

        for (StateVector flight : flights) {
            table.addCell(flight.getIcao24());
            table.addCell(flight.getOriginCountry());
            table.addCell(flight.isOnGround() ? "YES" : "NO");
            table.addCell(flight.getCallsign());
        }

        document.add(table);
        if (model.get("type").equals("download")) {
            response.setHeader("Content-Disposition", "attachment; filename=flights.pdf");
        }

    }
}
