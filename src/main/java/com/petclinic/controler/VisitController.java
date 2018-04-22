package com.petclinic.controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.Transaction;

import com.petclinic.controler.datatable.PetDataTable;
import com.petclinic.controler.datatable.VetDataTable;
import com.petclinic.controler.datatable.VisitDataTable;
import com.petclinic.model.Pet;
import com.petclinic.model.Visit;
import com.util.ApplicationBean;

import lombok.Data;

@ManagedBean(name = "visitController", eager = true)
@ViewScoped
public @Data class VisitController {
	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean application;
	@ManagedProperty(value = "#{petDataTable}")
	private PetDataTable petDataTable;
	@ManagedProperty(value = "#{vetDataTable}")
	private VetDataTable vetDataTable;
	@ManagedProperty(value = "#{visitDataTable}")
	private VisitDataTable visitDataTable;
	private Visit visit = new Visit();

	public void save(ActionEvent action) {
		visit.setPet(petDataTable.getSelectedPet());
		visit.setVet(vetDataTable.getSelectedVet());

		application.session.save(visit);
		visit = new Visit();		
	}
	
	public void modify(ActionEvent action) {
		Transaction transaction = application.session.beginTransaction();
		application.session.update(visitDataTable.getSelectedVisit());	
		transaction.commit();
		visit = new Visit();
	}
}
