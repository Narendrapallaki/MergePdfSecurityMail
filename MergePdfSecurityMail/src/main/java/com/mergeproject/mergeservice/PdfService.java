package com.mergeproject.mergeservice;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

@Service
public class PdfService {

	@Autowired
	private ExcelAndPdfService pdfService;

	public byte[] generatePdf() {

		List<com.mergeproject.entity.UserDetails> userList = pdfService.getAllUser();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(out);
		com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
		Document document = new Document(pdfDoc);

		//  Title of the page
		document.add(new Paragraph("User Details").setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER)
				.setFontColor(ColorConstants.BLACK));

		document.add(new Paragraph("\n"));

		// Table with columns
		Table table = new Table(4);

		
		table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
		table.addHeaderCell(new Cell().add(new Paragraph("Name").setBold()));
		table.addHeaderCell(new Cell().add(new Paragraph("Role").setBold()));
		table.addHeaderCell(new Cell().add(new Paragraph("Location").setBold()));

	
		for (com.mergeproject.entity.UserDetails user : userList) {
			table.addCell(new Cell().add(new Paragraph(String.valueOf(user.getId()))));
			table.addCell(new Cell().add(new Paragraph(user.getName())));
			table.addCell(new Cell().add(new Paragraph(user.getRole())));
			table.addCell(new Cell().add(new Paragraph(user.getLocation())));
		}

		document.add(table);
		document.close();

		byte[] pdfBytes = out.toByteArray();


		return pdfBytes;

	}

}
