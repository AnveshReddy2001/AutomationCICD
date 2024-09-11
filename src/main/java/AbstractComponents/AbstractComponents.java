package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.CartPage;
import rahulshettyacademy.OrdersPage;


    
    public class AbstractComponents {
        public WebDriver driver;

        public AbstractComponents(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "/html/body/app-root/app-dashboard/app-sidebar/nav/ul/li[4]/button")
        WebElement cartHeader;

        @FindBy(xpath = "/html/body/app-root/app-dashboard/app-sidebar/nav/ul/li[3]/button")
        WebElement myOrders;

        

        public void waitForElementToAppear(By findBy) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        }

        public void waitForElementToAppear(WebElement findBy) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(findBy));
        }

        public void presenceOfWebElementToAppear(By locator) {

    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    	}

        public OrdersPage goToOrdersPage() {
            
            
            myOrders.click();
            OrdersPage op = new OrdersPage(driver);
    		return op;
        }

        public CartPage goToCartPage() {
           
           
            cartHeader.click();
            CartPage cp = new CartPage(driver);
    		return cp;
        }
        public void waitForElementToDisappear(WebElement ele) throws InterruptedException
    	{
    		Thread.sleep(5000);
//    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//    		wait.until(ExpectedConditions.invisibilityOf(ele));

    	}
    }



