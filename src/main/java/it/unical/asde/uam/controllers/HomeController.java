package it.unical.asde.uam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.helper.UserProfileHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.dto.LoginFormDTO;
import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{  
  
    
    /**
     * 
     * This is the main route like  http://localhost:8080/WEBSITE_URL
     * The view login.jsp contains the home page in witch there is login form
     * 
     * 
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showLogin(@ModelAttribute("loginForm") LoginFormDTO loginForm, Model model, HttpServletRequest request) {
        model.addAttribute("pageTitle","Login");     
        model.addAttribute("loginForm",new LoginFormDTO());          
        return "home/login";
    }
    
    
    /**
     * 
     * This is the route WEBSITE_URL/login
     * It's invoked only in post and handle the login phase
     * If something goes wrong it return back to home page for login again
     * or for showing errors
     * 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("loginForm") LoginFormDTO loginForm, BindingResult result, HttpServletRequest request, Model model) {                            
        
                
        String viewToRender = "home/login";
        
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
                model.addAttribute("pageTitle","Login"); 
                model.addAttribute("loginForm",loginForm);
                viewToRender = "home/login";
                break;
        }
       
        return viewToRender;       
    }

    
    /**
     *  
     * This is the route  WEBSITE_URL/logout
     * 
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request){
        SessionHelper.cleanSession(request.getSession());
        return "redirect:/";
    }
    
    
    
    
    /********** PRIVATE METHODS ********/
    
    
    private String loginProfessor(LoginFormDTO loginForm, Model model, HttpServletRequest request){
        ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");
        
        Professor professor = professorDao.retrieveForLogin(loginForm.getUsername(), loginForm.getPassword());        
        if (professor  == null) {
            model.addAttribute("error", messageSource.getMessage("message.invalid", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "home/login";
        }
        
        if (professor.getAccepted() == Accepted.NOT_ACCEPTED) {
            model.addAttribute("error", messageSource.getMessage("message.not_accepted", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "home/login";
        }
       SessionHelper.setUserProfessorLogged(professor, request.getSession()); 
       return "redirect:/professor/dashboard";
    }
    
    private String loginAdministrator(LoginFormDTO loginForm, Model model, HttpServletRequest request){
        
        
        AdministratorDAO administratorDao = (AdministratorDAO) context.getBean("administratorDAO");
        
        Administrator administrator = administratorDao.retrieveAdminForLogin(loginForm.getUsername(), loginForm.getPassword());        
        if (administrator  == null) {
            model.addAttribute("error", messageSource.getMessage("message.invalid", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "home/login";
        }
        
        if (administrator.getAccepted() == Accepted.NOT_ACCEPTED) {
            model.addAttribute("error", messageSource.getMessage("message.not_accepted", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "home/login";
        }
                
       SessionHelper.setUserAdministratorLogged(administrator, request.getSession()); 
       return "redirect:/admin/dashboard";
       
    }
    
    private String loginStudent(LoginFormDTO loginForm, Model model, HttpServletRequest request){
                   
        
        StudentDAO studentDao = (StudentDAO) context.getBean("studentDAO");
        
        Student stud = studentDao.retrieveForLogin(loginForm.getUsername(), loginForm.getPassword());        
        if (stud  == null) {
            model.addAttribute("error", messageSource.getMessage("message.invalid", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "home/login";
        }
        
        if (stud.getAccepted() == Accepted.NOT_ACCEPTED) {
            model.addAttribute("error", messageSource.getMessage("message.not_accepted", null, localeResolver.resolveLocale(request)));
            SessionHelper.cleanSession(request.getSession());
            return "home/login";
        }


       SessionHelper.setUserStudentLogged(stud, request.getSession());
       return "redirect:/student/dashboard";
         
       
      
    }
    
}
