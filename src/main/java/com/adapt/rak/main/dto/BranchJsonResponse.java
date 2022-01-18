package com.adapt.rak.main.dto;

import java.util.List;

public class BranchJsonResponse {
	List<BranchDetailsDto> branchList;
	private int totalAvgWaitingTime;
	private int totalAvgTransactionTime;
	private int totalServedCustomers;
	private int totalWaitingCustomers;
	
	

	public List<BranchDetailsDto> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BranchDetailsDto> branchList) {
		this.branchList = branchList;
	}

	

	public int getTotalAvgWaitingTime() {
		return totalAvgWaitingTime;
	}

	public void setTotalAvgWaitingTime(int totalAvgWaitingTime) {
		this.totalAvgWaitingTime = totalAvgWaitingTime;
	}

	public int getTotalAvgTransactionTime() {
		return totalAvgTransactionTime;
	}

	public void setTotalAvgTransactionTime(int totalAvgTransactionTime) {
		this.totalAvgTransactionTime = totalAvgTransactionTime;
	}

	public int getTotalServedCustomers() {
		return totalServedCustomers;
	}

	public void setTotalServedCustomers(int totalServedCustomers) {
		this.totalServedCustomers = totalServedCustomers;
	}

	public int getTotalWaitingCustomers() {
		return totalWaitingCustomers;
	}

	public void setTotalWaitingCustomers(int totalWaitingCustomers) {
		this.totalWaitingCustomers = totalWaitingCustomers;
	}

	
	

}
