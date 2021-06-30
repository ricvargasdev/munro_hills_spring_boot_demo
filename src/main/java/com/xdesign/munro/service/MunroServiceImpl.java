package com.xdesign.munro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xdesign.munro.model.Munro;
import com.xdesign.munro.repository.MunroRepository;
import com.xdesign.munro.searchCriteria.MunroSearchCriteria;
import com.xdesign.munro.specification.MunroSpecification;

/**
 * 
 * @author Ricardo Vargas
 */
@Service
public class MunroServiceImpl implements MunroService {

	@Autowired
	private MunroRepository repository;

	@Override
	public List<Munro> getHills(MunroSearchCriteria searchCriteria, int offset, int maxResults, Sort sort) {
		List<Munro> results;
		
		Specification<Munro> specifications = MunroSpecification.createSpeficiations(searchCriteria);

		if(maxResults > 0 && sort != null) {
			results = repository.findAll(specifications, PageRequest.of(offset, maxResults, sort)).toList();
		}else if(maxResults > 0) {
			results = repository.findAll(specifications, PageRequest.of(offset, maxResults)).toList();
		}else if(sort != null) {
			results = repository.findAll(specifications, sort);
		}else {
			results = repository.findAll(specifications);
		}

		return results;
	}
	
	
}
