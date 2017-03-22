package com.naskoni.library.dao.impl;

import static com.naskoni.library.constant.CommonConstants.ID;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.naskoni.library.dao.LendDao;
import com.naskoni.library.entity.Lend;

@SuppressWarnings("unchecked")
@Repository
public class LendDaoImpl implements LendDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Lend findLend(long id) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Lend.class);
    criteria.add(Restrictions.like(ID, id));
    List<Lend> lends = criteria.list();
    session.close();

    return lends.isEmpty() ? null : lends.get(0);
  }

  @Override
  public Set<Lend> findLends() {
    Session session = sessionFactory.openSession();
    String sql = "FROM Lend";
    Query query = session.createQuery(sql);
    List<Lend> lends = query.list();
    session.close();

    return new HashSet<>(lends);
  }

  @Override
  public Set<Lend> findLends(String searchedWord, String searchParam) {
    Session session = sessionFactory.openSession();
    String sql = String.format("SELECT l from Lend l WHERE l.%s.name=:name", searchParam);
    Query query = session.createQuery(sql);
    query.setParameter("name", searchedWord);
    List<Lend> lends = query.list();
    session.close();

    return new HashSet<>(lends);
  }

  @Override
  public Set<Lend> findLends(Date searchedDate, String searchParam) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Lend.class);
    criteria.add(Restrictions.like(searchParam, searchedDate));
    List<Lend> lends = criteria.list();
    session.close();

    return new HashSet<>(lends);
  }

  @Transactional
  @Override
  public void addLend(Lend lend) {
    Session session = sessionFactory.openSession();
    session.save(lend);
    session.flush();
  }

  @Transactional
  @Override
  public void update(Lend lend) {
    Session session = sessionFactory.openSession();
    session.saveOrUpdate(lend);
    session.flush();
  }
}
