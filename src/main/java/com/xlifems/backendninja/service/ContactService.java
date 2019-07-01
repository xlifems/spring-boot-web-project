package com.xlifems.backendninja.service;

import java.util.List;

import com.xlifems.backendninja.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact(ContactModel contactModel);
	public abstract List<ContactModel> listAllContact();
}
