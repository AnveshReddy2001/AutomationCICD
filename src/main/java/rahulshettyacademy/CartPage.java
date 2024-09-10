package rahulshettyacademy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    public Boolean verifyProductDisplay(String productName) {
       Boolean match=   productTitles.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
       return match;
    }

    public CheckoutPage goToCheckOut() {
        checkoutEle.click();
        return new CheckoutPage(driver);
    }
}




