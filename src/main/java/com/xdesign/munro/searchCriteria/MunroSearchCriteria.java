package com.xdesign.munro.searchCriteria;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Wrapper class to store the Search Criteria for Munros.
 * @author Ricardo Vargas
 *
 */
@Getter
@Setter
@Builder
public class MunroSearchCriteria {

	private Optional<String> category;
	private Optional<Integer> total;
	private Optional<Double> minHeight;
	private Optional<Double> maxHeight;

}
