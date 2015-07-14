package com.lst.lc.test.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.junit.Test;

import com.lst.lc.entities.Direction;

public class DirectionDaoTest extends BaseTestDao {

	public Direction getDirection(int id) {
		return get(Direction.class, id);
	}
	
	public List<Direction> getAllDirections() {
		return getAll("Direction");
	}
	
	public void update(int directionId, String name, String description,
			String enabled) {
		String hql = "update Direction as direction set direction.directionName = ?, direction.description = ?, direction.enabled = ? where directionId = ?";
		Query query = query(hql);
		query.setString(0, name).setString(1, description)
				.setString(2, enabled).setInteger(3, directionId)
				.executeUpdate();
	}
	
	public void delete(int directionId) {
		String hql = "delete Direction as direction where direction.directionId = ?";
		Query query = query(hql);
		query.setInteger(0, directionId).executeUpdate();
	}
	
	public List<Direction> getEnabledDirections() {
		String hqlString = "from Direction as direction where direction.enabled=?";
		Query query = query(hqlString);
		query.setString(0, "1");
		List<Direction> ts = query.list();
		return ts;
	}
	
	@Test
	public void getDirectionTest() {
		Direction direction = getDirection(2);
		System.out.println(direction.getDescription());
	}
	
	@Test
	public void getAllDirectionsTest() {
		List<Direction> directions = getAllDirections();
		int n = directions.size();
		for(Direction direction : directions){
			System.out.println(direction.getDirectionId());
		}
	}
}
