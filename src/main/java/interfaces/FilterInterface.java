package interfaces;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

public interface FilterInterface {
	public Order getOrder(String sortField, SortOrder sortOrder);
	public Predicate getPredicate(String field, Object value);
	public <T> void setJoins(Class<T> chosenClass, CriteriaQuery<T> criteriaQuery, CriteriaBuilder criteriaBuilder);
	public <T> Root<T> getFrom();
	public String getIdName();

}
