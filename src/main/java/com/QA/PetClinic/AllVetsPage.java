package com.QA.PetClinic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllVetsPage {
	@FindBy(xpath = "/html/body/app-root/app-vet-list/div/div/h2")
	private WebElement title;
	
	public String getTitle() {
		return title.getText();
	}
}
