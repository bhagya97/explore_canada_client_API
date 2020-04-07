package com.explore.canada.security;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.configuration.Config;
import com.explore.canada.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController
{
	private static final String SECRET = "secret";
	private int noOfAttempts = 0;

	@GetMapping("/login")
	public String login(Model model)
	{
		return "login.html";
	}

	@GetMapping("/login-error")
	public String loginError(Model model)
	{
		return "login-error.html";
	}

	@GetMapping("/onetimepassword")
	public String oneTimePassword(Model model)
	{
		return "onetimepassword";
	}

	@PostMapping("/onetimepassword")
	public String oneTimePasswordVerification(Model model, @RequestParam(value = SECRET, required = false)String oneTimeOTP){
		UserInfo userInfo;
		List<String> errorMessages = new ArrayList<>();
		UserInfo userAuth = Config.getInstance().getUserAuth();
		LoginService loginService = Config.getInstance().getLoginService();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.isAuthenticated()){
			auth = loginService.setUserAuthentication(userAuth.getUserEmail(),userAuth.getUserPassword());
		}
		model.addAttribute("userEmail",maskString(userAuth.getUserEmail()));
		userInfo = loginService.validateOneTimePassword(userAuth.getUserEmail(), oneTimeOTP);
		if (null == userInfo) {
			noOfAttempts++;
			if (noOfAttempts < 3) {
				errorMessages.add(
						"Invalid one time password.\n"+
						" " + (3 - noOfAttempts) + " remaining attempts.");
				model.addAttribute("errorMessage", errorMessages);
				model.addAttribute("level","ERROR");
				return "onetimepassword";
			} else {
				return "redirect:/login?logout";
			}
		} else {
			if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
			{
				userInfo = new UserInfo();
				userInfo.loadUserByEmailId(userAuth.getUserEmail(), userInfo);
				model.addAttribute("userInfo", userInfo);
			}
			return "user";
		}
	}

	private String maskString(String rawText){
		int length = rawText.length();
		double limit = 0.50;
		int characterToMask = (int)(length * limit);
		rawText = rawText.substring(0,(length - characterToMask) + 1);
		for(int i=0;i<characterToMask;i++) {
			rawText = rawText + "*";
		}
		return rawText;
	}
}