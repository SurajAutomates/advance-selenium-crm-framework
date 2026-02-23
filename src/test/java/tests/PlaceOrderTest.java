package tests;


import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import genericUtilitis.ExcelUtility;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

@Listeners(listeners.TestListener.class)
public class PlaceOrderTest extends BaseClass {

	@Test(dataProvider = "getData")
	public void placeOrderTest(String email, String password,String productName , String country) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email,password);
		
		
		// Validate page title after login
		Assert.assertTrue(driver.getTitle().toLowerCase().contains("let's shop"));
//		--------------------------------------------------------------------------------------------------------------
		ProductPage productPage = new ProductPage(driver);
		
		productPage.addProductByName(productName);

		// Wait for success notification
		
		String msg = productPage.getToastMessage();
		Assert.assertTrue(msg.contains("Product Added To Cart"));

//		--------------------------------------------------------------------------------------------------------------
		CartPage cartPage = productPage.goToCart();
		
		String productFromCart = cartPage.getProductNameFromCart();
		Assert.assertEquals(productName,productFromCart);
		
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		
		checkoutPage.selectCountry(country);
		checkoutPage.placeOrder();
		
		String confirmationMessage = checkoutPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.contains("Order Placed Successfully"));
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		return new ExcelUtility().getExcelData();
	}
}
