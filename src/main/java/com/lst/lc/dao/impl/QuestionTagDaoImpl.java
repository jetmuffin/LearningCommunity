package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.QuestionTagDao;
import com.lst.lc.entities.QuestionTag;

@Repository("questionTagDao")
public class QuestionTagDaoImpl extends BaseDao implements QuestionTagDao {

	@Override
	public void addTag(QuestionTag tag) {
		save(tag);
	}

	@Override
	public QuestionTag getTag(int tagId) {
		return get(QuestionTag.class, tagId);
	}

	@Override
	public List<QuestionTag> getTags() {
		return getAll("QuestionTag");
	}

	@Override
	public QuestionTag getTagByName(String name) {
		String hql = "from QuestionTag as tag where tag.tagName = ?";
		Query query = query(hql);
		query.setString(0, name);
		List<QuestionTag> tags = query.list();
		if(tags != null)
			return tags.get(0);
		return null;
	}

	@Override
	public List<QuestionTag> getTagsOrderByNum() {
		// TODO Auto-generated method stub
		return null;
	}

}
