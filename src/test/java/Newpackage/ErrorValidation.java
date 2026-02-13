package Newpackage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Newpackage.Test.BasicTest;
import Newpackage.pageobject.CartPage;
import Newpackage.pageobject.CheckoutPage;
import Newpackage.pageobject.ConformationPage;
import Newpackage.pageobject.LandingPage;
import Newpackage.pageobject.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;
import Newpackage.Test.Retry;

public class ErrorValidation extends BasicTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException{

    	String productName="ZARA COAT 3";
      
        ProductCatalog productCatalog=landingPage.loginApplication("aaaaambikau26@gmail.com","2222");
        landingPage.getErrorMessage();
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }
	
	@Test
    public void productErrorValidation() throws IOException{

    	String productName="ZARA COAT 3";
      
        ProductCatalog productCatalog=landingPage.loginApplication("ambikau26@gmail.com","Ambika12345");
     
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);     
        CartPage cartPage =  productCatalog.goToCartPage();
     
        boolean match = cartPage.VerifyProductDisplay("COAT");  
        Assert.assertFalse(match);	
 
    }

}