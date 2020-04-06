package com.explore.canada.microservice;

import com.explore.canada.beans.UserInfo;
import com.explore.canada.dao.IUserDAO;
import com.explore.canada.dao.IUserLogin;
import com.explore.canada.security.IPasswordEncryption;

public class UserDBMock implements IUserLogin {
	public void loadUserByID(UserInfo user)
	{
		user.setUserEmail("keerthigowda.g18@gmail.com");
		user.setUserFirstName("Keerthi");
		user.setUserLastName("Gowda");
		user.setUserId("1");
	}

	public void loadUserByBannerID(String emailID, UserInfo user)
	{
		user.setUserEmail("keerthigowda.g18@gmail.com");
		user.setUserFirstName("Keerthi");
		user.setUserLastName("Gowda");
		user.setUserId("1");
	}

	@Override
	public boolean authenticate(String userEmail, String userPassword, IPasswordEncryption encryption, IUserDAO userDAO,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
