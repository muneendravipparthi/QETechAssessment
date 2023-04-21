package com.asses.definitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.asses.pages.ReqresHomePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReqresPageDefinitions {
	private static WebDriver driver;
	public final static int TIMEOUT = 10;
	public final String url = "https://reqres.in/";
	ReqresHomePage reqreshomepage;

	@Before
	public void setUp() {
	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
		driver.manage().window().maximize();
		reqreshomepage = new ReqresHomePage(driver);
	}

	@Given("Open the ChromeBrowser and launch the application")
	public void loginTest() {
		driver.get(url);
	}

	@When("User should able to list different request types, end points")
	public void getListOfRequests() {
		for (WebElement element : reqreshomepage.requestTypes) {
			System.out.println(" request types :: " + element.getAttribute("data-http"));
			System.out.println(" request :: " + element.getText());
			element.click();
			String Url = reqreshomepage.url.getText();
			System.out.println("end point :: " + Url);

		}
	}

	@Then("User should display {string} details after selecting a specific option")
	public void verifyDetailsOfEndPoint(String EndPoint) {
		for (WebElement element : reqreshomepage.requestTypes) {
			if (element.getText().contains(EndPoint)) {
				element.click();
				String Url = reqreshomepage.url.getText();
				String Response = reqreshomepage.response.getText();
				Assert.assertEquals(Url, "/api/users/23", "URL not matched");
				Assert.assertEquals(Response, "", "Response not matched");
				break;
			}

		}
	}

	@And("User should able to view button to navigate to support page")
	public void verifySupportButtonIsPresent() {
		Assert.assertTrue(reqreshomepage.supportbutton.isDisplayed(), "Support button not displayed");
	}

	@After
	public void teardown() {

		driver.quit();
	}

	@When("User click on support button")
	public void user_click_on_support_button() {
		reqreshomepage.supportbutton.click();
	}

	@Then("User should list options for one-time & monthly support")
	public void verifySupportOptions() {
		Assert.assertTrue(reqreshomepage.oneTimeRadioBtn.isDisplayed(), "one-time not displayed");
		Assert.assertTrue(reqreshomepage.monthlySupportRadioBtn.isDisplayed(), "monthly support not displayed");

	}

	@Then("User should able to provide Upgrade details")
	public void verifyUpgradeDetails() {
		reqreshomepage.upgradeBtn.click();
		reqreshomepage.emailIdInput.sendKeys("test@tesmail.com");
		reqreshomepage.subscribeBtn.click();
	}

}
