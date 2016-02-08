package com.equal.pages;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.equal.annotations.Page;
import com.equal.common.Driver;
import com.equal.pages.base.PageObject;
import com.equal.ui.components.Button;
import com.equal.ui.components.EqualFieldDecorator;
import com.equal.ui.components.TextContainer;

@Page(title = "Home Page Mede")
public class HomePage extends PageObject {

	@FindBy(xpath = "//div[@id='menu']//a[contains(text(),'Home')]")
	private Button homeButton ;
	
	@FindBy(xpath = "//div[@id='element-box']/div[2]/div[@class='adminform']")
	private TextContainer homePage ;
	
	




	public HomePage() {
		PageFactory.initElements(new EqualFieldDecorator(getDriver()), this);
		this.successor = new HashMap<Class<? extends PageObject>, Button>();
	}

	public void makeThisPageCurrent() {
		this.setcurrentPage(this.getClass());
	}

	public boolean isHomePage() {
		homePage.visibilityOfElementWait();
		return homePage.isDisplayed();
	}
	public void clickHomeButton() {
		homeButton.visibilityOfElementWait();
		homeButton.click();
	}

	

	@Override
	public boolean exist() {
		return homePage.isDisplayed();
	}

}
