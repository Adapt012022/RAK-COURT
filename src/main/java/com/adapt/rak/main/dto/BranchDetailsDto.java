package com.adapt.rak.main.dto;

public class BranchDetailsDto {
	private int avgWaitingTime;
	private int avgTransactionTime;
	private int countOfCustomerWaiting;
	private int countOfCustomerServed;
	private String branchName;
	private int branchId;
	public int getAvgWaitingTime() {
		return avgWaitingTime;
	}
	public void setAvgWaitingTime(int avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	public int getAvgTransactionTime() {
		return avgTransactionTime;
	}
	public void setAvgTransactionTime(int avgTransactionTime) {
		this.avgTransactionTime = avgTransactionTime;
	}
	public int getCountOfCustomerWaiting() {
		return countOfCustomerWaiting;
	}
	public void setCountOfCustomerWaiting(int countOfCustomerWaiting) {
		this.countOfCustomerWaiting = countOfCustomerWaiting;
	}
	public int getCountOfCustomerServed() {
		return countOfCustomerServed;
	}
	public void setCountOfCustomerServed(int countOfCustomerServed) {
		this.countOfCustomerServed = countOfCustomerServed;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	

}
