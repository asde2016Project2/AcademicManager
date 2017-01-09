package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.persistence.ProfessorDAO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author Francesco Bruno
 */
@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionLocaleResolver localeResolver;

    
    
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String dashboardGet() {
//        model.addAttribute("examForm", new Exam());

        return "professor/dashboard";

    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLogin(@ModelAttribute("userForm") Professor professor, Model model, HttpServletRequest request) {
         return "professor/login";
    }
    
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("userForm") Professor professor, Model model, HttpServletRequest request) {
        ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");
        if (professorDao.retrieveForLogin(professor.getUsername(), professor.getPassword()) == null) {
            model.addAttribute("error", messageSource.getMessage("message.invalid", null, localeResolver.resolveLocale(request)));
            return "professor/login";
        }
        request.getSession().setAttribute("professor", professor.getUsername());
        return "redirect:/professor/dashboard";
    }

}
