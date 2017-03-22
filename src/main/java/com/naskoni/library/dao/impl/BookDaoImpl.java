package com.naskoni.library.dao.impl;

import static com.naskoni.library.constant.CommonConstants.ID;

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

import com.naskoni.library.dao.BookDao;
import com.naskoni.library.entity.Book;

@SuppressWarnings("unchecked")
@Repository
public class BookDaoImpl implements BookDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Book findBook(String name) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Book.class);
    criteria.add(Restrictions.like("name", name));
    List<Book> books = criteria.list();
    session.close();

    return books.isEmpty() ? null : books.get(0);
  }

  @Override
  public Book findBook(long id) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Book.class);
    criteria.add(Restrictions.like(ID, id));
    List<Book> books = criteria.list();
    session.close();

    return books.isEmpty() ? null : books.get(0);
  }

  @Override
  public Set<Book> findBooks() {
    Session session = sessionFactory.openSession();
    String sql = "FROM Book";
    Query query = session.createQuery(sql);
    List<Book> books = query.list();
    session.close();

    return new HashSet<>(books);
  }

  @Override
  public Set<Book> findBooks(String searchedWord, String searchParam) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Book.class);
    criteria.add(Restrictions.like(searchParam, "%" + searchedWord + "%"));
    List<Book> books = criteria.list();
    session.close();

    return new HashSet<>(books);
  }

  @Override
  public Set<Book> findBooks(int searchedNumber, String searchParam) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Book.class);
    criteria.add(Restrictions.like(searchParam, searchedNumber));
    List<Book> books = criteria.list();
    session.close();

    return new HashSet<>(books);
  }

  @Transactional
  @Override
  public void addBook(Book book) {
    Session session = sessionFactory.openSession();
    session.save(book);
    session.flush();
  }

  @Transactional
  @Override
  public void deleteBook(Long id) {
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Book.class);
    criteria.add(Restrictions.eq(ID, id)).uniqueResult();
    List<Book> row = criteria.list();
    session.delete(row.get(0));
    session.flush();
  }

  @Transactional
  @Override
  public void update(Book book) {
    Session session = sessionFactory.openSession();
    session.saveOrUpdate(book);
    session.flush();
  }
}
