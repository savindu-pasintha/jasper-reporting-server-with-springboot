package com.codingboot.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingboot.entity.Product;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class InvoiceController {

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Product(121, "gihan", 54884),
				new Product(122, "Mouse", 54884),
				new Product(123, "Laptop", 54884),
				new Product(124, "Mobile", 54884),
				new Product(125, "Headphone", 54884)
		), false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "7000");

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/invoice.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

	@GetMapping(value = "/pdf1", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice1() throws JRException, IOException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Product(121, "gihan", 54884),
				new Product(122, "Mouse", 54884),
				new Product(123, "Laptop", 54884),
				new Product(124, "Mobile", 54884),
				new Product(125, "Headphone", 54884)
		), false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/simpleCDS.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
