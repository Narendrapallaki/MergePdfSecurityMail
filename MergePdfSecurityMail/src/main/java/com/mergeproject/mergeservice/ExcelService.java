package com.mergeproject.mergeservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ExcelService {
	
	@Autowired
	private ExcelAndPdfService excelAndPdfService;
	      
	public ByteArrayInputStream generateExcel() throws IOException
	{
		List<com.mergeproject.entity.UserDetails> allUser = excelAndPdfService.getAllUser();
		 ByteArrayInputStream dataToExcel = com.mergeproject.excelreader.ExcelReader.dataToExcel(allUser);
		 
		 return dataToExcel;
	}

}
