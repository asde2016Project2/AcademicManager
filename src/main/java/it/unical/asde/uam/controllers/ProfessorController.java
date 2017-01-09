package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Student;
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
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private WebApplicationContext context;

    @RequestMapping(value="dashboard",method = RequestMethod.GET)
    public String dashboardGet() {
//        model.addAttribute("examForm", new Exam());
        return "professor/dashboard";

    }
    
    

}
