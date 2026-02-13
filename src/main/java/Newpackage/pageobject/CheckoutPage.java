package Newpackage.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	
    //driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
	 //WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
	 //driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	By results= By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		country.sendKeys("india");
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	
	public ConformationPage submitOrder()
	{
     
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollIntoView(true);", submit);
        js1.executeScript("arguments[0].click();", submit);
		return new ConformationPage(driver);
	}
	
}
