package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Francesco Bruno
 */
@Controller
@RequestMapping("/professor")
public class ProfessorController extends BaseController{  

    
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad( HttpServletRequest request) {
        
        if(!SessionHelper.isProfessor(request.getSession())){
            return "redirect:/";
        }
                                
        return "professor/dashboard";
    }
    

}
