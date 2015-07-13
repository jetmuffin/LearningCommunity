package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Direction;

@Repository("directionDao")
public class DirectionDaoImpl extends BaseDao implements DirectionDao {

	@Override
	public void addDirection(Direction direction) {
		save(direction);
	}

	@Override
	public Direction getDirection(int id) {
		return get(Direction.class, id);
	}

	@Override
	public List<Direction> getAllDirections() {
		return getAll("Direction");
	}

	@Override
	public void update(int directionId, String name, String description,
			String enabled) {
		String hql = "update Direction as direction set direction.directionName = ?, direction.description = ?, direction.enabled = ? where directionId = ?";
		Query query = query(hql);
		query.setString(0, name).setString(1, description)
				.setString(2, enabled).setInteger(3, directionId)
				.executeUpdate();
	}

	@Override
	public void delete(int directionId) {
		String hql = "delete Direction as direction where direction.directionId = ?";
		Query query = query(hql);
		query.setInteger(0, directionId).executeUpdate();
	}
}
