package com.travel.travtronics.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.model.BeUserModel;

public interface BeUserCustomPaginationRepository {

	public Page<BeUserModel> fetchPagination(Integer orgId, String userName, Pageable pageable, String sortBy,
			SortType sortType);

}
