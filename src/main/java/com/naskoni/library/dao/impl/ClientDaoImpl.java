package com.naskoni.library.dao.impl;

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

import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.dao.ClientDao;
import com.naskoni.library.entity.Client;

@SuppressWarnings("unchecked")
@Repository
public class ClientDaoImpl implements ClientDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Client findClient(String name) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Client.class);
    criteria.add(Restrictions.like("name", name));
    List<Client> clients = criteria.list();
    session.close();

    return clients.isEmpty() ? null : clients.get(0);
  }

  @Override
  public Client findClient(long id) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Client.class);
    criteria.add(Restrictions.like(CommonConstants.ID, id));
    List<Client> clients = criteria.list();
    session.close();

    return clients.isEmpty() ? null : clients.get(0);
  }

  @Override
  public Set<Client> findClients() {
    Session session = sessionFactory.openSession();
    String sql = "FROM Client";
    Query query = session.createQuery(sql);
    List<Client> clients = query.list();
    session.close();

    return new HashSet<>(clients);
  }

  @Override
  public Set<Client> findClients(String searchedWord, String searchParam) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Client.class);
    criteria.add(Restrictions.like(searchParam, "%" + searchedWord + "%"));
    List<Client> clients = criteria.list();
    session.close();

    return new HashSet<>(clients);
  }

  @Override
  public Set<Client> findClients(Date searchedDate, String searchParam) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Client.class);
    criteria.add(Restrictions.like(searchParam, searchedDate));
    List<Client> clients = criteria.list();
    session.close();

    return new HashSet<>(clients);
  }

  @Transactional
  @Override
  public void addClient(Client client) {
    Session session = sessionFactory.openSession();
    session.save(client);
    session.flush();
  }

  @Transactional
  @Override
  public void deleteClient(Long id) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Client.class);
    criteria.add(Restrictions.eq(CommonConstants.ID, id)).uniqueResult();
    List<Client> row = criteria.list();
    session.delete(row.get(0));
    session.flush();
  }

  @Transactional
  @Override
  public void update(Client client) {
    Session session = sessionFactory.openSession();
    session.saveOrUpdate(client);
    session.flush();
  }
}
