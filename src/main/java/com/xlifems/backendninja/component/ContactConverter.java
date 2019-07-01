package com.xlifems.backendninja.component;

import org.springframework.stereotype.Component;

import com.xlifems.backendninja.entity.Contact;
import com.xlifems.backendninja.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public Contact convertToContactModel2Contact(ContactModel contactModel) {
		Contact contact = new Contact();		
		contact.setId(contactModel.getId());
		contact.setFirtname(contactModel.getFirtname());
		contact.setLastname(contactModel.getLastname());
		contact.setCity(contactModel.getCity());
		contact.setTelephone(contactModel.getTelephone());
		return contact;
	}
	
	public ContactModel convertToContact2ContactModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirtname(contact.getFirtname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setCity(contact.getCity());
		contactModel.setTelephone(contact.getTelephone());
		return contactModel;
	}

}
