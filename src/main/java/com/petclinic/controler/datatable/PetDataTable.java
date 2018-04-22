package com.petclinic.controler.datatable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.petclinic.controler.dao.PetDAO;
import com.petclinic.filters.PetFilter;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.Type;
import com.petclinic.model.lazy.GenericLazyModel;

import interfaces.FilterInterface;
import lombok.Data;

@ManagedBean(name = "petDataTable", eager = true)
@ViewScoped
public @Data class PetDataTable {
	private String selectedPetName, selectedPetTypeName, selectedPetOwnerName;
	private int selectedPetId;
	private Pet selectedPet;
	private Type selectedType;
	private GenericLazyModel<Pet> petModel;
	@ManagedProperty(value = "#{petDAO}")
	private PetDAO petDAO;
	
	@PostConstruct
	public void init() {
		FilterInterface filter = new PetFilter();
		petModel = new GenericLazyModel<Pet>(filter, petDAO);
		petModel.setPageSize(10);
	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("wybralem " + selectedPet.getPetName());
		selectedPetName = selectedPet.getPetName();
		selectedPetId = selectedPet.getPetId();
		selectedPetTypeName = selectedPet.getType().getTypeName();
		selectedPetOwnerName = selectedPet.getOwner().getOwnerFullName();
	}
	
	public void onTypeSelect(SelectEvent event) {
		selectedPetTypeName = selectedType.getTypeName();
		selectedPet.setType(selectedType);
	}
	
	public LazyDataModel<Pet> getPetModel() {
		return petModel;
	}	
}