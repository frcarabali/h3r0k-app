package commonFuntions;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class BaseTest {

	public WebDriver driver;
	private static Properties pro = new Properties();
	private static InputStream in = BaseTest.class.getResourceAsStream("../test.properties");
	private static Logger log = Logger.getLogger(BaseTest.class);
	
	public BaseTest(WebDriver driver) {
		this.driver = driver;
		
	}
	/*********** START SELENIUM BASIC FUNCTIONS ******************/

	public void clear(By locator) {
		driver.findElement(locator).clear();
	}
	
	public void writeElement(By locator, String texto) {
		driver.findElement(locator).sendKeys(texto);
	}

	public void submit(By locator) {
		driver.findElement(locator).click();
	}
	


	public String getElement(By locator) {
		return driver.findElement(locator).getAttribute("disabled");
	}

	public void loadImage(By locator, String file) {
        File element = new File(file);
        driver.findElement(locator).sendKeys(element.getAbsolutePath());
    }
			
	/*********** FINISH SELENIUM BASIC FUNCTIONS ******************/	
	
	/************* START ASSERT FUNCTION ****************/
	public void assertTextElement (By locator, String Comparar) {
		
		 assertEquals(driver.findElement(locator).getText(),Comparar);
			
		}

	/************* FINISH ASSERT FUNCTION ****************/
	

	/************ START JAVASCRIPT FUNCTION ************/

	public void ScrollCentered(By locator) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView({inline: \"center\", block: \"center\", behavior: \"smooth\"});",
                Element);
    }
	
	/************ FINISH JAVASCRIPT FUNCTION ************/

	/************ START WAITS ************/
	public void explicitWait(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public String leerPropiedades(String valor) {
		try {
			pro.load(in);
		} catch (Exception e) {
			log.error("====== ERROR LEYENDO EL ARCHIVO DE PROPIEDADES========= " + e);
		}
		return pro.getProperty(valor);
	}
	
	
	public String extractTextElement(By locator) {
		return driver.findElement(locator).getText();
	}

	 /*
     * Metodo que selecciona el tipo de captura que se va a realizar (Reporte o
     * Local) Modificar el archivo test.properties el valor "TipoCaptura"
     */
    public void attachCapture(String descripcion) {
        String tipoCaptura = leerPropiedades("TipoCaptura");
        if (tipoCaptura.equals("Local")) {
        	attachLocalCapture(descripcion);
        } else {
        	attachReportCapture(descripcion);
        }
    }

    /*
     *This action runs and saves adds images to Allure reports
     */
    public byte[] attachReportCapture(String descripcion) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMMM-yyyy hh.mm.ss");
        byte[] captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // log.info("**************** Evidencia Tomada Reporte:" + descripcion +
        // dateFormat.format(GregorianCalendar.getInstance().getTime())
        // +"**************");
        Allure.addAttachment(descripcion + dateFormat.format(GregorianCalendar.getInstance().getTime()),
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return captura;
    }

    /*
     * This action saves the image captures in the following path
     * src/test/resources/Data/Capturas
     */
    public void attachLocalCapture(String descripcion) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMMM-yyyy hh.mm.ss");
            String imageNombre = leerPropiedades("CapturasPath") + "\\" + descripcion
                    + dateFormat.format(GregorianCalendar.getInstance().getTime());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // log.info("**************** Evidencia Tomada Local:" + descripcion +
            // dateFormat.format(GregorianCalendar.getInstance().getTime())
            // +"**************");
            FileUtils.copyFile(scrFile, new File(String.format("%s.png", imageNombre)));
        } catch (Exception e) {
            log.error("############## ERROR,  BaseTest - attachLocalCapture() #########" + e);
        }

    }
	
   
   
}
    

