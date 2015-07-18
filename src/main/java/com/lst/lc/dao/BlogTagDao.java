package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.BlogTag;

public interface BlogTagDao {
	
	public void addTag(BlogTag tag);
	
	public BlogTag getTag(int tagId);
	
	public List<BlogTag> getTags();
	
	public BlogTag getTagByName(String name);
	
	public List<BlogTag> getTagsOrderByNum();
}
