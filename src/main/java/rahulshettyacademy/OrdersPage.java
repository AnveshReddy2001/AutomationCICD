package rahulshettyacademy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table/tbody/tr/td[position()]")
    List<WebElement> productNames;

    public Boolean verifyProductDisplay(String productName) {
        Boolean match = productNames.stream()
                .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }
}




