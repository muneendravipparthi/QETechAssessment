package com.asses.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class ReqresHomePage {
	private WebDriver driver=null;
	
	@FindBy(xpath = "//div[@class='endpoints']//following-sibling::li")
	public List<WebElement> requestTypes;
	
	@FindBy(xpath = "//span[@class='url']")
	public WebElement url;
	
	@FindBy(xpath = "//span[@data-key='response-code']")
	public WebElement response;
	
	@FindBy(xpath = "//div[@class='t-center']/button")
	public WebElement supportbutton;
	
	@FindBy(xpath = "//input[@id='supportOneTime']")
	public WebElement oneTimeRadioBtn;
	
	@FindBy(xpath = "//input[@id='supportRecurring']")
	public WebElement monthlySupportRadioBtn;
	
	
	
	//Support page
	@FindBy(id = "trigger-pro")
	public WebElement upgradeBtn;
	
	@FindBy(id = "mce-EMAIL")
	public WebElement emailIdInput;
	
	@FindBy(id = "mc-embedded-subscribe")
	public WebElement subscribeBtn;
	
	public ReqresHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
