package com.petclinic.controler.datatable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.petclinic.controler.dao.OwnerDAO;
import com.petclinic.filters.SimpleFilter;
import com.petclinic.model.Owner;
import com.petclinic.model.lazy.GenericLazyModel;

import interfaces.FilterInterface;
import lombok.Data;

@ManagedBean(name = "ownerDataTable", eager = true)
@ViewScoped
public @Data class OwnerDataTable {
	private String selectedOwnerFullName;
	private int selectedOwnerId;
	private Owner selectedOwner;
	private GenericLazyModel<Owner> ownerModel;
	@ManagedProperty(value = "#{ownerDAO}")
	private OwnerDAO ownerDAO;
	
	@PostConstruct
	public void init() {
		FilterInterface filter = new SimpleFilter("ownerId");
		ownerModel = new GenericLazyModel<Owner>(filter, ownerDAO);
		ownerModel.setPageSize(10);
	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("wybralem " + selectedOwner.getOwnerFullName());
		selectedOwnerFullName = selectedOwner.getOwnerFullName();
		selectedOwnerId = selectedOwner.getOwnerId();
	}
	
	public LazyDataModel<Owner> getOwnerModel() {
		return ownerModel;
	}	
}
