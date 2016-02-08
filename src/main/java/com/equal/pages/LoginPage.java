package com.equal.pages;

import java.util.HashMap;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.equal.annotations.Page;
import com.equal.pages.HomePage;
import com.equal.pages.base.PageObject;
import com.equal.ui.components.Button;
import com.equal.ui.components.EqualFieldDecorator;
import com.equal.ui.components.TextField;


@Page(title = "Login Page Amalgam")
public class LoginPage extends PageObject {

	@FindBy(id = "success")
	private Button loginForm;
	
	@FindBy(xpath = "//input[@title='Enter Username']")
	private TextField userName;
	
	@FindBy(xpath = "//input[@type='password']")
	private TextField password;
	
	@FindBy(xpath = "//a[contains(text(),'Log In')]")
	private Button login;
	
	
	public LoginPage() {
		PageFactory.initElements(new EqualFieldDecorator(getDriver()), this);
		this.successor = new HashMap<Class<? extends PageObject>, Button>();
		this.successor.put(HomePage.class, login);
	}
	
	public void setUserName(String userName){
		this.userName.visibilityOfElementWait();
		this.userName.sendText(userName);
	}
	
	public void setPassword(String pass){
		password.visibilityOfElementWait();
		password.sendText(pass);
	}
	
	public void clickLogin(){
		login.visibilityOfElementWait();
		login.click();
	}
	
	@Override
	public boolean exist() {
		return loginForm.isDisplayed();
	}
	public void makeThisPageCurrent() {
		this.setcurrentPage(this.getClass());
	}

}
