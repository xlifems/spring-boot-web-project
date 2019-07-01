package com.xlifems.backendninja.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xlifems.backendninja.component.ContactConverter;
import com.xlifems.backendninja.entity.Contact;
import com.xlifems.backendninja.model.ContactModel;
import com.xlifems.backendninja.repository.ContactRepository;
import com.xlifems.backendninja.service.ContactService;

@Service("contactServiceImp")
public class ContactServiceImp implements ContactService{
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertToContactModel2Contact(contactModel));
		return contactConverter.convertToContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContact() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactModels = new ArrayList<ContactModel>();
		for (Contact contact : contacts) {
			contactModels.add(contactConverter.convertToContact2ContactModel(contact));
		}
		return contactModels;
	}
	
	

}
