package com.xlifems.backendninja.service;

import java.util.List;

import com.xlifems.backendninja.entity.Contact;
import com.xlifems.backendninja.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact(ContactModel contactModel);
	public abstract List<ContactModel> listAllContact();
	public abstract Contact findContactById(int id);
	public abstract void removeContact(int id);
	public abstract ContactModel findContactByIdModel(int id);
}
