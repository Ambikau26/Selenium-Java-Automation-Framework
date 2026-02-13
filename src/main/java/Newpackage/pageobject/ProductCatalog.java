package Newpackage.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	By ProductBy= By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");

	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(ProductBy);
		return products;
	}
	
	public WebElement getProductName(String productName)
	{
		  WebElement pro = getProductList().stream()
	                .filter(p -> p.findElement(By.tagName("b")).getText().equals(productName))
	                .findFirst().orElse(null);
		  return pro;

	}
	
	public void addProductToCart(String productName)
	{
	    WebElement pro = getProductName(productName);
	    WebElement addToCartButton = pro.findElement(addToCart);
	    
	    // Use JavaScript click instead of regular click
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", addToCartButton);
	    
	    waitForElementToAppear(toastMessage);
	    waitForElementToDisappear(spinner);
	}
	
	

}
