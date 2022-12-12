package com.codingboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import com.codingboot.entity.Product;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@SpringBootApplication
public class SpringDataJpaExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		 SpringApplication.run(SpringDataJpaExampleApplication.class, args);

//		SpringApplication application = new SpringApplication(SpringDataJpaExampleApplication.class);
//		application.run(args);
//		

	}

	@Override
	public void run(String... args) throws Exception {

//		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
//
//				new Product(121, "Keyboard", 54884), new Product(122, "Mouse", 54884),
//				new Product(123, "Laptop", 54884), new Product(124, "Mobile", 54884),
//				new Product(125, "Headphone", 54884)
//
//		), false);
//
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("total", "7000");
//
//		JasperReport compileReport = JasperCompileManager
//				.compileReport(new FileInputStream("src/main/resources/invoice.jrxml"));
//
//		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
//
//		JasperExportManager.exportReportToPdfFile(jasperPrint, System.currentTimeMillis() + ".pdf");

	}
}
