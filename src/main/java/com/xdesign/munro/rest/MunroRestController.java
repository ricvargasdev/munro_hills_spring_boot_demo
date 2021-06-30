package com.xdesign.munro.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xdesign.munro.dto.MunroDTO;
import com.xdesign.munro.exception.MinGreaterThanMaxException;
import com.xdesign.munro.model.Munro;
import com.xdesign.munro.searchCriteria.MunroSearchCriteria;
import com.xdesign.munro.service.MunroService;

/**
 * 
 * @author Ricardo Vargas
 */
@RestController
@ControllerAdvice
public class MunroRestController {

	@Autowired
	MunroService service;
	
	
	@GetMapping(value = "/munro/hills", headers="Accept=application/json")
	public List<MunroDTO> getHills(
			@RequestParam(required = false)
			Optional<String> category,

			@RequestParam(required = false)
			Optional<Integer> total,

			@RequestParam(required = false)
			Optional<Double> minHeight,
			
			@RequestParam(required = false)
			Optional<Double> maxHeight,
			
			@RequestParam(required = false)
			Optional<String> heightSort,
			
			@RequestParam(required = false)
			Optional<String> sortField1,
			
			@RequestParam(required = false)
			Optional<String> sortOrder1,
			
			@RequestParam(required = false)
			Optional<String> sortField2,
			
			@RequestParam(required = false)
			Optional<String> sortOrder2
		) throws MinGreaterThanMaxException {

		if(minHeight.isPresent() && maxHeight.isPresent() && minHeight.get() > maxHeight.get()){
			throw new MinGreaterThanMaxException();
		}

		MunroSearchCriteria searchCriteria = MunroSearchCriteria.builder()
				.category(category)
				.total(total)
				.minHeight(minHeight)
				.maxHeight(maxHeight)
				.build();

		List<MunroDTO> data = new ArrayList<MunroDTO>();
		List<Munro> results;

		int maxResults = (total.isPresent() ? total.get() : 0); 

		String sf1 = (sortField1.isPresent() ? sortField1.get() : null);
		String so1 = (sortOrder1.isPresent() ? getSortOrder(sortOrder1.get()) : null);
	
		String sf2 = (sortField2.isPresent() ? sortField2.get() : null);
		String so2 = (sortOrder2.isPresent() ? getSortOrder(sortOrder2.get()) : null);

		if(sf1 != null && so1 != null && sf2 != null && so2 != null){
			results = service.getHills(searchCriteria, 0, maxResults, Sort.by(Sort.Direction.fromString(so1), sf1).and(Sort.by(Sort.Direction.fromString(so2), sf2)));
				
		}else if(sf1 != null && so1 != null){
			results = service.getHills(searchCriteria, 0, maxResults, Sort.by(Sort.Direction.fromString(so1), sf1));
		}else if(sf2 != null && so2 != null){
			results = service.getHills(searchCriteria, 0, maxResults, Sort.by(Sort.Direction.fromString(so2), sf2));
		}else{
			results = service.getHills(searchCriteria, 0, maxResults, null);
		}
		
		results.forEach(munro -> {
			data.add(new MunroDTO(munro.getName(), munro.getHeightMeters(), munro.getPost1997(), munro.getGridRef()));
		});

		return data;
	}

    @ExceptionHandler(MinGreaterThanMaxException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
	
	private String getSortOrder(String sortOrder) {
		String result = "";
		if(sortOrder.equalsIgnoreCase(Sort.Direction.ASC.toString())) {
			result = Sort.Direction.ASC.toString();
		}else if(sortOrder.equalsIgnoreCase(Sort.Direction.DESC.toString())) {
			result = Sort.Direction.DESC.toString();
		}
		return result;
	}

}
