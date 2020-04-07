package com.explore.canada.booking;

import com.explore.canada.bean.*;
import com.explore.canada.configuration.Config;
import com.explore.canada.service.RestServiceClient;
import com.itextpdf.text.DocumentException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class TicketController {
    RestServiceClient client = new RestServiceClient();
    Authentication auth;
    private static final String SOURCE = "source";
    private static final String DESTINATION = "destination";

    /*
    @GetMapping("/findbus")
    public String displayFindbus(Model model)
    {
        return "findbus";
    }
     */

    @GetMapping("/findbus")
    public String findBus(
            Model model,
            @RequestParam(value = "placeid", required = false) String placeId,
            @RequestParam(value = "placename", required = false) String placeName,
            @RequestParam(value = "placeLocation", required = false) String placeLocation,
            @RequestParam(value = "placeurl", required = false) String placeUrl,
            @RequestParam(value = "actiontype", required = false) String actionType,
            @RequestParam(value = "source", required = false) String sourceLocation) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo;
        TicketInfo[] ticketInfos;
        SearchInfo searchInfo = new SearchInfo();
        SearchInfo[] result = searchInfo.loadAllPlaces();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
        {
            RestServiceClient client = new RestServiceClient();
            client.makeParametrizedGetRequest(ServiceEndPoint.GET_USER_SERVICE_URL,auth.getPrincipal().toString());
            userInfo = client.getUserInfo();
            model.addAttribute("userInfo", userInfo);

            if(actionType.equalsIgnoreCase("checkAvailability")){
                Map<String,String> parameters = new HashMap<>();
                String avilability = null;
                parameters.put(SOURCE,sourceLocation);
                parameters.put(DESTINATION,placeLocation);
                client.makeParametrizedBusGetRequest(ServiceEndPoint.GET_FIND_BUS,parameters);
                ticketInfos = client.getTicketInfos();
                if(ticketInfos != null && ticketInfos.length == 0){
                    avilability = "available";
                }
                model.addAttribute("ticketInfos",ticketInfos);
                model.addAttribute("placename", placeName);
                model.addAttribute("placeLocation", placeLocation);
                model.addAttribute("placeid",placeId);
                model.addAttribute("placeurl",placeUrl);
                model.addAttribute("avilability",avilability);
                return "findbus";
            }
            else if(actionType.equalsIgnoreCase("selectPlace")) {
                model.addAttribute("placename", placeName);
                model.addAttribute("placeLocation", placeLocation);
                model.addAttribute("placeid",placeId);
                model.addAttribute("placeurl",placeUrl);
                return "findbus";
            }
            else{
                return "findbus";
            }
        }
        else {
            return "login";
        }
    }

    @GetMapping("/bookticket")
    public String processbookticket(Model model,
            @RequestParam(name = "busid", required = false) String busId,
            @RequestParam(name = "children", required = false) String noOfChildren,
            @RequestParam(name = "adults", required = false) String noOfAdults)
    {

        RestServiceClient client = null;
        client = new RestServiceClient();
        String url = ServiceEndPoint.GET_PAYMENT_BUS;
        String find_bus_by_id_url = ServiceEndPoint.GET_BUS_BY_ID;
        ShoppingCart shoppingCart = Config.getInstance().getShoppingCart();
        TicketInfo ticketInfo = null;
        Map<String,String> parameters = new HashMap<>();
        parameters.put("busid",busId);
        client.makeParametrizedBusGetRequestById(find_bus_by_id_url,parameters);
        ticketInfo = client.getTicketInfo();
        //calculate total fare
        if(null != ticketInfo) {
            ticketInfo.calculateTotalFare(
                    Integer.parseInt(noOfAdults),
                    Integer.parseInt(noOfChildren));
            shoppingCart.setBookingInfo(ticketInfo);
        }

        if (ticketInfo != null)
        {
            // This is lame, I will improve this with auto-signin for M2.
            return "payment";
        }
        else
        {
            // Something wrong with the input data.
            return "bookticket";
            //m.addObject(ErrorMessage.ERROR_LABEL, u.getFailureResults());
        }
    }



    /*
    @GetMapping("/displayticket")
    public String displayticket(Model model)
    {
        return "displayticket";
    }
     */
}
