package com.explore.canada.navigation;
import com.explore.canada.bean.SearchInfo;
import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.ShoppingCart;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.configuration.Config;
import com.explore.canada.service.LoginService;
import com.explore.canada.service.RestServiceClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DefaultController {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    Authentication auth;

    @GetMapping("/")
    public String index(@RequestParam(value = "date", required = false)String dateString, Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo;
        Date date = null;
        SearchInfo searchInfo = new SearchInfo();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
        {
            userInfo = new UserInfo();
            userInfo.loadUserByEmailId(auth.getPrincipal().toString(),userInfo);
            model.addAttribute("userInfo", userInfo);
        }

        if (dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);

            } catch (ParseException pe) {
                date = new Date();
            }
        } else {
            date = new Date();
        }

        SearchInfo[] result = searchInfo.loadAllPlaces();
        model.addAttribute("places", result);
        //model.addAttribute("movieBooking", new MovieScreening());
        return "home";
    }

    /*
    @GetMapping("/bookticket")
    public String bookTickets(
            Model model,
            @RequestParam(value = "startdate", required = false) String startDate,
            @RequestParam(value = "enddate", required = false) String endDate,
            @RequestParam(value = "placeid", required = false) String placeId,
            @RequestParam(value = "placename", required = false) String placeName,
            @RequestParam(value = "placeurl", required = false) String placeUrl,
            @RequestParam(value = "actiontype", required = false) String actionType) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo;
        Date journeyStartDate = null;
        Date journeyEndDate = null;
        SearchInfo searchInfo = new SearchInfo();
        SimpleDateFormat targetFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        SearchInfo[] result = searchInfo.loadAllPlaces();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
        {
            RestServiceClient client = new RestServiceClient();
            client.makeParametrizedGetRequest(ServiceEndPoint.GET_USER_SERVICE_URL,auth.getPrincipal().toString());
            userInfo = client.getUserInfo();
            model.addAttribute("userInfo", userInfo);

            if(actionType.equalsIgnoreCase("selectTicket")) {
                if (startDate != null && endDate != null && placeId != null) {
                    try {
                        journeyStartDate = DATE_FORMAT.parse(startDate);
                        journeyEndDate = DATE_FORMAT.parse(endDate);
                        model.addAttribute("startdate", targetFormat.format(journeyStartDate));
                        model.addAttribute("enddate", targetFormat.format(journeyEndDate));
                        model.addAttribute("placename", placeName);
                        model.addAttribute("placeid", placeId);
                        model.addAttribute("placeurl", placeUrl);
                    } catch (ParseException pe) {
                        System.out.println("Invalid date supplied!");
                    }
                    return "bookticket";
                } else {
                    model.addAttribute("places", result);
                    //model.addAttribute("movieBooking", new MovieScreening());
                    return "home";
                }
            }
            else if(actionType.equalsIgnoreCase("checkAvailability")){
                model.addAttribute("startdate",startDate);
                model.addAttribute("enddate",endDate);
                model.addAttribute("placename", placeName);
                model.addAttribute("placeid",placeId);
                model.addAttribute("placeurl",placeUrl);
                model.addAttribute("avilability","available");
                return "bookticket";
            }
            else{
                return "home";
            }

        }
        else {
            return "login";
        }
    }
     */

    @GetMapping("/seats")
    public String bookSeats(@RequestParam(value = "count", required = true)int seatCount, Model model) {
        model.addAttribute("count", seatCount);
        return "seats";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = new UserInfo();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
        {
            userInfo.loadUserByEmailId(auth.getPrincipal().toString(), userInfo);
            model.addAttribute("userInfo", userInfo);
        }
        return "user";
    }

    @GetMapping("/home")
    public String getPlaces(@RequestParam(value = "date", required = false)String dateString, Model model) {
        Date date = null;
        SearchInfo searchInfo = new SearchInfo();
        if (dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);

            } catch (ParseException pe) {
                date = new Date();
            }
        } else {
            date = new Date();
        }
        SearchInfo[] result = (SearchInfo[])model.getAttribute("places");
        if(result == null || result.length == 0) {
            result = searchInfo.loadAllPlaces();
        }
        model.addAttribute("places", result);

        //model.addAttribute("movieBooking", new MovieScreening());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
