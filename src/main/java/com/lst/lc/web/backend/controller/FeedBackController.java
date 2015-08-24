package com.lst.lc.web.backend.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.FeedbackDao;
import com.lst.lc.entities.Feedback;

@Controller
@RequestMapping("/manage/feedback")
public class FeedBackController {
        
        @Autowired
        @Qualifier("feedbackDao")
        private FeedbackDao feedbackDao;
        
        @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
        public String list(Model model,HttpSession session) {
                List<Feedback> feedbacks = feedbackDao.get();
                model.addAttribute("feedbacks", feedbacks);
                return "backend/feedback/list";
        }
        
        @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
        public String read(Model model,HttpSession session, @PathVariable int id) {
                feedbackDao.changeState(id);
                return "redirect:/feedback/feedbacks";
        }

}
