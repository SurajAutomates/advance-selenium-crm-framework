package pages;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	WebDriver driver ;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectCountry(String Country) {
		driver.findElement(By.xpath("//input[@placeholder = 'Select Country']")).sendKeys(Country);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//button")));
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().toLowerCase().contains(Country.toLowerCase())) {
				suggestion.click();
				break;
			}
		}
	}
	
	public void placeOrder() {
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
	}
	
	public String getConfirmationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement notification_msg = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Order Placed Successfully')]")));

		// Validate success message
		String msg = notification_msg.getText();
		return msg;
	}
}
