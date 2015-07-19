package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Operation;

public interface OperationDao {
	
	public void addOperation(Operation operation);
	
	public Operation get(int operationId);
	
	public void updateOperation(int operationId, String operation, int integral);
	
	public List<Operation> getOperations();
	
	public int getIntegral(String operation);

}
