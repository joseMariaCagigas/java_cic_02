package es.cic.ejemploREST;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Properties;

public class AlternativoIntegracion {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new HtmlUnitDriver();
    
    
	Properties pr = new Properties();
	pr.load(getClass().getResourceAsStream("jetty.properties"));
	String puerto = pr.getProperty("jetty.port");
	String puertoValido = "8080";
	try {
		Integer.parseInt(puerto);
		puertoValido = puerto;
	} catch (NumberFormatException nfe) {		
	}	
	
    baseUrl = "http://localhost:" + puertoValido + "/ejemploREST/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAlternativoIntegracion() throws Exception {
    driver.get(baseUrl + "rest/sampleRest");
  }

  @After
  public void tearDown() throws Exception {
	  driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
