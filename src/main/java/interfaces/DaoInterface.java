package interfaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

public interface DaoInterface {
	
	public List<?> getData(Integer start);
	
	public Map<Integer, ?> getDataMap(Integer first);
	
	public List<?> getSpecificData(Integer first, String sortField, SortOrder sortOrder, Map<String, Object> filters, FilterInterface filter);
	
	public Integer countSpecificObjects(Map<String, Object> filters, FilterInterface filter);
	
}
