package com.explore.canada.search;

import com.explore.canada.bean.SearchInfo;
import com.explore.canada.bean.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class SearchController {
    Authentication auth;

    @GetMapping("/search")
    public String searchPlace(@RequestParam(value = "name", required = false)String name, Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        Date date = null;
        SearchInfo[] result;
        SearchInfo searchInfo = new SearchInfo();
        UserInfo userInfo = new UserInfo();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
        {
            userInfo.loadUserByEmailId(auth.getPrincipal().toString(), userInfo);
            model.addAttribute("userInfo", userInfo);
        }

        if(null == name || name.isEmpty() || name.trim().isEmpty()) {
            result = searchInfo.loadAllPlaces();
        }
        else {
            result = searchInfo.loadSearchByCategory(name);
        }
        model.addAttribute("places", result);
        return "home";
    }
}
