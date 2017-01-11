package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController{  

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad( HttpServletRequest request) {
        
        if(!SessionHelper.isAdmin(request.getSession())){
            return "redirect:/";
        }
                                
        return "admin/dashboard";
    }
}
