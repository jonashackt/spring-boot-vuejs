package com.pigdroid.vue;

import java.time.Duration;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FrontendE2EIT {

	private static WebDriver driver;

	@BeforeAll
	static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
	}

	@AfterAll
	static void tearDownClass() {
		driver.close();
	}

	@Test
	@Order(1)
	void testFrontendIsUp() {
		Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
			driver.get("http://localhost/");
			return "frontend".equals(driver.getTitle());
		});
	}

	@Test
	@Order(2)
	void testHelloWorldWorks() {
		driver.get("http://localhost/");
		By tab = By.xpath("/html/body/div/div[1]/a[2]");
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(tab));
		driver.findElement(tab).click();
		By button = By.xpath("/html/body/div/div[2]/button");
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(button));
		By responseText = By.xpath("/html/body/div/div[2]/h4");
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(responseText));
		driver.findElement(button).click();
		Awaitility.await().atMost(Durations.TEN_SECONDS)
		.pollDelay(Durations.ONE_SECOND)
			.until(() -> {
				driver.findElement(button).click();
				String text = driver.findElement(responseText).getText();
				System.out.println(text);
				return "Backend response: Hello from Spring Boot Backend!".equals(text);});
	}

}
