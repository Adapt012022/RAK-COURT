package com.adapt.rak.main.service;


import com.adapt.rak.main.Response.ResponseEntitys;
import com.adapt.rak.main.dto.BranchJsonResponse;


public interface RakCourtService {

	ResponseEntitys<?> getBranches();

}
