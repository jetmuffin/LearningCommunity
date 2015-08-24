package com.lst.lc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.AdminDao;
import com.lst.lc.dao.FeedbackDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.Feedback;

@Repository("feedbackDao")
public class FeedbackDaoImpl extends BaseDao implements FeedbackDao {

        @Override
        public void add(Feedback feedBack) {
                save(feedBack);
        }

        @Override
        public List<Feedback> get() {
                String hql = "from Feedback as feedback where feedback.isHandled = 0";
                Query query = query(hql);
                return query.list();
        }

        @Override
        public void changeState(int feedbackId) {
                String hql = "update Feedback as feedback set feedback.isHandled = 1 where feedback.feedbackId = ?";
                Query query = query(hql).setInteger(0, feedbackId);
                query.executeUpdate();
        }

}
