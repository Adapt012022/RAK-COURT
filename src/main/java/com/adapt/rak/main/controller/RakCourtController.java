/**
 * Created by Jasmin on 04/01/2022
 */
package com.adapt.rak.main.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.adapt.rak.main.Response.ResponseEntitys;
import com.adapt.rak.main.Response.Success;
import com.adapt.rak.main.dto.BranchJsonResponse;
import com.adapt.rak.main.service.RakCourtService;

@RestController
public class RakCourtController {
	Logger logger = LoggerFactory.getLogger(RakCourtController.class);
	@Autowired
	RakCourtService rakCourtService;
	
	@CrossOrigin(value = "*")
	@GetMapping("/")
	public String home() {
		return "Welcome to Rak court";
	}
	
	@CrossOrigin(value = "*")
	@GetMapping("/branchDetails")
	public ResponseEntitys<?> getBranchDetails(){
		logger.info("#######Entered into branch details##############33");
		ResponseEntitys<?> response =rakCourtService.getBranches();
//		try {
//			response =rakCourtService.getBranches();
//            return response;
//           
//        } catch (HttpStatusCodeException e) {
//            logger.info("Branch Error " + e);
//            return response;
//        }
		 return response;
	}
	
//	private <T> Success<T> getSuccess(T data) {
//        return new Success<T>(true, HttpStatus.OK.getReasonPhrase(), data);
//    }
//
//    private Success<String> getError(String msg) {
//        return new Success<String>(false, msg, null);
//    }


}
