package com.travel.travtronics.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.travel.travtronics.model.BeUserDetailsModel;

public interface BeUserDetailsModelRepository extends PagingAndSortingRepository<BeUserDetailsModel, Long>{

	
	Optional<BeUserDetailsModel> findByUserId(Long userId);
}
