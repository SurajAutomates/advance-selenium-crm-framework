package pages;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getFirstProductName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait until at least one product card button is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='card'])[1]//button")));
		List<WebElement> Products = driver.findElements(By.xpath("(//div[@class='card'])"));
		String firstProduct = Products.get(0).findElement(By.xpath(".//h5//b")).getText();
		return firstProduct;
	}

	public void addFirstProductToCart() {
		driver.findElement(By.xpath("(//div[@class='card-body'])[1]//button[2]")).click();
	}

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement notification_msg = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Product Added To Cart')]")));

		// Validate success message
		String msg = notification_msg.getText();
		return msg;
	}
	
	public void addProductByName(String productName) {
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

		for (WebElement product : products) {
		    String name = product.findElement(By.cssSelector("b")).getText();

		    if (name.equalsIgnoreCase(productName)) {
		        product.findElement(By.cssSelector("button:nth-of-type(2)")).click();
		        break;
		    }
		}
	}
	public CartPage goToCart() {
		driver.findElement(By.xpath("(//button[@class = 'btn btn-custom'])[3]")).click();
		return new CartPage(driver);
	}
}
