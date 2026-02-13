package Newpackage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Newpackage.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

    public static void main(String[] args) throws InterruptedException {

    	String productName="ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        
      
        driver.findElement(By.id("userEmail")).sendKeys("ambikau26@gmail.com");
    
        
        driver.findElement(By.id("userPassword")).sendKeys("Ambika12345");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

    
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement pro = products.stream()
                .filter(p -> p.findElement(By.tagName("b")).getText().equals(productName))
                .findFirst().orElse(null);

        Thread.sleep(2000); 
        
      
        WebElement addToCartButton = pro.findElement(By.cssSelector(".card-body button:last-of-type"));
        

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addToCartButton);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

   
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        
        List<WebElement> Cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));

        boolean match = Cartproducts.stream().anyMatch(Cartproduct->Cartproduct.getText().equalsIgnoreCase(productName));
        
        Assert.assertTrue(match);
        
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ta-results")
        ));
         
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        
       
        WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));

        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
        js1.executeScript("arguments[0].click();", placeOrder);
        
        String message= driver.findElement(By.cssSelector(".hero-primary")).getText();
        
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
        
        driver.close();

 
    }
}