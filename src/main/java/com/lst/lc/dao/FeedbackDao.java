package com.lst.lc.dao;

import java.util.List;

import com.lst.lc.entities.Feedback;

public interface FeedbackDao {
        
        public void add(Feedback feedBack);
        
        public List<Feedback> get();
        
        public void changeState(int feedbackId);

}
