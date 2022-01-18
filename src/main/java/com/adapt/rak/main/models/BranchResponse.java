package com.adapt.rak.main.models;

import java.util.List;



public class BranchResponse {
private List<Branch> branchList;
public BranchResponse(List<Branch> branchList) {
    this.branchList = branchList;
    
   
}
public List<Branch> getBranchList() {
	return branchList;
}
public void setBranchList(List<Branch> branchList) {
	this.branchList = branchList;
}



}
