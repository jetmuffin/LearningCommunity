package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.BlogTagDao;
import com.lst.lc.entities.BlogTag;

@Repository("blogTagDao")
public class BlogTagDaoImpl extends BaseDao implements BlogTagDao {

	@Override
	public void addTag(BlogTag tag) {
		save(tag);
	}

	@Override
	public BlogTag getTag(int tagId) {
		return get(BlogTag.class, tagId);
	}

	@Override
	public List<BlogTag> getTags() {
		return getAll("BlogTag");
	}

	@Override
	public BlogTag getTagByName(String name) {
		String hql = "from BlogTag as tag where tag.tagName = ?";
		Query query = query(hql);
		query.setString(0, name);
		List<BlogTag> tags = query.list();
		if(tags != null)
			return tags.get(0);
		return null;
	}

	@Override
	public List<BlogTag> getTagsOrderByNum() {
		String hql = "from BlogTag as tag order by tag.number desc";
		Query query = query(hql);
		return query.list();
	}

}
