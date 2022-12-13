package com.codingboot.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class InvoiceController {


	@GetMapping(value = "/datasource")
	public Map<String, List> selectors(String data) {

		HashMap<String, List> map = new HashMap<>();
		for (int i = 0; i < 20; i++) {
			HashMap<String,String> obj = new HashMap<>();
			obj.put("first","savindu");
			obj.put("last","pasintha");
			obj.put("age","26");
			List<Map> arr = new ArrayList<>();
			arr.add(obj);
			map.put(i + "row",arr);
		}
		return map;
	}

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
		parameters.put("parameter_id", "7000");

		ArrayList arrList = new ArrayList<String>();
		arrList.add("n1");
		arrList.add("n2");

		parameters.put("parameter_arr",arrList);

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
