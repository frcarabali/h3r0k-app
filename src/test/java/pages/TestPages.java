package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestPages {
	WebDriver driver;
	
					
			public By mainText;
			public By inputImage;
			public By textArea;
			public By createItemButton;
			public By updateItemButton;
			public By DeletedButton;
		
		public TestPages(WebDriver driver) {
			this.driver = driver;					
				
			mainText=By.xpath("//a[@class='navbar-brand']");
			inputImage = By.id("inputImage");
			textArea =By.name("text");
			createItemButton = By.xpath("//*[@class='btn pull-right btn-success']");
			updateItemButton = By.xpath("//*[@class='btn pull-right btn-primary']");
			DeletedButton = By.xpath("//*[text()='Yes, delete it!']");	
			
		}	
}
