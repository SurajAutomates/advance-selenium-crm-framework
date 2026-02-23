package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getProductNameFromCart() {
		String productFromCart = driver.findElement(By.xpath("//div[@class = 'cartSection']//h3")).getText();
		System.out.println(productFromCart);
		return productFromCart;
	}
	
	public CheckoutPage goToCheckOut() {
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		return new CheckoutPage(driver);
	}
}
