package com.travel.travtronics.repository;

import java.util.List;

import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.dto.UserSearchResponse;

public interface UserCustomSearchRepository {

	public List<UserSearchResponse> findBySearchParameters(UserSearchRequestDto model);

}
