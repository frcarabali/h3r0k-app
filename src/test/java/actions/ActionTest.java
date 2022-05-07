package actions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commonFuntions.BaseTest;
import io.cucumber.datatable.DataTable;
import pages.TestPages;



public class ActionTest extends BaseTest {
	
	WebDriver driver;
	private static Logger log = Logger.getLogger(ActionTest.class);
	TestPages testPage = new TestPages(driver);
		
	public ActionTest(WebDriver driver) {
		super(driver);
	}
	
	
	public void validAccess(String text) {
		log.info("************ ValidAccess *********");
		try {	
			explicitWait(testPage.mainText);
			assertTextElement(testPage.mainText,text);
			attachCapture("MainPage");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - validAccess() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - validAccess() ##########"+ e,false);
		}
		
	}
	
	public void newItem(DataTable table) throws InterruptedException {
		log.info("************ create a new item *********");
		List<Map<String, String>> data = table.asMaps(String.class, String.class);	
		try {
		for (Map<String, String> dataFeature : data) {
			loadImage(testPage.inputImage, dataFeature.get("Image"));
			writeElement(testPage.textArea,  dataFeature.get("Text"));
			submit(testPage.createItemButton);			
		}
		attachCapture("createANewItem");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - newItem() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - newItem() ##########"+ e,false);
		}
				
	}
	
	public void editedItem(String nameEditItem,DataTable table) throws InterruptedException {
		log.info("************ edit an item *********");
		List<Map<String, String>> data = table.asMaps(String.class, String.class);	
		try {
		for (Map<String, String> dataFeature : data) {
			explicitWait(By.xpath("//*[text()='"+nameEditItem+"']/preceding::button[text()='Edit']"));
			ScrollCentered(By.xpath("//*[text()='"+nameEditItem+"']/preceding::button[text()='Edit']"));
			submit(By.xpath("//*[text()='"+nameEditItem+"']/preceding::button[text()='Edit']"));
			loadImage(testPage.inputImage, dataFeature.get("Image2"));
			writeElement(testPage.textArea,  dataFeature.get("Text2"));
			submit(testPage.updateItemButton);			
		}
		attachCapture("editedAnItem");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - editedAnItem() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - editedAnItem() ##########"+ e,false);
		}
				
	}
	
	public void deletedItem(String nameDeletedItem){
		log.info("************ Delete my item *********");
		try {	
			explicitWait(By.xpath("//p[text()='"+nameDeletedItem+"']/parent::div/parent::div/child::div[1]/child::button[text()='Delete']"));
			ScrollCentered(By.xpath("//p[text()='"+nameDeletedItem+"']/parent::div/parent::div/child::div[1]/child::button[text()='Delete']"));
			submit(By.xpath("//p[text()='"+nameDeletedItem+"']/parent::div/parent::div/child::div[1]/child::button[text()='Delete']"));
			explicitWait(testPage.DeletedButton);
			submit(testPage.DeletedButton);
			attachCapture("DeletedMyItem");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - DeletedMyItem() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - DeletedMyItem() ##########"+ e,false);
		}
	}
	
	public void maxLongTextItem(DataTable tabla) throws InterruptedException {
		log.info("************ the max long in description text is checked *********");
		List<Map<String, String>> data = tabla.asMaps(String.class, String.class);	
		try {
		for (Map<String, String> dataFeature : data) {
			explicitWait(testPage.textArea);
			ScrollCentered(testPage.textArea);
			writeElement(testPage.textArea,  dataFeature.get("Validation"));
					
			if (dataFeature.get("Validation").length() == 0)
				{
					assertEquals("This is the validation between...", getElement(testPage.createItemButton), "true");
				}	else if (dataFeature.get("Validation").length() <= 300)
				{
					assertEquals("This is the validation between...", getElement(testPage.createItemButton), null);
				} else
				{
					assertEquals("This is the validation between...", getElement(testPage.createItemButton), "true");
				}
			clear(testPage.textArea);
		}
		attachCapture("validatedMaxLong");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - validatedMaxLong() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - validatedMaxLong() ##########"+ e,false);
		}		
	}
	
	public void validatedItem(String nameValidatedItem) throws InterruptedException {
		log.info("************ validated the item *********");
		try {
			explicitWait(By.xpath("//*[text()='"+nameValidatedItem+"']"));
			ScrollCentered(By.xpath("//*[text()='"+nameValidatedItem+"']"));
			assertTextElement(By.xpath("//*[text()='"+nameValidatedItem+"']"),nameValidatedItem);
			attachCapture("validatedItem");
		} catch (Exception e) {
			log.error("####### ERROR PruebaAccion - validatedItem() ##########"+ e);
			assertTrue("####### ERROR PruebaAccion - validatedItem() ##########"+ e,false);
		}		
	}
	
}
