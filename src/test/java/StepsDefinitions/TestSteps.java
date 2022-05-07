package StepsDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import actions.ActionTest;
import commonFuntions.BaseTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.cucumber.datatable.DataTable;

public class TestSteps {

	WebDriver driver;
	Logger log = Logger.getLogger(TestSteps.class);
	BaseTest baseTest;
	ActionTest pruebaAccion;

	public TestSteps() {
		// super(driver);
		this.driver = Driver.driver;
		pruebaAccion = new ActionTest(driver);
	}

	@Given("^valid when an user accesses to the page \"([^\"]*)\"$")
	public void validWhenAnUserAccessesToThePage(String text)
			throws Throwable {
		pruebaAccion.validAccess(text);
		
	}

	@When("an item is created")
	public void anItemIsCreated(DataTable table) throws InterruptedException {
		pruebaAccion.newItem(table);
	}

	@And("^an item is edited \"([^\"]*)\"$")
	public void anItemIsEdited(String nameEditItem,DataTable table) throws InterruptedException {
		pruebaAccion.editedItem(nameEditItem, table);
	}

	@And("^the created item is deleted \"([^\"]*)\"$")
	public void theCreatedItemIsDeleted(String nameDeletedItem) throws InterruptedException {
		pruebaAccion.deletedItem(nameDeletedItem);
	}
	
	@And("the max long in description text is checked")
	public void theMaxLongInDescriptionTextIsChecked(DataTable table) throws InterruptedException {
		pruebaAccion.maxLongTextItem(table);
	}

	@And("^an item is validated in the list with the text \"([^\"]*)\"$")
	public void anItemIsValidatedInTheListWithTheText(String nameValidatedItem) throws InterruptedException {
		pruebaAccion.validatedItem(nameValidatedItem);
	}

}
