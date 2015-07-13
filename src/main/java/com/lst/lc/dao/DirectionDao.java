package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Direction;

public interface DirectionDao {
	
	public void addDirection(Direction direction);
	
	public Direction getDirection(int id);
	
	public List<Direction> getAllDirections();
	
	public void update(Direction direction);
	
	public void delete(Direction direction);

}
