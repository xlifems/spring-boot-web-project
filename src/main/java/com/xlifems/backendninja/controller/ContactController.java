package com.xlifems.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xlifems.backendninja.constant.ViewConstant;
import com.xlifems.backendninja.model.ContactModel;
import com.xlifems.backendninja.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactServiceImp")
	private ContactService contactService;

	@GetMapping("/cancel")
	private String cancel() {
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping("/contactform")
	private String redirectContactform(Model model) {
		model.addAttribute("contactModel", new ContactModel());
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping("/addcontact")
	private String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
		LOG.info("METHOD: addContact() --- PARAMS: " + contactModel.toString());
		if (null != contactService.addContact(contactModel)) {
			model.addAttribute("return", 1);
		} else {
			model.addAttribute("return", 0);
		}

		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	private ModelAndView showcontacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		mav.addObject("contacts", contactService.listAllContact());
		return mav;
	}
}
