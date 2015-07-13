package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Direction;

public interface DirectionDao {
	
	public void addDirection(Direction direction);
	
	public Direction getDirection(String name);
	
	public List<Direction> getAllDirections();

}
