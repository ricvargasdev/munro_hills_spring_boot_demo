package com.xdesign.munro.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.xdesign.munro.model.Munro;

/**
 * 
 * @author Ricardo Vargas
 */
@Repository
public interface MunroRepository extends CrudRepository <Munro, Long>, JpaRepository<Munro, Long>, JpaSpecificationExecutor<Munro>{

	/**
	 * @param specification
	 * @param sort
	 */
	List<Munro> findAll(@Nullable Specification<Munro> specification, Sort sort);
}
