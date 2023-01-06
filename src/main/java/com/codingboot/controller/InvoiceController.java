package com.codingboot.controller;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import com.codingboot.model.ReportTable;
import com.codingboot.service.ReportTableService;
import com.codingboot.entity.Device;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.codingboot.entity.Products;
import com.codingboot.entity.Milora;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Controller
@RestController
public class InvoiceController {

	@Autowired
	private Environment env;

	@Autowired
	private ReportTableService reportTableService;

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
	public ResponseEntity<byte[]> downloadInvoice4(@RequestParam("name") String name,
												   @RequestParam("path") String pdfPath,
												   @RequestParam("clientFolderName") String clientFolderName,
												   @RequestParam("apiEndPoint") String apiEndPoint) throws JRException, IOException {
		System.out.println(name+"\n"+pdfPath);
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
		,new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment")
		,new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment"),
				new Milora("s","20:10","stoped",".03ms","description","comment")
		,new Milora("s","20:10","stoped",".03ms","description","comment"),
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



//
//		JasperReport compileReport = JasperCompileManager
//				.compileReport(new FileInputStream("src/main/resources/Milora_A4SimpleKeys.jrxml"));

		JasperReport compileReport=null;
		File theDir = new File(pdfPath);
		File clientDir = new File(clientFolderName);
		//if (theDir.exists() && clientDir.exists()){
			compileReport = JasperCompileManager
					.compileReport(new FileInputStream(pdfPath));
		//}

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");
		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		//System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}


	@GetMapping(value = "/pdf5",
			consumes="application/json",
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice5(@RequestHeader Map<String, String> requestHeaders,
												   @RequestBody List<Device> listOfDevice) throws JRException, IOException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfDevice,false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/xoupdatedreport.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

	}

	@GetMapping(value = "/pdf6",
			consumes="application/json",
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice6(@RequestHeader Map<String, String> requestHeaders,
												   @RequestBody List<Device> listOfDevice) throws JRException, IOException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfDevice,false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");


		String string = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<!-- Created with Jaspersoft Studio version 6.20.0.final using JasperReports Library version 6.20.0-2bc7ab61c56f459e8176eb05c7705e145cd400ad  -->\n" +
				"<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"xoupdatedreport\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"fee13ff4-cabf-4168-9507-854bbe91b32b\">\n" +
				"\t<property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"xoupdatedreportendpoint.jrdax\"/>\n" +
				"\t<queryString language=\"json\">\n" +
				"\t\t<![CDATA[data]]>\n" +
				"\t</queryString>\n" +
				"\t<field name=\"name\" class=\"java.lang.String\">\n" +
				"\t\t<property name=\"net.sf.jasperreports.json.field.expression\" value=\"name\"/>\n" +
				"\t\t<fieldDescription><![CDATA[name]]></fieldDescription>\n" +
				"\t</field>\n" +
				"\t<field name=\"description\" class=\"java.lang.String\">\n" +
				"\t\t<property name=\"net.sf.jasperreports.json.field.expression\" value=\"description\"/>\n" +
				"\t\t<fieldDescription><![CDATA[description]]></fieldDescription>\n" +
				"\t</field>\n" +
				"\t<field name=\"parent\" class=\"java.lang.Integer\">\n" +
				"\t\t<property name=\"net.sf.jasperreports.json.field.expression\" value=\"parent\"/>\n" +
				"\t\t<fieldDescription><![CDATA[parent]]></fieldDescription>\n" +
				"\t</field>\n" +
				"\t<field name=\"id\" class=\"java.lang.Integer\">\n" +
				"\t\t<property name=\"net.sf.jasperreports.json.field.expression\" value=\"id\"/>\n" +
				"\t\t<fieldDescription><![CDATA[id]]></fieldDescription>\n" +
				"\t</field>\n" +
				"\t<field name=\"enable\" class=\"java.lang.String\">\n" +
				"\t\t<property name=\"net.sf.jasperreports.json.field.expression\" value=\"enable\"/>\n" +
				"\t\t<fieldDescription><![CDATA[enable]]></fieldDescription>\n" +
				"\t</field>\n" +
				"\t<field name=\"token\" class=\"java.lang.String\">\n" +
				"\t\t<property name=\"net.sf.jasperreports.json.field.expression\" value=\"token\"/>\n" +
				"\t\t<fieldDescription><![CDATA[token]]></fieldDescription>\n" +
				"\t</field>\n" +
				"\t<group name=\"name\">\n" +
				"\t\t<groupExpression><![CDATA[$F{name}]]></groupExpression>\n" +
				"\t</group>\n" +
				"\t<group name=\"description\">\n" +
				"\t\t<groupExpression><![CDATA[$F{description}]]></groupExpression>\n" +
				"\t</group>\n" +
				"\t<group name=\"parent\">\n" +
				"\t\t<groupExpression><![CDATA[$F{parent}]]></groupExpression>\n" +
				"\t</group>\n" +
				"\t<group name=\"id\">\n" +
				"\t\t<groupExpression><![CDATA[$F{id}]]></groupExpression>\n" +
				"\t</group>\n" +
				"\t<group name=\"enable\">\n" +
				"\t\t<groupExpression><![CDATA[$F{enable}]]></groupExpression>\n" +
				"\t</group>\n" +
				"\t<group name=\"token\">\n" +
				"\t\t<groupExpression><![CDATA[$F{token}]]></groupExpression>\n" +
				"\t</group>\n" +
				"\t<background>\n" +
				"\t\t<band splitType=\"Stretch\"/>\n" +
				"\t</background>\n" +
				"\t<title>\n" +
				"\t\t<band height=\"79\" splitType=\"Stretch\">\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"180\" y=\"30\" width=\"231\" height=\"30\" uuid=\"9341b823-6467-432e-bfa3-228327830486\"/>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"20\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[XO DEVICES REPORT]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t</band>\n" +
				"\t</title>\n" +
				"\t<pageHeader>\n" +
				"\t\t<band height=\"1\" splitType=\"Stretch\"/>\n" +
				"\t</pageHeader>\n" +
				"\t<columnHeader>\n" +
				"\t\t<band height=\"61\" splitType=\"Stretch\">\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"-20\" y=\"0\" width=\"100\" height=\"30\" forecolor=\"#F7192B\" uuid=\"9a716150-3d10-4d21-b16d-84d518e80381\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"043260ea-22d7-402b-ac6d-bf471ca787dd\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"12\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[id]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"80\" y=\"0\" width=\"100\" height=\"30\" forecolor=\"#F7192B\" uuid=\"8d7039dd-7c17-434d-88fd-f03535cc85d3\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"dc8f8757-4342-42c6-8be0-aa2b716ed37e\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"12\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[name]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"180\" y=\"0\" width=\"100\" height=\"30\" forecolor=\"#F7192B\" uuid=\"7e8fb3d2-13bf-4597-83a0-80eb9ebfc776\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"4741a235-5d7c-409e-942c-b39a8a25a18d\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"12\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[token]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"280\" y=\"0\" width=\"100\" height=\"30\" forecolor=\"#F7192B\" uuid=\"d751d010-2cca-4833-9b43-981bb2244e9b\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"dc6dd737-ba67-4ec9-be1f-1e826fe345e9\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"12\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[enable]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"380\" y=\"0\" width=\"100\" height=\"30\" forecolor=\"#F7192B\" uuid=\"549a3ca7-0906-4754-96dc-fe0a99771c7e\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"578de257-6eb8-44bd-9947-81bace34d11f\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"12\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[parent]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t\t<staticText>\n" +
				"\t\t\t\t<reportElement x=\"480\" y=\"0\" width=\"100\" height=\"30\" forecolor=\"#F7192B\" uuid=\"7f162556-d224-4dbf-9706-61a02e5b38b0\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"2c41e31e-72d6-4c7e-863c-856bad20646b\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font size=\"12\" isBold=\"true\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<text><![CDATA[description]]></text>\n" +
				"\t\t\t</staticText>\n" +
				"\t\t</band>\n" +
				"\t</columnHeader>\n" +
				"\t<detail>\n" +
				"\t\t<band height=\"126\" splitType=\"Stretch\">\n" +
				"\t\t\t<textField>\n" +
				"\t\t\t\t<reportElement x=\"-20\" y=\"80\" width=\"100\" height=\"30\" uuid=\"5ca045e1-0f5c-4cca-bf1f-436eea0b679a\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"043260ea-22d7-402b-ac6d-bf471ca787dd\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font isBold=\"false\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<textFieldExpression><![CDATA[$F{id}]]></textFieldExpression>\n" +
				"\t\t\t</textField>\n" +
				"\t\t\t<textField>\n" +
				"\t\t\t\t<reportElement x=\"80\" y=\"80\" width=\"100\" height=\"30\" uuid=\"f93da916-d9ac-4153-bda8-8aa893c63515\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"dc8f8757-4342-42c6-8be0-aa2b716ed37e\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font isBold=\"false\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<textFieldExpression><![CDATA[$F{name}]]></textFieldExpression>\n" +
				"\t\t\t</textField>\n" +
				"\t\t\t<textField>\n" +
				"\t\t\t\t<reportElement x=\"180\" y=\"80\" width=\"100\" height=\"30\" uuid=\"e1471671-eafc-4181-9bc4-d87d07d5cf2a\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"4741a235-5d7c-409e-942c-b39a8a25a18d\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font isBold=\"false\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<textFieldExpression><![CDATA[$F{token}]]></textFieldExpression>\n" +
				"\t\t\t</textField>\n" +
				"\t\t\t<textField>\n" +
				"\t\t\t\t<reportElement x=\"280\" y=\"80\" width=\"100\" height=\"30\" uuid=\"ddf8fd47-c601-4e1b-942b-95ad74d16079\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"dc6dd737-ba67-4ec9-be1f-1e826fe345e9\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font isBold=\"false\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<textFieldExpression><![CDATA[$F{enable}]]></textFieldExpression>\n" +
				"\t\t\t</textField>\n" +
				"\t\t\t<textField>\n" +
				"\t\t\t\t<reportElement x=\"380\" y=\"80\" width=\"100\" height=\"30\" uuid=\"02318ebf-e887-4f99-ba0e-c18b7c21a178\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"578de257-6eb8-44bd-9947-81bace34d11f\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font isBold=\"false\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<textFieldExpression><![CDATA[$F{parent}]]></textFieldExpression>\n" +
				"\t\t\t</textField>\n" +
				"\t\t\t<textField>\n" +
				"\t\t\t\t<reportElement x=\"480\" y=\"80\" width=\"100\" height=\"30\" uuid=\"8f09d0bb-7c8f-4a87-86d7-fcaaa6945ab1\">\n" +
				"\t\t\t\t\t<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"2c41e31e-72d6-4c7e-863c-856bad20646b\"/>\n" +
				"\t\t\t\t</reportElement>\n" +
				"\t\t\t\t<textElement textAlignment=\"Center\">\n" +
				"\t\t\t\t\t<font isBold=\"false\"/>\n" +
				"\t\t\t\t</textElement>\n" +
				"\t\t\t\t<textFieldExpression><![CDATA[$F{description}]]></textFieldExpression>\n" +
				"\t\t\t</textField>\n" +
				"\t\t</band>\n" +
				"\t</detail>\n" +
				"\t<columnFooter>\n" +
				"\t\t<band splitType=\"Stretch\"/>\n" +
				"\t</columnFooter>\n" +
				"\t<pageFooter>\n" +
				"\t\t<band splitType=\"Stretch\"/>\n" +
				"\t</pageFooter>\n" +
				"\t<summary>\n" +
				"\t\t<band splitType=\"Stretch\"/>\n" +
				"\t</summary>\n" +
				"</jasperReport>\n";

		InputStream inputStream = new ByteArrayInputStream(string.getBytes(Charset.forName("UTF-8")));
		JasperReport compileReport = JasperCompileManager.compileReport(inputStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);


		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
		//String sxml = JasperExportManager.exportReportToXml(jasperPrint);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

	}

	//export as xml report types
	@GetMapping(value = "/pdf7",
			consumes="application/json")
	public ResponseEntity<String> downloadInvoice7() throws JRException, IOException {
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

		//byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
       String data  = JasperExportManager.exportReportToXml(jasperPrint);
		//return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XML).body(data);
		//String data  = JasperExportManager.exportReportToHtmlFile();
		System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.xml");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XML).body(data);
	}

	//export as html and other types
	@PostMapping(value = "/pdf8",
			consumes="application/json")
	public ResponseEntity<byte[]> downloadInvoice8(@RequestBody ReportTable reportTable) throws JRException, IOException {
		final Exporter exporter;
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		boolean html = false;
		JasperReport compileReport = null;
		String format = "HTML";

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(125, "last", 54884)), false);

