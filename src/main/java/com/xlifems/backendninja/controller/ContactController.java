package com.xlifems.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") esta anotacion nos permite securizar los metodos a nivel del 
	 * rol con el que el usuario esta logueado, tambien se puede utilizar a nivel global esdecir en la clase
	 * @param id
	 * @param model
	 * @return
	 */
	@PreAuthorize("permitAll()")
	@GetMapping("/contactform")
	public String redirectContactform(@RequestParam(name = "id", required = false) int id, Model model) {
		ContactModel contactModel = new ContactModel();
		if (id != 0 ) {
			contactModel = contactService.findContactByIdModel(id);
		}
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
		LOG.info("METHOD: addContact() --- PARAMS: " + contactModel.toString());
		if (null != contactService.addContact(contactModel)) {
			model.addAttribute("return", 1);
		} else {
			model.addAttribute("return", 0);
		}
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showcontacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		
		//Recuperamos el usuario que se encuentra eb sesion y lo enviamos a la vista, el cual debe ser obtenido de las clases de spring security		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAllContact());
		return mav;
	}
	
	/**
	 * Removes the contact.
	 *
	 * @param id the id
	 * @return the model and view
	 */
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
		contactService.removeContact(id);
		return showcontacts();
	}
}
