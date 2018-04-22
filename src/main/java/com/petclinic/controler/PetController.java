package com.petclinic.controler;

import javax.faces.bean.ViewScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import org.hibernate.Transaction;

import com.petclinic.controler.datatable.OwnerDataTable;
import com.petclinic.controler.datatable.PetDataTable;
import com.petclinic.controler.datatable.TypeDataTable;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.util.ApplicationBean;

import lombok.Data;

@ManagedBean(name = "petController", eager = true)
@ViewScoped
public @Data class PetController {
	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean application;
	@ManagedProperty(value = "#{ownerDataTable}")
	private OwnerDataTable ownerDataTable;
	@ManagedProperty(value = "#{typeDataTable}")
	private TypeDataTable typeDataTable;
	@ManagedProperty(value = "#{petDataTable}")
	private PetDataTable petDataTable;
	private Pet pet = new Pet();

	public void save(ActionEvent action) {
		pet.setOwner(ownerDataTable.getSelectedOwner());
		pet.setType(typeDataTable.getSelectedType());

		application.session.save(pet);
		pet = new Pet();	
	}
	
	public void modify(ActionEvent action) {
		Transaction transaction = application.session.beginTransaction();
		application.session.update(petDataTable.getSelectedPet());	
		transaction.commit();
		pet = new Pet();
	}
}
