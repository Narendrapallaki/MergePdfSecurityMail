package com.mergeproject.mergecontrollor;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mergeproject.entity.UserDetails;
import com.mergeproject.mergeservice.ExcelAndPdfService;
import com.mergeproject.mergeservice.ExcelService;
import com.mergeproject.mergeservice.PdfService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class UserControllor {

	@Autowired
	private ExcelAndPdfService pdfService;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private PdfService pdfService2;

	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDetails>> getUserAll() {
		List<UserDetails> allUser = pdfService.getAllUser();

		return ResponseEntity.ok(allUser);
	}

	@GetMapping("/download/pdf")
	public ResponseEntity<byte[]> pdfDownload() {

		byte[] pdf = pdfService2.generatePdf();

		// entity class name
		String entityName = UserDetails.class.getSimpleName();
		// pdf file name

		// String fileName = entityName.toLowerCase() +".pdf";

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		String formattedDate = today.format(formatter);

		String fileName = entityName.toLowerCase() + "_" + formattedDate + ".pdf";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", fileName);

		return ResponseEntity.ok().headers(headers).body(pdf);

		// return ResponseEntity.ok(pdf);
	}

	
	@GetMapping("/download/excel")
	public ResponseEntity<InputStreamResource> download() throws IOException {
		//String fileName = "userDetails.xlsx";
		ByteArrayInputStream inputStream = excelService.generateExcel();
		InputStreamResource response = new InputStreamResource(inputStream);

		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		String formattedDate = today.format(formatter);

		String fileName = "UserDetails" + "_" + formattedDate + ".xlsx";

		
		ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
		return responseEntity;
	}
	 

	
	
}