		//System.out.println("reportLocation : "+reportTable.getPath());
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");

		File theDir = new File(reportTable.getPath());
		if (theDir.exists()){
			compileReport = JasperCompileManager
					.compileReport(new FileInputStream(reportTable.getPath()));
		}

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		switch (format) {
			case "HTML":
				exporter = new HtmlExporter();
				exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
				html = true;
				break;

			case "CSV":
				exporter = new JRCsvExporter();
				break;

			case "XML":
				exporter = new JRXmlExporter();
				break;

			case "XLSX":
				exporter = new JRXlsxExporter();
				break;

			case "PDF":
				exporter = new JRPdfExporter();
				break;

			default:
				throw new JRException("Unknown report format: ");
		}

		if (!html) {
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		}

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.exportReport();

		byte [] data = out.toByteArray();

		//String data  = JasperExportManager.exportReportToXml(jasperPrint);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.html");

		return ResponseEntity.ok().headers(headers).body(data);
	}

	//working with iframe
	@GetMapping(value = "/pdf9", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice9(@RequestParam("name") String name,
												   @RequestParam("path") String pdfSavedFileName,
												   @RequestParam("clientFolderName") String clientFolderName,
												   @RequestParam("apiEndPoint") String apiEndPoint) throws JRException, IOException {
		System.out.println(name+"\n"+pdfSavedFileName+"\n"+clientFolderName);

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(121, "gihan", 54884),
				new Products(122, "Mouse", 54884),
				new Products(123, "Laptop", 54884),
				new Products(124, "Mobile", 54884),
				new Products(125, "last", 54884)), false);


		Map<String, Object> parameters = new HashMap<>();
		parameters.put("total", "9999");

		JasperReport compileReport=null;
		File theDir = new File(pdfSavedFileName);
		File clientDir = new File(clientFolderName);
		//if (theDir.exists() && clientDir.exists()){
		compileReport = JasperCompileManager
				.compileReport(new FileInputStream(env.getProperty("report_folder_path")+"/"+clientFolderName+"/"+pdfSavedFileName));
		//}

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// System.currentTimeMillis() + ".pdf");
		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		//System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}


	@PostMapping("/upload")
	public  ModelAndView  uploadFile(@RequestParam("file") MultipartFile file,
									 @RequestParam("reportName") String reportName,
									 @RequestParam("apiName") String apiName,
							 RedirectAttributes attributes) {
		 System.out.println(apiName);
		 System.out.println(reportName);
		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return new ModelAndView("redirect:/");
		}

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// save the file on the local file system
		try {
			//if not exist create folder for specific client
			File theDir = new File("./client-folder");
			if (!theDir.exists()){
				theDir.mkdirs();
			}

		   //Path path = Paths.get("D:\\spring-data-jpa-example-master\\src\\main\\resources\\" + reportName);
			Path path = Paths.get("./client-folder/"+reportName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded " + reportName + '!');
		return new ModelAndView("redirect:/");
	}
	@PostMapping("/upload2")
	public ModelAndView   uploadFile2(@RequestParam("file") MultipartFile file,
										   @RequestParam("reportName") String reportName,
										   @RequestParam("apiName") String apiName,
										   @RequestParam("description") String description,
										   @RequestParam("clientFolderId") String clientFolderId,
										   RedirectAttributes attributes) {
		System.out.println(apiName);
		System.out.println(reportName);

		HashMap<String, List> map = new HashMap<>();
		HashMap<String,String> obj = new HashMap<>();
		List<Map> arr = new ArrayList<>();
		obj.put("dataset",apiName);
		obj.put("reportName", reportName);
		obj.put("description", description);

		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			obj.put("file", "no file");
		}
		obj.put("file", "include file");

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// save the file on the local file system
		try {
			//if not exist create folder for specific client
			String report_folder_path = env.getProperty("report_folder_path");
			if(!report_folder_path.isEmpty() && !clientFolderId.isEmpty()) {
				File theDir = new File(report_folder_path+"/"+clientFolderId);
				if (!theDir.exists()) {
					theDir.mkdirs();
					obj.put("client_folder_created", report_folder_path+"/"+clientFolderId);
				}

				obj.put("client_folder_access", "access previous folder");

				//Path path = Paths.get("D:\\spring-data-jpa-example-master\\src\\main\\resources\\" + reportName);
				if(!clientFolderId.isEmpty() && !reportName.isEmpty()) {
					String fileSavedPath = report_folder_path +"/"+ clientFolderId+"/" +Math.random()+reportName;
					Path path = Paths.get(fileSavedPath);
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

					obj.put("file_upload",fileSavedPath);

					ReportTable reportTable = new ReportTable(1, reportName, description, fileSavedPath, new Date().toString(), "20", apiName,clientFolderId);
					reportTableService.addReportTable(reportTable);
				}
			}
		} catch (IOException e) {
			obj.put("err", e.getMessage());
			e.printStackTrace();
		}
		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded " + reportName + '!');
		arr.add(obj);
		map.put("row",arr);
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/saveReport")
	public ReportTable addRecord(@RequestBody ReportTable reportTable){
		return reportTableService.addReportTable(reportTable);
	}

	@GetMapping("/findAllReports")
	public List<ReportTable> findAllReports(){
		return reportTableService.fetchAllReportTable();
	}

	@GetMapping("/deleteByIdReport")
	public void deleteByIdReport(@RequestParam String id){
		reportTableService.deleteByIdReport(Integer.parseInt(id));
	}

}
