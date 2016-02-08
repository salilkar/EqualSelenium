package com.equal.business;

import com.equal.pages.HomePage;
import com.equal.pages.base.PageFactory;

public class HomePageBO {

	
	public boolean isHomePage() {
		HomePage home = (HomePage) PageFactory.init(HomePage.class);
		return home.exist();
	}
	

	public void navigateToHomePage() {
		HomePage home = (HomePage) PageFactory.init(HomePage.class);
		home.clickHomeButton();
	}
}
