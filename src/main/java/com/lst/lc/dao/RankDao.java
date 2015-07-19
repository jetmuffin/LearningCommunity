package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Rank;

public interface RankDao {
	
	public void addRank(Rank rank);
	
	public Rank get(int rankId);
	
	public void updateRank(int rankId, int integral, String name);
	
	public String getRank(int integral);
	
	public List<Rank> getRanks();

}
