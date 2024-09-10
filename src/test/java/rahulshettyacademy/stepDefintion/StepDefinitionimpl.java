package rahulshettyacademy.stepDefintion;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.CartPage;
import rahulshettyacademy.CheckoutPage;
import rahulshettyacademy.ConfirmationPage;
import rahulshettyacademy.LandingPage;

import rahulshettyacademy.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionimpl extends BaseTest {

	public LandingPage landingpage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	CartPage cartPage;

	@Given("I landed on eCommerce Page")
	public void I_landed_on_eCommerce_Page() throws IOException {
		landingpage = LaunchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void loggedInUsernameAndPassword(String name, String Password) {
		productCatalogue = landingpage.loginApplication(name, Password);
	}

	@When("^I add product (.+) to cart$")
	public void iAddProductsToCart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkoutSubmitOrder(String productName) {
		CartPage cp = productCatalogue.goToCartPage();

		Boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cp.goToCheckOut();
		checkoutPage.select("india");
		confirmationPage = checkoutPage.submit1();
	}

	@Then("{string} message is displayed on the ConfirmationPage")
	public void messageDisplayedConfirmationPage(String string) {
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	 @Given("the user is on the landing page")
	    public void the_user_is_on_the_landing_page() throws IOException {
	        landingpage = LaunchApplication();
	    }

	    @When("the user tries to login with email {string} and password {string}")
	    public void the_user_tries_to_login_with_email_and_password(String email, String password) {
	        landingpage.loginApplication(email, password);
	    }

	    @Then("the error message {string} should be displayed")
	    public void the_error_message_should_be_displayed(String expectedErrorMessage) {
	        String actualErrorMessage = landingpage.getErrorMessage();
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	    }

	    @Given("the user is logged in with email {string} and password {string}")
	    public void the_user_is_logged_in_with_email_and_password(String email, String password) throws IOException {
	        landingpage = LaunchApplication();
	        productCatalogue = landingpage.loginApplication(email, password);
	    }

	    @When("the user adds the product {string} to the cart")
	    public void the_user_adds_the_product_to_the_cart(String productName) throws InterruptedException {
	        productCatalogue.addProductToCart(productName);
	    }

	    @And("the user navigates to the cart page")
	    public void the_user_navigates_to_the_cart_page() {
	        cartPage = productCatalogue.goToCartPage();
	    }

	    @Then("the product {string} should not be present in the cart")
	    public void the_product_should_not_be_present_in_the_cart(String incorrectProductName) {
	        boolean match = cartPage.verifyProductDisplay(incorrectProductName);
	        Assert.assertFalse(match);
	        driver.close();
	    }
	}
