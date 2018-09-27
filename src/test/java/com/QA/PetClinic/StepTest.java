package com.QA.PetClinic;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.QA.PetClinic.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StepTest {
	WebDriver driver = null;
	ExtentReports report = new ExtentReports(Constants.REPORT_LOCATION);
	public static ExtentTest test;
	
	private RequestSpecification request;
	private Response response;
	private ValidatableResponse json;
	
	HomePage homePage = null;
	AllVetsPage allVetsPage = null;
	
	@Before
	public void setup() {
		
	}
	
	@Given("^a vet$")
	public void a_vet() throws Throwable {
		test = report.startTest("Testing Petclinic as vet Selenium");
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Desktop/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://10.0.10.10:4200/petclinic/welcome");
		
	}

	@When("^I click on some records$")
	public void i_click_on_some_records() throws Throwable {
	    homePage = PageFactory.initElements(driver, HomePage.class);
	    homePage.clickVetsDrop();
	    homePage.clickAllVets();
	}

	@Then("^I can see the care available for animals$")
	public void i_can_see_the_care_available_for_animals() throws Throwable {
		String element = driver.findElement(By.xpath("/html/body/app-root/app-vet-list/div/div/h2")).getText();
		assertEquals("Veterinarians",element);
		Thread.sleep(2000);
		driver.quit();
	}

	@Given("^an admin$")
	public void an_admin() throws Throwable {
	    System.out.println("Logged in as Admin");
	}

	@When("^I update a record$")
	public void i_update_a_record() throws Throwable {
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/specialties/1";
		request = RestAssured.given();
		
		request.header("Content-Type","application/json");
		request.header("specialtyId",0);
		
		JSONObject specialties = new JSONObject();
		
		specialties.put("id", 1);
		specialties.put("name", "string");
		
		request.body(specialties.toString());
		
		response = request.put();
		
		System.out.println(response.getStatusCode());
	}

	@Then("^the correct details are now shown$")
	public void the_correct_details_are_now_shown() throws Throwable {
	    assertEquals(response.getStatusCode(),204);
	}

	@When("^I delete a animal$")
	public void i_delete_a_animal() throws Throwable {
	    RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/pets/3";
	    request = RestAssured.given();
	    
	    request.header("Content-Type","application/json");
	    response = request.get();
	    
	    given().contentType(ContentType.JSON).when()
	    .delete("http://10.0.10.10:9966/petclinic/pets/3");
	    
	}

	@Then("^emails arent sent to deceased annimals$")
	public void emails_arent_sent_to_deceased_annimals() throws Throwable {
	    assertEquals(response.getStatusCode(),201);
	}

	@When("^I add new records$")
	public void i_add_new_records() throws Throwable {
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/users";
		request = RestAssured.given();
		
		request.header("Content-Type","application/json");
		request.header("Accept", "application/json;charset=UTF-8");
		
		JSONObject user = new JSONObject();
		JSONObject role = new JSONObject();
		JSONArray roles = new JSONArray();
		
		role.put("id", 4);
		role.put("name", "string");
		roles.add(role);
		
		user.put("enabled", true);
		user.put("password", "chicken");
		user.put("roles", roles);
		user.put("username", "McLovin");
		
		request.body(user.toString());
		
		response = request.post("/");
	}

	@Then("^the records are correct$")
	public void the_records_are_correct() throws Throwable {
	    response.getStatusCode();
	    System.out.println(response.getStatusCode());
	    assertEquals(response.getStatusCode(),201);
	}

	@When("^I add new owners to the records$")
	public void i_add_new_owners_to_the_records() throws Throwable {
	    RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/owners";
	    request = RestAssured.given();
	    
	    request.header("Content-Type","application/json");
		request.header("Accept", "application/json;charset=UTF-8");
		
		JSONObject owner = new JSONObject();
		JSONArray pets = new JSONArray();
		JSONObject pet = new JSONObject();
		JSONObject type = new JSONObject();
		JSONArray visits = new JSONArray();
		JSONObject visit = new JSONObject();
		
		visit.put("date", "yyyy/MM/dd");
		visit.put("description", "March");
		visit.put("id", 0);
		visit.put("pet", pet);
		
		visits.add(visit);
		
		type.put("id", 0);
		type.put("name","WOof");
		pet.put("type", type);
		pet.put("birthDate", "2018-09-27T08:50:25.563Z");
		pet.put("id", 12);
		pet.put("name", "Wally");
		pet.put("owner", owner);
		pet.put("visits", visits);
		
		pets.add(pet);
		
		owner.put("address","Spring Lane");
		owner.put("city","Manhattan");
		owner.put("firstName", "Geoff");
		owner.put("id", 12);
		owner.put("lastName", "Jenkins");
		owner.put("pets", pets);
		owner.put("telephone", "00011204032");
		
		request.body(owner.toString());
		response = request.post("/");
		
		System.out.println(response.getStatusCode());
	}

	@Then("^the details show the change$")
	public void the_details_show_the_change() throws Throwable {
	    System.out.println(response.getStatusCode());
	}
	
	@After
	public void tearDown() {
		report.endTest(test);
		report.flush();
	}

}
