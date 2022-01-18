/**
 * Created by Jasmin on 04/01/2022
 */
package com.adapt.rak.main.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
//import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adapt.rak.main.Response.ResponseCode;
import com.adapt.rak.main.Response.ResponseDescription;
import com.adapt.rak.main.Response.ResponseEntitys;
import com.adapt.rak.main.Response.ResponseMessage;
import com.adapt.rak.main.configuration.AppProperties;
import com.adapt.rak.main.dto.BranchDetailsDto;
import com.adapt.rak.main.dto.BranchJsonResponse;
import com.adapt.rak.main.models.Branch;
import com.adapt.rak.main.models.Variable;


//import com.google.gson.Gson;

@Service
public class RakCourtServiceImpl implements RakCourtService {
	Logger logger = LoggerFactory.getLogger(RakCourtServiceImpl.class);
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AppProperties properties;

	@Override
	public ResponseEntitys<?> getBranches() {
		ResponseEntitys<?> response=null;
		System.out.println(properties.getBranchUrl());
		// Gson gson=new Gson();
		BranchJsonResponse branchJsonResp = new BranchJsonResponse();
		try {
			ResponseEntity<Branch[]> branchList = restTemplate.exchange(properties.getBranchUrl(), HttpMethod.GET,
					new HttpEntity<>(createHeaders(properties.getUsername(), properties.getPassword())), Branch[].class);
			// System.out.println(branchList.getBody());
			// System.out.println(gson.toJson(branchList.getBody()));
			Objects.requireNonNull(branchList.getBody(), "Empty Date List");
			List<BranchDetailsDto> btDetailDto = new ArrayList<BranchDetailsDto>();
			List<BranchJsonResponse> jsonResponse = new ArrayList<BranchJsonResponse>();
			
			double count = 0.0;
			int servedTot = 0, trtTot = 0, atrtTot = 0, awtTot = 0, totServedCustomer = 0, totWt = 0;
			ResponseEntity<Variable[]> variableData = restTemplate.exchange(properties.getVariables(), HttpMethod.GET,
					new HttpEntity<>(createHeaders(properties.getUsername(), properties.getPassword())), Variable[].class);
			Objects.requireNonNull(variableData.getBody(), "Empty Date List");
//			List<String> branchStafInfoWithIds = Arrays.asList(branchList.getBody()).stream()
//					.map(branch -> "branchStatInfo_" + branch.getId()).collect(Collectors.toList()); //eg:branchStatInfo_1,branchStatInfo_2 etc
			
			
//			Map<String, Variable> variablesMap = Arrays.asList(variableData.getBody()).stream()
//					.filter(varObj -> branchStafInfoWithIds.contains(varObj.getName()))
//					.collect(Collectors.toMap(var -> var.getName(), var -> var)); //eg: key:branchStatInfo_1 ,value= {name :branchStatInfo_1,value:2366366@737@..
			int wtTot = 0;
			int wtBelowTot = 0,
					wtAboveTot = 0;
			
			
//			
			
			// ############BRANCH ITERATION STARTS HERE ##################
			for (Branch branchObj : branchList.getBody()) {
				BranchDetailsDto branchdetailsDto = new BranchDetailsDto();
				String varValue = null;
				String[] variableValueArray = null;
				int countOfWaitingCutomer = 0, countOfServedCustomer = 0, avgWtTime = 0, avgTransTime = 0,   branchId = branchObj.getId();
				
				String currentDate=getCurrentDate();
				for (Variable varObj : variableData.getBody()) {
					String branchStatInfo = "branchStatInfo_" + branchId;
				if (varObj.getName().equals(branchStatInfo)) {
						varValue = varObj.getValue();
						System.out.println("varValue=" + varValue);
						//if (variablesMap.containsKey("branchStatInfo_" + branchId)) {
							//varValue = variablesMap.get("branchStatInfo_" + branchId).getValue();
							variableValueArray = varValue.split("@");
							String val1 = variableValueArray[0];
							int val2 = getValue(variableValueArray[2]);
							int val3 = getValue(variableValueArray[3]);
							int val4 = getValue(variableValueArray[4]);
							int val5 = getValue(variableValueArray[5]);
							int val6 = getValue(variableValueArray[6]);
							if(currentDate.equals(val1)) {
								countOfServedCustomer = val5; // customer served in each branch
								totServedCustomer += countOfServedCustomer; //  Total served customer count of whole branch(adding)
								countOfWaitingCutomer = val2; // Customer waiting count in each branch
								totWt += countOfWaitingCutomer; //Total waiting customer count of whole branch

								avgWtTime = getAverage(val4, val2); //Average waiting time of each branch
								/****Commented for awtTot    value issue  *****/
//								if (avgWtTime > 0)
//									count++;
//								wtBelowTot = (val2 - val3); // total waiting below val2- val3
//								wtAboveTot = val3; // tot waiting above sl val3
//								wtTot = val4; // tot waiting time val4
//								awtTot += getAverage(wtTot, wtBelowTot + wtAboveTot);// Total average waiting time of whole
//																						// branches(waiting time available branches
//																						// only)*/
								/********Commented for awtTot    value issue  End *************/
								
								
								wtBelowTot += (val2 - val3); // total waiting below
					            wtAboveTot += val3; // tot waiting above sl
					            wtTot += val4;// Total average waiting time of whole
								// branches(waiting time available branches
								// only)
								

						
								avgTransTime = getAverage(val6, val5); // Average transaction time in each branch
								servedTot += val5; // Getting served total
								trtTot += val6; // getting transaction total
							}
							
						}
						//break;
					//}
				}
			
				logger.info(
						"branch name:-" + branchObj.getName() + "  branch Id:=" + branchObj.getId() + "  avgwaiting time:-"
								+ avgWtTime + "  avgTransactionTime:-" + avgTransTime + "  Count of serverd Customer:-"
								+ countOfServedCustomer + " count of waiting customer:-" + countOfWaitingCutomer);
				branchdetailsDto.setBranchName(branchObj.getName());
				branchdetailsDto.setBranchId(branchId);
				branchdetailsDto.setAvgWaitingTime(avgWtTime);
				branchdetailsDto.setAvgTransactionTime(avgTransTime);
				branchdetailsDto.setCountOfCustomerServed(countOfServedCustomer);
				branchdetailsDto.setCountOfCustomerWaiting(countOfWaitingCutomer);
				btDetailDto.add(branchdetailsDto);

			}
			// ##############BRANCH ITERATION ENDS HERE ##############################		
			
			logger.info("Total waiting customers:=" + totWt + "  Total served customers:=" + totServedCustomer
					+ "  Avgwaiting time total:=" + (int) Math.round((awtTot / count)) + " AVg transaction time total:="
					+ (int) Math.round((atrtTot)));
			awtTot = getAverage(wtTot, wtBelowTot + wtAboveTot);
			atrtTot = getAverage(trtTot, servedTot); // TOTAL AVERAGE TRANSACTION TIME OF WHOLE BRANCHES
			
			branchJsonResp.setBranchList(btDetailDto);
			branchJsonResp.setTotalServedCustomers(totServedCustomer);
			branchJsonResp.setTotalWaitingCustomers(totWt);
			//branchJsonResp.setTotalAvgWaitingTime((int) Math.round((awtTot / count))); //Commented for awtTot Value issue
			branchJsonResp.setTotalAvgWaitingTime((int) Math.round((awtTot)));
			// branchJsonResp.setTotalAvgWaitingTime((int)(awtTot / count));
			branchJsonResp.setTotalAvgTransactionTime((int) Math.round((atrtTot)));
			response=new ResponseEntitys<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS,ResponseDescription.SUCCESS,branchJsonResp);
			//return branchJsonResp;
		}catch(Exception e) {
			logger.error("error"+e);
			response=new ResponseEntitys<>(ResponseCode.UN_AUTHORIZED, ResponseMessage.FAILED,ResponseDescription.FAILED);
		}
		
		
		//jsonResponse.add(branchJsonResp);
		

		return response;
	}

	
	
	private String getCurrentDate() {
		SimpleDateFormat objSDF = new SimpleDateFormat("YYYY-MM-DD");
		Date date = new Date();
		//System.out.println("Date1 : " + objSDF.format(date));
		String forMattedDate = objSDF.format(date);
		forMattedDate = forMattedDate.replace("-", "");
		//System.out.println("ReplacedString:=" + forMattedDate);
		return forMattedDate;

	}

	private int getAverage(int timeInSeconds, int count) {
		// TODO Auto-generated method stub
		int avg = 0;
		if (count > 0) {
			avg = timeInSeconds / count;
			avg = avg / 60;
			// OR mm:ss
		}
		return avg;
	}

	private int getValue(String value) {
		int val = Integer.parseInt(value);
		return val;

	}

	HttpHeaders createHeaders(final String username, final String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
}
