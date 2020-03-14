package com.explore.canada.security;
import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.service.RestServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController
{
	private final String EMAIL = "email";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String DATE_OF_BIRTH = "dateOfBirth";
	
	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
   public ModelAndView processSignup(
   	@RequestParam(name = EMAIL) String email,
   	@RequestParam(name = PASSWORD) String password,
   	@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
   	@RequestParam(name = FIRST_NAME) String firstName,
   	@RequestParam(name = LAST_NAME) String lastName,
   	@RequestParam(name = DATE_OF_BIRTH) String dateOfBirth)
	{
		UserInfo u;
		RestServiceClient client = null;
		if (UserInfo.isEmailValid(email) &&
				UserInfo.isFirstNameValid(firstName) &&
				UserInfo.isLastNameValid(lastName) &&
			 	password.equals(passwordConfirm))
		{
			u = new UserInfo();
			u.setUserEmail(email);
			u.setUserPassword(password);
			u.setUserFirstName(firstName);
			u.setUserLastName(lastName);
			u.setUserEmail(email);
			client = new RestServiceClient();
			client.makePostRequest(ServiceEndPoint.REGISTER_USER_SERVICE_URL,u);
		}
		ModelAndView m;
		if (client.getUserInfo() != null)
		{
			// This is lame, I will improve this with auto-signin for M2.
			m = new ModelAndView("login");
		}
		else
		{
			// Something wrong with the input data.
			m = new ModelAndView("signup");
			//m.addObject(ErrorMessage.ERROR_LABEL, u.getFailureResults());
		}
		return m;
	}
}