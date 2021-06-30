package com.xdesign.munro.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.xdesign.munro.model.Munro;
import com.xdesign.munro.searchCriteria.MunroSearchCriteria;

/**
 * Search for the hills depending on the incoming search criteria
 * 
 * @author Ricardo Vargas
 */
public interface MunroService {
	/**
	 * <b>Retrieves hills</b>
	 * 
	 * <ul>
	 * <li>1. Filtering of search by hill category (i.e. Munro, Munro Top or
	 * either). If this information is not provided by the user it should default to
	 * either. This should use the “post 1997” column and if it is blank the hill
	 * should be always excluded from results.</li>
	 *
	 * <li>2. The ability to sort the results by height in meters and alphabetically
	 * by name. For both options it should be possibly to specify if this should be
	 * done in ascending or descending order.</li>
	 *
	 * <li>3. The ability to limit the total number of results returned, e.g. only
	 * show the top 10</li>
	 *
	 * <li>4. The ability to specify a minimum height in meters</li>
	 *
	 * <li>5. The ability to specify a maximum height in meters</li>
	 *
	 * <li>6. Queries may include any combination of the above features and none are
	 * mandatory.</li>
	 *
	 * <li>7. Suitable error responses for invalid queries (e.g. when the max height
	 * is less than the minimum height)</li>
	 *
	 * <li>8. The ability to combine sort criteria in order of preference. For
	 * example: sort by height descending and then alphabetically by name as a
	 * secondary criteria (for when the height is the same)</li>
	 * </ul>
	 * 
	 * @param searchCriteria
	 * @param offset
	 * @param maxResults
	 * @param sort
	 * 
	 */
	public List<Munro> getHills(MunroSearchCriteria searchCriteria, int offset, int maxResults, Sort sort);
}
