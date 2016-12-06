package it.unical.asde.uam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("welcomeMessage", "Welcome guest (prof/student/admin)");
		
		return "home";
		
	}

}
