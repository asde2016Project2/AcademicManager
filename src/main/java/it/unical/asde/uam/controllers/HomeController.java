package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.Helper.UserProfileHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.LoginFormDTO;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.persistence.ProfessorDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{  

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
//        model.addAttribute("examForm", new Exam());
        return "home";

    }
     
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLogin(@ModelAttribute("loginForm") LoginFormDTO loginForm, Model model, HttpServletRequest request) {
        model.addAttribute("loginForm",new LoginFormDTO()); 
        return "login";
    }
    
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("loginForm") LoginFormDTO loginForm, BindingResult result, HttpServletRequest request, Model model) {                
                        
        /*                
        if(result.hasErrors()){  
           model.addAttribute("loginForm",loginForm);
           return "login";
        }
        */
        
        System.out.println("Username: "+loginForm.getUsername());
        System.out.println("Password: "+loginForm.getPassword());
        
        String viewToRender = "login";
        
        switch(loginForm.getProfileType()){
            case UserProfileHelper._ADMINISTRATOR:
                viewToRender = loginAdministrator(loginForm,model,request); 
                break;
            case UserProfileHelper._PROFESSOR:     
                viewToRender = loginProfessor(loginForm,model,request);                
                break;
            case UserProfileHelper._STUDENT:     
                viewToRender = loginStudent(loginForm,model,request); 
                break;                
            default:
                model.addAttribute("loginForm",loginForm);
                viewToRender = "login";
                break;
        }
       
        return viewToRender;       
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request){
        SessionHelper.cleanSession(request.getSession());
        return "redirect:login";
    }
    
    
    private String loginProfessor(LoginFormDTO loginForm, Model model, HttpServletRequest request){
        ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");
        
        Professor professor = professorDao.retrieveForLogin(loginForm.getUsername(), loginForm.getPassword());        
        if (professor  == null) {
            model.addAttribute("error", messageSource.getMessage("message.invalid", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "login";
        }
        
       SessionHelper.setUserProfessorLogged(professor, request.getSession()); 
       return "redirect:/professor/dashboard";
    }
    
    private String loginAdministrator(LoginFormDTO loginForm, Model model, HttpServletRequest request){
        return "login";
    }
    
    private String loginStudent(LoginFormDTO loginForm, Model model, HttpServletRequest request){
        return "login";
    }
    
}
