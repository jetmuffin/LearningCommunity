package com.lst.lc.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lst.lc.dao.LetterDao;
import com.lst.lc.entities.Letter;
import com.lst.lc.entities.LetterId;


@Repository("letterDao")
public class LetterDaoImpl extends BaseDao implements LetterDao {

        @Override
        public void add(Letter letter) {
                save(letter);
                getSession().flush();
        }

        @Override
        public void updateState(int fromUid, int toUid, String date, int State) {
                String hql = "update Letter as letter set letter.state = ? where letter.id.fromUid = ? and letter.id.toUid = ? and letter.id.time = ?";
                Query query = query(hql).setInteger(0, State).setInteger(1, fromUid).setInteger(2, toUid).setString(3, date);
                query.executeUpdate();
        }

        @Override
        public List<Letter> getAll(int uid) {
                String hql = "from Letter as letter where letter.id.toUid = ? order by letter.state desc";
                Query query = query(hql).setInteger(0, uid);
                return query.list();
        }

        @Override
        public int getUnRead(int uid) {
                String hql = "from Letter as letter where letter.id.toUid = ? and letter.state = 0";
                Query query = query(hql).setInteger(0, uid);
                return query.list().size();
        }


}
