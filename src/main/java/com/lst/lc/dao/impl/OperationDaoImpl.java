package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.OperationDao;
import com.lst.lc.entities.Operation;
import com.lst.lc.utils.ListUtils;

@Repository("operationDao")
public class OperationDaoImpl extends BaseDao implements OperationDao {

	@Override
	public void addOperation(Operation operation) {
		save(operation);
	}

	@Override
	public Operation get(int operationId) {
		return get(Operation.class, operationId);
	}

	@Override
	public void updateOperation(int operationId, String operation, int integral) {
		String hql = "update Operation as operation set operation.operation = ?, operation.integral = ? where operation.operationId = ?";
		Query query = query(hql);
		query.setString(0, operation).setInteger(1, integral)
				.setInteger(2, operationId).executeUpdate();
	}

	@Override
	public List<Operation> getOperations() {
		String hql = "from Opeartion as operation order by operation.integral";
		Query query = query(hql);
		return query.list();
	}

	@Override
	public int getIntegral(String operation) {
		String hql = "from Operation as operation where operation.operation = ?";
		Query query = query(hql);
		query.setString(0, operation);
		List<Operation> operations = query.list();
		if(!ListUtils.isNull(operations)){
			return operations.get(0).getIntegral();
		}
		return 0;
	}

}
