package com.explore.canada.booking;

import com.explore.canada.bean.*;
import com.explore.canada.configuration.Config;
import com.explore.canada.service.RestServiceClient;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {
    RestServiceClient client = new RestServiceClient();

    /*
    @GetMapping("/payment")
    public String displayFindbus(Model model)
    {
        return "payment";
    }
     */

    @PostMapping("/payment")
    public String processPayment(Model model,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "card_no") String cardNumber,
                                 @RequestParam(name = "expiry_month") String expiryMonth,
                                 @RequestParam(name = "cvv") String cvv,
                                 @RequestParam(name = "expiry_year") String expiryYear)
    {

        String url = ServiceEndPoint.PAYMENT_USER_SERVICE_URL;
        ShoppingCart shoppingCart = Config.getInstance().getShoppingCart();
        //setting user details
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = new UserInfo();
        String userEmail = auth.getPrincipal().toString();
        shoppingCart.setUserInfo(userInfo.loadUserByEmailId(userEmail, userInfo));

        TicketInfo bookingInfo = null;
        Card card = new Card();
        card.setCardHolderName(name);
        card.setCardNumber(cardNumber);
        card.setCvvNumber(Integer.parseInt(cvv));
        card.setMonthExpiry(Integer.parseInt(expiryMonth));
        card.setYearExpiry(Integer.parseInt(expiryYear));
        shoppingCart.setCardInfo(card);
        client.makePostRequest(url,shoppingCart);
        bookingInfo = client.getTicketInfo();

        if (bookingInfo != null)
        {
            // This is lame, I will improve this with auto-signin for M2.
            model.addAttribute("ticketInfo",bookingInfo);
            //Config.getInstance().setTicketInfo(bookingInfo);
            return "displayticket";
        }
        else
        {
            // Something wrong with the input data.
            return "payment";
            //m.addObject(ErrorMessage.ERROR_LABEL, u.getFailureResults());
        }
    }

    @GetMapping("/displayticket")
    public String processdisplayticket(Model model, @RequestParam(value = "date", required = false)String dateString) {
        TicketInfo ticketInfo = (TicketInfo) model.getAttribute("ticketInfo");
        //TicketInfo ticketInfo = Config.getInstance().getTicketInfo();
        if (ticketInfo != null)
        {
            // This is lame, I will improve this with auto-signin for M2.
            model.addAttribute("ticketInfo",ticketInfo);
            return "displayticket";
        }
        else
        {
            // Something wrong with the input data.
            return "payment";
            //m.addObject(ErrorMessage.ERROR_LABEL, u.getFailureResults());
        }
    }
}
