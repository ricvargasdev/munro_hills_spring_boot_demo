package com.xdesign.munro.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.xdesign.munro.model.Munro;
import com.xdesign.munro.model.Munro_;
import com.xdesign.munro.searchCriteria.MunroSearchCriteria;

public final class MunroSpecification {

	public static Specification<Munro> createSpeficiations(MunroSearchCriteria searchCriteria) {
		return getHillsByCategory(searchCriteria.getCategory())
			.and(getHillsBetweenMinAndMaxHeight(searchCriteria.getMinHeight(), searchCriteria.getMaxHeight()))
			.and(getHillsBelowMaxHeight(searchCriteria.getMaxHeight()))
			.and(getHillsAboveMinHeight(searchCriteria.getMinHeight()));
	}

	public static Specification<Munro> getHillsByCategory(Optional<String> category) {
		return (root, query, builder) -> {
			return category.map(c -> builder.equal(root.get(Munro_.post1997), String.valueOf(c))).orElse(null);
		};
	}

	public static Specification<Munro> getHillsBetweenMinAndMaxHeight(Optional<Double> minHeight, Optional<Double> maxHeight) {
		return (root, query, builder) -> {
			return minHeight.map(min -> {
				return maxHeight.map(max -> builder.between(root.get(Munro_.heightMeters), min, max)).orElse(null);
			}).orElse(null);
		};
	}

	public static Specification<Munro> getHillsBelowMaxHeight(Optional<Double> maxHeight) {
		return (root, query, builder) -> {
			return maxHeight.map(max -> builder.lessThanOrEqualTo(root.get(Munro_.heightMeters), max)).orElse(null);
		};
	}
	
	
	public static Specification<Munro> getHillsAboveMinHeight(Optional<Double> minHeight) {
		return (root, query, builder) -> {
			return minHeight.map(min -> builder.greaterThanOrEqualTo(root.get(Munro_.heightMeters), min)).orElse(null);
		};
	}
}
