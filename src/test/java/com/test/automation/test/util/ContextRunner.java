package com.test.automation.test.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

@SpringBootTest
public class ContextRunner implements CommandLineRunner{
		
	public static void main(String[] args){		
		SpringApplication.run(ContextRunner.class);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Arguments:" + args);
		XmlSuite suite = new XmlSuite();
		suite.setName("Suite");
		
		XmlTest test = new XmlTest(suite);
		test.setName("Test");
		
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.test.automation.test.cases.ui.OrangeHRM"));
		test.setXmlClasses(classes);
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();	
	}	
}
