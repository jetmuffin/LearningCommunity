package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.QuestionTag;

public interface QuestionTagDao {
	
	public void addTag(QuestionTag tag);
	
	public QuestionTag getTag(int tagId);
	
	public List<QuestionTag> getTags();
	
	public QuestionTag getTagByName(String name);
	
	public List<QuestionTag> getTagsOrderByNum();
}
