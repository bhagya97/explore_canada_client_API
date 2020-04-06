package com.explore.canada.microservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.explore.canada.beans.UserInfo;

@SpringBootTest
@SuppressWarnings("deprecation")
public class USerTest {
	
	@Test
	public void setIDTest()
	{
		UserInfo u = new UserInfo();
		u.setUserId("10");
		Assert.isTrue("10" == u.getUserId());
	}
	
	@Test
	public void getIDTest()
	{
		UserInfo u = new UserInfo();
		u.setUserId("10");
		Assert.isTrue("10" == u.getUserId());
	}
	
	
	@Test
	public void setFirstNameTest()
	{
		UserInfo u = new UserInfo();
		u.setUserFirstName("Keerthi");
		Assert.isTrue(u.getUserFirstName().equals("Keerthi"));
	}
	
	@Test
	public void getFirstNameTest()
	{
		UserInfo u = new UserInfo();
		u.setUserFirstName("Keerthi");
		Assert.isTrue(u.getUserFirstName().equals("Keerthi"));
	}

	@Test
	public void setLastNameTest()
	{
		UserInfo u = new UserInfo();
		u.setUserLastName("Gowda");
		Assert.isTrue(u.getUserLastName().equals("Gowda"));
	}

	@Test
	public void getLastNameTest()
	{
		UserInfo u = new UserInfo();
		u.setUserLastName("Gowda");
		Assert.isTrue(u.getUserLastName().equals("Gowda"));
	}
	
	@Test
	public void setEmailTest()
	{
		UserInfo u = new UserInfo();
		u.setUserEmail("keerthigowda.g18@gmail.com");
		Assert.isTrue(u.getUserEmail().equals("keerthigowda.g18@gmail.com"));
	}
	
	@Test
	public void getEmailTest()
	{
		UserInfo u = new UserInfo();
		u.setUserEmail("keerthigowda.g18@gmail.com");
		Assert.isTrue(u.getUserEmail().equals("keerthigowda.g18@gmail.com"));
	}
	
	@Test
	public void isFirstNameValidTest()
	{
		Assert.isTrue(UserInfo.isFirstNameValid("keerthi"));
		Assert.isTrue(!UserInfo.isFirstNameValid(null));
		Assert.isTrue(!UserInfo.isFirstNameValid(""));
	}
	
	@Test
	public void isLastNameValidTest()
	{
		Assert.isTrue(UserInfo.isLastNameValid("gowda"));
		Assert.isTrue(!UserInfo.isLastNameValid(null));
		Assert.isTrue(!UserInfo.isLastNameValid(""));
	}
	
	@Test
	public void isEmailValidTest()
	{
		Assert.isTrue(UserInfo.isEmailValid("keerthigowda.g18@gmail.com"));
		Assert.isTrue(!UserInfo.isEmailValid(null));
		Assert.isTrue(!UserInfo.isEmailValid(""));
		Assert.isTrue(!UserInfo.isEmailValid("@gmail.com"));
		Assert.isTrue(!UserInfo.isEmailValid("keerthigowda.g18@"));
	}

}
