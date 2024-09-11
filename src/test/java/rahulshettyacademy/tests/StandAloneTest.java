package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.CartPage;
import rahulshettyacademy.CheckoutPage;
import rahulshettyacademy.ConfirmationPage;
import rahulshettyacademy.OrdersPage;
import rahulshettyacademy.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"}, retryAnalyzer = Retry.class)
    public void productPurchaseTest(HashMap<String,String> input) throws IOException, InterruptedException {
    	ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
	    productCatalogue.addProductToCart(input.get("productName"));
		CartPage cp = productCatalogue.goToCartPage();
                Thread.sleep(3000);
		Boolean match = cp.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cp.goToCheckOut();
		checkoutPage.select("india");
		ConfirmationPage confirmationPage = checkoutPage.submit1();
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods= {"productPurchaseTest"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductDisplay(productName));
		
    }

    @DataProvider
    public Object[][] getData() throws IOException {
    	
    	String filePath = System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json";
    	 List<HashMap<String, String>> data = getJsonDataToMap(filePath);
        //List<HashMap<String, String>> data = dataReader.getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][] { { data.get(0) }, { data.get(1) } };
    }
}


