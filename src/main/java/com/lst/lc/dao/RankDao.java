package com.lst.lc.dao;

import com.lst.lc.entities.Rank;

public interface RankDao {
	
	public void addRank(Rank rank);
	
	public Rank getRank(String name);
	
	public void updateRank(int integral, String name);
	
	public String getRank(int integral);

}
