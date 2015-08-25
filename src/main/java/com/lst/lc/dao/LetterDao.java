package com.lst.lc.dao;

import java.util.Date;
import java.util.List;

import com.lst.lc.entities.Letter;

public interface LetterDao {
	
	public void add(Letter letter);
	
	public void updateState(int fromUid, int toUid, Date date, int State);
	
	/**
	 * 收到的所有站内信
	 * @param uid
	 * @return
	 */
	public List<Letter> getAll(int uid);
	
	public long getUnRead(int uid);
	
}
