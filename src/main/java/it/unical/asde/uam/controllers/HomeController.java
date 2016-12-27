package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.ExamDAO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private WebApplicationContext context;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("examForm", new Exam());
        return "home";

    }

    

}
