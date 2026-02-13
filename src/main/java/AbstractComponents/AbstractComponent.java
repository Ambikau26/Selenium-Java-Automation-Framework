package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Newpackage.pageobject.CartPage;
import Newpackage.pageobject.OrderPage;


public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToDisappear(By locator)
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	

	 // driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	public CartPage goToCartPage() 
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() 
	{
	    orderHeader.click();
	    return new OrderPage(driver);
	}

	
   }
