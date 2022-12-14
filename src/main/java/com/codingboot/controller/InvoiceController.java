package com.codingboot.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingboot.entity.Products;
import com.codingboot.entity.Milora;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class InvoiceController {

	@GetMapping(value = "/datasource")
	public Map<String, List> selectors(@RequestHeader Map<String, String> headers, String data) {
		HashMap<String, List> map = new HashMap<>();

		headers.forEach((key, value) -> {
			if(key.equals("token") && value.equals("abc")){
				for (int i = 0; i < 20; i++) {
					HashMap<String,String> obj = new HashMap<>();
					List<Map> arr = new ArrayList<>();
//					for(int y=0; y<10; y++) {
						obj.put("id",String.valueOf(i));
						obj.put("first", "savindu");
						obj.put("last", "pasintha");
						obj.put("age", "26");
						arr.add(obj);
//					}
					map.put(i + "row",arr);
				}
			}
			System.out.println(String.format("Header '%s' = %s", key, value));
		});
		return map;
	}

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(125, "Headphone", 54884)
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
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(125, "Headphone", 54884)
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

	@GetMapping(value = "/pdf3", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice3() throws JRException, IOException {
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment")
		), false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/Milora_A4.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");
		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		//System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	@GetMapping(value = "/pdf4", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice4() throws JRException, IOException {
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment")
		), false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/Milora_A4SimpleKeys.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");
		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		//System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
