package com.petclinic.controler.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.swing.event.ChangeEvent;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

import com.petclinic.controler.dao.VisitDAO;
import com.petclinic.controler.datatable.VetDataTable;
import com.petclinic.model.Visit;

import lombok.Data;

@ManagedBean(name = "vetSchedule", eager = true)
@ViewScoped
public @Data class VetSchedule{
	@ManagedProperty(value = "#{vetDataTable}")
	private VetDataTable vetDataTable;
	@ManagedProperty(value = "#{visitDAO}")
	private VisitDAO visitDAO;
	private ScheduleModel lazyEventModel;

	public ScheduleModel getLazyEventModel() {

		lazyEventModel = new LazyScheduleModel() {
			@Override
			public void loadEvents(Date start, Date end) {
				List<Visit> visits = null;
				if (vetDataTable.getSelectedVet() == null) {
					visits = visitDAO.getVisitCalendar("stop",
							"stop", start, end);
				} else {
					visits = visitDAO.getVisitCalendar(vetDataTable.getSelectedVetFirstName(),
							vetDataTable.getSelectedVetLastName(), start, end);
				}
				for (int i = 0; i < visits.size(); i++) {
					addEvent(new DefaultScheduleEvent("Pet: " + visits.get(i).getPet().getPetName() + " Owner: " +visits.get(i).getPet().getOwner().getOwnerFullName(), visits.get(i).getVisitDate(),
							addMinutes(visits.get(i).getVisitDate())));
				}
			}
		};
		return lazyEventModel;
	}

	public Date addMinutes(Date date) {
		final long ONE_MINUTE_IN_MILLIS = 60000;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long t = calendar.getTimeInMillis();
		return new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
	}
	
	public void changeSchedule(ChangeEvent event) {
		vetDataTable.getSelectedVetFullName();
	}
}