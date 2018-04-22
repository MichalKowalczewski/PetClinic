package com.petclinic.controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.Transaction;

import com.petclinic.controler.datatable.OwnerDataTable;
import com.petclinic.model.Owner;
import com.util.ApplicationBean;

import lombok.Data;

@ManagedBean(name = "ownerController", eager = true)
@ViewScoped
public @Data class OwnerController {
	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean application;
	
	@ManagedProperty(value = "#{ownerDataTable}")
	private OwnerDataTable ownerDataTable;
	
	private Owner owner = new Owner();
	
	public void save(ActionEvent action) {
		application.session.save(owner);		
		owner = new Owner();
	}
	
	public void modify(ActionEvent action) {
		Transaction transaction = application.session.beginTransaction();
		System.out.println(ownerDataTable.getSelectedOwner().getCity());
		application.session.update(ownerDataTable.getSelectedOwner());	
		transaction.commit();
		owner = new Owner();
	}

}
