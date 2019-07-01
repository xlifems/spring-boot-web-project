package com.xlifems.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlifems.backendninja.constant.ViewConstant;
import com.xlifems.backendninja.model.UserCredentials;

@Controller
public class Logincontroller {
	
	private static final Log LOG = LogFactory.getLog(Logincontroller.class);

	@GetMapping("/")
	public String redirectToLogin() {
		LOG.info("METHOD: redirectToLogin() ");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model, 
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("METHOD: showLoginForm() --- PARAMS:  error: " + error + " logout: "+ logout );
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		model.addAttribute("userCredentials", new UserCredentials());
		LOG.info("Returning to login view " );
		return ViewConstant.LOGIN;
	}

	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name = "userCredentials") UserCredentials userCredentials) {
		LOG.info("METHOD: showLoginForm() --- PARAMS: " + userCredentials.toString() );
		if (userCredentials.getUsername().equals("user") && userCredentials.getPassword().equals("user")) {
			LOG.info("Returning to contacts view " );
			return "redirect:/contacts/showcontacts";
		}
		LOG.info("redirect to login?error view " );
		return "redirect:/login?error";
	}

}
