package Newpackage.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Newpackage.Test.BasicTest;
import Newpackage.pageobject.CartPage;
import Newpackage.pageobject.CheckoutPage;
import Newpackage.pageobject.ConformationPage;
import Newpackage.pageobject.LandingPage;
import Newpackage.pageobject.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BasicTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConformationPage conformationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
           //new 
		landingPage=launchApplication();
	}
	
	@Given("^Loggeed in with username (.+) and password (.+)$")
	public void Loggeed_in_with_username_and_password(String username, String password)
	{
		productCatalog=landingPage.loginApplication(username,password); 
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		   List<WebElement> products = productCatalog.getProductList();
	        productCatalog.addProductToCart(productName); 
	}
	
	@When("^checkOut (.+) and submit the order$")
	public void checkOut_submit_order(String productName)
	{
		CartPage cartPage =  productCatalog.goToCartPage();   
        boolean match = cartPage.VerifyProductDisplay(productName);  
        Assert.assertTrue(match);	
        CheckoutPage checkoutPage= cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        conformationPage=checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string)
	{
        String message= conformationPage.getConformationMessage(); 
        Assert.assertTrue(message.equalsIgnoreCase(string));
        driver.close();;
	}
	
	
	@Then("{string} message is displayed")
	public void error_dmessage_displayed(String string)
	{
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		driver.close();
	}
	
}
