package com.lst.lc.dao.impl;

import org.hibernate.Query;

import com.lst.lc.dao.RankDao;
import com.lst.lc.entities.Rank;

public class RankDaoImpl extends BaseDao implements RankDao {

	@Override
	public void addRank(Rank rank) {
		save(rank);
	}

	@Override
	public Rank getRank(String name) {
		return get(Rank.class, name);
	}

	@Override
	public void updateRank(int integral, String name) {
		String hql = "update Rank as rank set rank.integral = ? where rank.rankName = ?";
		Query query = query(hql);
		query.setInteger(0, integral).setString(1, name).executeUpdate();
	}

}
