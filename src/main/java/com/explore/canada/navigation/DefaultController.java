package com.explore.canada.navigation;
import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.service.RestServiceClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo[] userInfo;
        if (authentication.isAuthenticated())
        {
            RestServiceClient client = new RestServiceClient();
            client.makeParametrizedGetRequest(ServiceEndPoint.GET_USER_SERVICE_URL,authentication.getPrincipal().toString());
            userInfo = client.getUserInfoList();
            model.addAttribute("userInfo", userInfo[0]);
        }
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo[] userInfo;
        if (authentication.isAuthenticated())
        {
            RestServiceClient client = new RestServiceClient();
            client.makeParametrizedGetRequest(ServiceEndPoint.GET_USER_SERVICE_URL,authentication.getPrincipal().toString());
            userInfo = client.getUserInfoList();
            model.addAttribute("userInfo", userInfo[0]);
        }
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
