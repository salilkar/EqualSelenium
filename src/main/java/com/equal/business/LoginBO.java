package com.equal.business;

import com.equal.dto.UserDTO;
import com.equal.pages.LoginPage;
import com.equal.pages.base.PageFactory;

public class LoginBO {
	public void login(UserDTO user){
		LoginPage login = (LoginPage) PageFactory.init(LoginPage.class);
		login.setUserName(user.getUserName());
		login.setPassword(user.getPassword());
		login.clickLogin();
	}
}
