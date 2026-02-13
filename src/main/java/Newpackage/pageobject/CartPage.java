package Newpackage.pageobject;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;

	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> Cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	 //driver.findElement(By.cssSelector(".totalRow button")).click();
	//List<WebElement> Cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
	
	public boolean VerifyProductDisplay(String productName)
	{
		boolean match=Cartproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	
	
	
}
