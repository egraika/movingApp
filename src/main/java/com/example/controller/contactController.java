package com.example.controller;

import java.io.FileReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ContactEntity;
import com.example.model.MoveEntity;
import com.example.view.Contact;
import com.example.view.Employee;
import com.example.view.Move;
import com.google.gson.Gson;

@Controller
public class contactController {
	
	@Autowired
	private com.example.dao.ContactDao ContactDao;

	
	@RequestMapping(value = "/newContact",method = RequestMethod.POST)
	@ResponseBody
	public Boolean contact(@RequestBody Contact contact) {
		
		ContactEntity ContactEntity = contactToContactEntity(contact);
		Date date = new Date();
		ContactEntity.setDate(date);
		ContactEntity.setStatus("open");
		ContactDao.save(ContactEntity);
		return true;
	}
	
	@RequestMapping(value = "/getContacts",method = RequestMethod.GET)
	@ResponseBody
	public List<Contact> getContact() {
		
		List<ContactEntity> ContactEntities = ContactDao.findAll();
		List<Contact> contacts = contactEntitiesToContact(ContactEntities);
		return contacts;
	}


	private List<Contact> contactEntitiesToContact(List<ContactEntity> contactEntities) {
		
		List<Contact> contacts = new ArrayList<Contact>();
		for(int i = 0; i < contactEntities.size(); i++) {
			Contact contact = new Contact();
			contact.setEmail(contactEntities.get(i).getEmail());
			contact.setID(contactEntities.get(i).getID());
			contact.setMessage(contactEntities.get(i).getMessage());
			contact.setFullName(contactEntities.get(i).getFullName());
			contact.setStatus(contactEntities.get(i).getStatus());
			contact.setDate(contactEntities.get(i).getDate());
			contacts.add(contact);
		}
		return contacts;
	}

	private ContactEntity contactToContactEntity(Contact contact) {
		
		ContactEntity ContactEntity = new ContactEntity();
		ContactEntity.setEmail(contact.getEmail());
		ContactEntity.setID(contact.getID());
		ContactEntity.setMessage(contact.getMessage());
		ContactEntity.setFullName(contact.getFullName());
		ContactEntity.setDate(contact.getDate());
		ContactEntity.setStatus(contact.getStatus());
		return ContactEntity;
	}
}

