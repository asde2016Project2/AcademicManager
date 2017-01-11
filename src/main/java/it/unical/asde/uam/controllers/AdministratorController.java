package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    private WebApplicationContext context;

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad( HttpServletRequest request) {
        
        if(!SessionHelper.isAdmin(request.getSession())){
            return "redirect:/";
        }
                                
        return "admin/dashboard";
    }
}
