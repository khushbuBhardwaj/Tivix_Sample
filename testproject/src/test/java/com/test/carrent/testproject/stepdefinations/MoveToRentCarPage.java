package com.test.carrent.testproject.stepdefinations;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import com.test.carrent.testproject.utilities.Constants;

import cucumber.api.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoveToRentCarPage {
    private WebDriver webDrive;
    Logger logger=LoggerFactory.getLogger(MoveToRentCarPage.class);
    
	@Given("Open Chrome and launch the application")
	public void open_Chrome_and_launch_the_application() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\j731\\Desktop\\Khushbu Bhardwaj\\chromedriver.exe");
		webDrive = new ChromeDriver();
		webDrive.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		webDrive.get("http://qalab.pl.tivixlabs.com/");
		webDrive.manage().window().maximize();
	}

	@When("Enter the Mendetory Fields for car serach")
	public void enter_the_Mendetory_Fields_for_car_serach() {
		webDrive.findElement(By.id("pickup")).sendKeys("002020-08-22");
		webDrive.findElement(By.id("dropoff")).sendKeys("002020-08-25");
		webDrive.findElement(By.xpath("//button[@type='submit']")).submit();
		
	}

	@Then("Click on search button and select the first searched car")
	public void click_on_search_buutin_and_select_the_first_searched_car() {
		webDrive.findElement(By.xpath("//a[@class='btn btn-success']")).click();
		webDrive.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
	}
	
	@Given("Enter madetory fields")
	public void enter_madetory_fields() {
	   logger.info("Enter madetory fields");
	}

	@When("Enter the fields for car serach {string} {string} {string} {string}")
	public void enter_the_fields_for_car_serach(String name, String lastName, String cardNumber, String email) {
		logger.info("Enter the fields for car serach");

		webDrive.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(name);
		webDrive.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys(lastName);
		webDrive.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardNumber);
		webDrive.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
	}

	@Then("Click on rent button and check the validations {string}")
	public void click_on_rent_button_and_check_the_validations(String errorType) {
		webDrive.findElement(By.xpath("//*[@id=\"rent_form\"]/button")).click();
		String shouldBeMessage="";
	
		switch(errorType) {
		case "valid":{
			//this element should not be visible after valid data entry
			boolean exceptionThown=false;
			try {
			  webDrive.findElements(By.xpath("//*[@id=\"rent_form\"]/div[1]/label"));
			}catch (Exception e) {
				exceptionThown=true;
				logger.error("This is a valid scenario for reting a car: Expected behaviour");
			}
			assertEquals(true, exceptionThown);
			break;
		}
		case "name":{
			shouldBeMessage=webDrive.findElement(By.xpath("//*[text()='Name is required']")).getText();
			assertEquals(Constants.MISSING_NAME, shouldBeMessage);
			break;
		}
		case "lastName":{
			shouldBeMessage=webDrive.findElement(By.xpath("//*[text()='Last name is required']")).getText();
			assertEquals(Constants.MISSING_LAST_NAME, shouldBeMessage);
			break;
		}
		case "email":{
			shouldBeMessage=webDrive.findElement(By.xpath("//*[text()='Email is required']")).getText();
			assertEquals(Constants.MISSING_EMAIL, shouldBeMessage);
			break;
		}
		case "invalidEmail":{
			shouldBeMessage=webDrive.findElement(By.xpath("//*[text()='Please provide valid email']")).getText();
			assertEquals(Constants.INVALID_CARD_NUMBER, shouldBeMessage);
			break;
		}
		case "cardNumber":{
			shouldBeMessage=webDrive.findElement(By.xpath("//*[text()='Card number is required']")).getText();
			assertEquals(Constants.MISSING_CARD_NUMER, shouldBeMessage);
			break;
		}
		default:break;
		}
	}
	
	@After
	public void tearDown(){
		webDrive.quit();
	}
	
}
