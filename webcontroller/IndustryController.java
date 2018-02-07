package com.sp17.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp17.Sp17Application;

@Controller
public class IndustryController {
		
	@RequestMapping(value="/industrypage", method = RequestMethod.GET)
	 public String formlist( Model model){
			
		 model.addAttribute("industryFields", Sp17Application.industryFieldDatabase.listIndustryFields());
		 return "industrypage";
	 }
	
	@RequestMapping(value="/tips", method = RequestMethod.GET)
	public String tips(){
		return "tips";
	}
	
	

}
		
