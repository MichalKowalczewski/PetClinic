package com.petclinic.filters;

import javax.faces.bean.ManagedProperty;
import javax.persistence.criteria.CriteriaBuilder;

import com.util.ApplicationBean;

public abstract class FilterHelper {
	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean application;
	
	public ApplicationBean getApplication() {
		return application;
	}

	public void setApplication(ApplicationBean application) {
		this.application = application;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		CriteriaBuilder criteriaBuilder = application.getSession().getCriteriaBuilder();
		return criteriaBuilder;
	}

}
