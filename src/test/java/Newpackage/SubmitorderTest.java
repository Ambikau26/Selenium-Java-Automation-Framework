package Newpackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Newpackage.Test.BasicTest;
import Newpackage.pageobject.CartPage;
import Newpackage.pageobject.CheckoutPage;
import Newpackage.pageobject.ConformationPage;
import Newpackage.pageobject.OrderPage;
import Newpackage.pageobject.ProductCatalog;


public class SubmitorderTest extends BasicTest{
	
	String productName="ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"PurchaseOrder"})
    public void submitorder(HashMap<String,String> input) throws IOException{ 	
      
        ProductCatalog productCatalog=landingPage.loginApplication(input.get("email"), input.get("password"));  
        
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("productName"));     
        CartPage cartPage =  productCatalog.goToCartPage();
        
        
       
        boolean match = cartPage.VerifyProductDisplay(input.get("productName"));  
        Assert.assertTrue(match);	
        CheckoutPage checkoutPage= cartPage.goToCheckout();
        
    
        checkoutPage.selectCountry("india");
        ConformationPage conformationPage=checkoutPage.submitOrder();
        
    
        String message= conformationPage.getConformationMessage();
        
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

 
    }
	
	@Test(dependsOnMethods="submitorder")
    public void orderHistory() throws IOException{
		
		ProductCatalog productCatalog=landingPage.loginApplication("ambikau26@gmail.com","Ambika12345");
		CartPage cartPage = productCatalog.goToCartPage();
	    OrderPage orderPage = cartPage.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyorderDisplay(productName));
	
	}
	

	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Newpackage\\data\\PurchaseOrder.json");
		
		return new Object[][] { 
			{data.get(0)}, {data.get(1)} };
			
		}
	

	}


