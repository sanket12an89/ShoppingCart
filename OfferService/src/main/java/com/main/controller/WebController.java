package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.service.IOfferService;

@Controller
public class WebController {

	@Autowired
	IOfferService offerServiceObj;

	@GetMapping("/cart")
	public ModelAndView tokenParametersWithModelAndView() {
		ModelAndView modelAndView = new ModelAndView("cart");
		modelAndView.addObject("message", "Token");
		modelAndView.addObject("customerType", offerServiceObj.getCustomeTypeList());
		return modelAndView;
	}
}
