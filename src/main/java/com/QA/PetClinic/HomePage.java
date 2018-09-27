package com.QA.PetClinic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]/a")
	private WebElement ownersDrop;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[1]/a")
	private WebElement allOwners;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[2]/a")
	private WebElement addNewOwner;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[3]/a")
	private WebElement vetsDrop;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[3]/ul/li[1]/a")
	private WebElement allVets;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[3]/ul/li[2]/a")
	private WebElement addNewVet;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[4]/a")
	private WebElement petTypes;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[5]/a")
	private WebElement specialities;
	
	public void clickOwners() {
		ownersDrop.click();
	}
	
	public void clickAllOwners() {
		allOwners.click();
	}
	
	public void clickAddNewOwner() {
		addNewOwner.click();
	}
	
	public void clickVetsDrop() {
		vetsDrop.click();
	}
	
	public void clickAllVets() {
		allVets.click();
	}
}
