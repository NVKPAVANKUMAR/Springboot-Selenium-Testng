package com.test.automation.test.util;

import com.test.automation.common.AppConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import com.test.automation.config.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;;

@ContextConfiguration(classes= BeanConfig.class)
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties
public class AbstractBaseTest extends AbstractTestNGSpringContextTests {
    
	private static final Logger LOG = Logger.getLogger(AbstractBaseTest.class.getName());
	
	@BeforeClass(dependsOnMethods={"springTestContextPrepareTestInstance"})
	public void setConfigs(){ 
		LOG.info("Test Execution Started.");
	}
}
