package com.petclinic.controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.Transaction;

import com.petclinic.controler.datatable.VetDataTable;
import com.petclinic.model.Vet;
import com.util.ApplicationBean;

import lombok.Data;

@ManagedBean(name = "vetController", eager = true)
@ViewScoped
public @Data class VetController {	
	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean application;
	@ManagedProperty(value = "#{vetDataTable}")
	private VetDataTable vetDataTable;
	
	private Vet vet = new Vet();
	
	public void save(ActionEvent action) {
		application.session.save(vet);
		
		vet = new Vet();
	}
	
	public void modify(ActionEvent action) {
		Transaction transaction = application.session.beginTransaction();
		application.session.update(vetDataTable.getSelectedVet());	
		transaction.commit();
		vet = new Vet();
	}
}
