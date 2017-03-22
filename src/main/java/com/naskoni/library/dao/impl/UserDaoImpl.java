package com.naskoni.library.dao.impl;

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

import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.dao.UserDao;
import com.naskoni.library.entity.LibraryUser;

@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public LibraryUser findUser(String username) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(LibraryUser.class);
    criteria.add(Restrictions.like("username", username));
    List<LibraryUser> users = criteria.list();
    session.close();

    return users.isEmpty() ? null : users.get(0);
  }

  @Override
  public LibraryUser findUser(Long id) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(LibraryUser.class);
    criteria.add(Restrictions.like(CommonConstants.ID, id));
    List<LibraryUser> users = criteria.list();
    session.close();

    return users.isEmpty() ? null : users.get(0);
  }

  @Override
  public Set<LibraryUser> findUsers() {
    Session session = sessionFactory.openSession();
    String sql = "FROM LibraryUser";
    Query query = session.createQuery(sql);
    List<LibraryUser> users = query.list();
    session.close();

    return new HashSet<>(users);
  }

  @Override
  public Set<LibraryUser> findUsers(String searchedWord, String searchParam) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(LibraryUser.class);
    criteria.add(Restrictions.like(searchParam, "%" + searchedWord + "%"));
    List<LibraryUser> users = criteria.list();
    session.close();

    return new HashSet<>(users);
  }

  @Transactional
  @Override
  public void addUser(LibraryUser user) {
    Session session = sessionFactory.openSession();
    session.save(user);
    session.flush();
  }

  @Transactional
  @Override
  public void updateUser(LibraryUser user) {
    Session session = sessionFactory.openSession();
    session.saveOrUpdate(user);
    session.flush();
  }
}
