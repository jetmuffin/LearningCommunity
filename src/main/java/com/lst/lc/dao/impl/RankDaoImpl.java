package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.RankDao;
import com.lst.lc.entities.Rank;

@Repository("rankDao")
public class RankDaoImpl extends BaseDao implements RankDao {

	@Override
	public void addRank(Rank rank) {
		save(rank);
	}

	@Override
	public Rank get(int rankId) {
		return get(Rank.class, rankId);
	}

	@Override
	public void updateRank(int rankId, int integral, String name) {
		String hql = "update Rank as rank set rank.integral = ?, rank.rankName = ? where rank.rankId = ?";
		Query query = query(hql);
		query.setInteger(0, integral).setString(1, name).setInteger(2, rankId).executeUpdate();
	}

	@Override
	public String getRank(int integral) {
		List<Rank> ranks = getRanks();
		for (int i = 0; i < ranks.size() - 1; i++) {
			if (integral >= ranks.get(i).getIntegral()
					&& integral <= ranks.get(i + 1).getIntegral()) {
				return ranks.get(i).getRankName();
			}
		}
		return ranks.get(ranks.size() - 1).getRankName();
	}

	@Override
	public List<Rank> getRanks() {
		String hql = "from Rank as rank order by rank.integral";
		Query query = query(hql);
		List<Rank> ranks = query.list();
		return ranks;
	}

}
