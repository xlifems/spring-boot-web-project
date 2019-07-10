package com.xlifems.backendninja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlifems.backendninja.component.ContactConverter;
import com.xlifems.backendninja.entity.Contact;
import com.xlifems.backendninja.model.ContactModel;
import com.xlifems.backendninja.repository.ContactRepository;

@RestController
@RequestMapping("/api")
public class RestContactController {

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@GetMapping("/contacts")
	public  List<ContactModel> getAllContacts(){	
	
		List<ContactModel> contactModels = new ArrayList<ContactModel>();
		List<Contact> contacts = contactRepository.findAll();
		
		for (Contact contact : contacts) {
			contactModels.add( contactConverter.convertToContact2ContactModel(contact));
		}
		return  contactModels;
				
	}
}
