package com.asses.definitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

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

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
		driver.manage().window().maximize();
	}

	@Given("Open the ChromeBrowser and launch the application")
	public void loginTest() {
		driver.get(url);
	}

	@When("User should able to list different request types, end points")
	public void getListOfRequests() {
		List<WebElement> we = driver.findElements(By.xpath("//div[@class='endpoints']//following-sibling::li"));
		for (WebElement ele : we) {
			System.out.println(" request types :: " + ele.getText());
			ele.click();
			String Url = driver.findElement(By.xpath("//span[@class='url']")).getText();
			System.out.println("end point :: " + Url);

		}
	}

	@Then("User should display {string} details after selecting a specific option")
	public void verifyDetailsOfEndPoint(String EndPoint) {
		List<WebElement> we = driver.findElements(By.xpath("//div[@class='endpoints']//following-sibling::li"));
		for (WebElement ele : we) {
			if (ele.getText().contains(EndPoint)) {
				ele.click();
				String Url = driver.findElement(By.xpath("//span[@class='url']")).getText();
				String Response = driver.findElement(By.xpath("//span[@data-key='response-code']")).getText();
				Assert.assertEquals(Url, "/api/users/23", "URL not matched");
				Assert.assertEquals(Response, "", "Response not matched");
				break;
			}

		}
	}

	@And("User should able to view button to navigate to support page")
	public void verifySupportButtonIsPresent() {
		WebElement supportbutton = driver.findElement(By.xpath("//div[@class=\"t-center\"]/button"));
		Assert.assertTrue(supportbutton.isDisplayed(), "Support button not displayed");
	}

	@After
	public void teardown() {

		driver.quit();
	}

	@When("User click on support button")
	public void user_click_on_support_button() {
		WebElement supportbutton = driver.findElement(By.xpath("//div[@class=\"t-center\"]/button"));
		supportbutton.click();
	}

	@Then("User should list options for one-time & monthly support")
	public void verifySupportOptions() {
		WebElement oneTime = driver.findElement(By.xpath("//input[@id='supportOneTime']"));
		WebElement monthlySupport = driver.findElement(By.xpath("//input[@id='supportRecurring']"));
		Assert.assertTrue(oneTime.isDisplayed(), "one-time not displayed");
		Assert.assertTrue(monthlySupport.isDisplayed(), "monthly support not displayed");

	}

	@Then("User should able to provide Upgrade details")
	public void verifyUpgradeDetails() {
		driver.findElement(By.id("trigger-pro")).click();
		driver.findElement(By.id("mce-EMAIL")).sendKeys("test@tesmail.com");
		driver.findElement(By.id("mc-embedded-subscribe")).click();
	}

}
