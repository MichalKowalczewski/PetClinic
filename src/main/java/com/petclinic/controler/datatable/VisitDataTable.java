package com.petclinic.controler.datatable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.petclinic.controler.dao.VisitDAO;
import com.petclinic.filters.VisitFilter;
import com.petclinic.model.Pet;
import com.petclinic.model.Vet;
import com.petclinic.model.Visit;
import com.petclinic.model.lazy.GenericLazyModel;

import interfaces.FilterInterface;
import lombok.Data;

@ManagedBean(name = "visitDataTable", eager = true)
@ViewScoped
public @Data class VisitDataTable {
	private String selectedVisitVetName, selectedVisitPetName;
	private int selectedVisitId;
	private Visit selectedVisit;
	private Pet selectedPet;
	private Vet selectedVet;
	private GenericLazyModel<Visit> visitModel;
	@ManagedProperty(value = "#{visitDAO}")
	private VisitDAO visitDAO;
	

	
	@PostConstruct
	public void init() {
		FilterInterface filter = new VisitFilter();
		visitModel = new GenericLazyModel<Visit>(filter, visitDAO);
		visitModel.setPageSize(10);
	}
	
	public void onRowSelect(SelectEvent event) {
		selectedVisitId = selectedVisit.getVisitId();
		selectedVisitVetName = selectedVisit.getVet().getVetFullName();
		selectedVisitPetName = selectedVisit.getPet().getPetName();
	}
	
	public void onVetSelect(SelectEvent event) {
		selectedVisitVetName = selectedVet.getVetFullName();
		selectedVisit.setVet(selectedVet);
	}
	
	public void onPetSelect(SelectEvent event) {
		selectedVisitPetName = selectedPet.getPetName();
		selectedVisit.setPet(selectedPet);
	}
	
	public LazyDataModel<Visit> getPetModel() {
		return visitModel;
	}	
}
