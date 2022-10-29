package com.gft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LayoutController 
{

@RequestMapping	
public ModelAndView exibeLayout() {
	ModelAndView mv = new ModelAndView("layout.html");
	return mv;
}

}
