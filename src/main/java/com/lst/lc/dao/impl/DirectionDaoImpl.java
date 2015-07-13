package com.lst.lc.dao.impl;

import java.util.List;

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
	public Direction getDirection(String name) {
		return get(Direction.class, name);
	}

	@Override
	public List<Direction> getAllDirections() {
		return getAll("Direction");
	}
}
