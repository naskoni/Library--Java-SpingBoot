package com.naskoni.library.service.impl;

import static com.naskoni.library.constant.CommonConstants.ASTERISK;
import static com.naskoni.library.constant.CommonConstants.YEAR;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naskoni.library.dao.BookDao;
import com.naskoni.library.entity.Book;
import com.naskoni.library.service.BookService;
import com.naskoni.library.util.ParseUtils;

@Service
public final class BookServiceImpl implements BookService {

  @Autowired
  private BookDao bookDao;

  @Override
  public Book findBook(String name) {
    return bookDao.findBook(name);
  }

  @Override
  public Book findBook(long id) {
    return bookDao.findBook(id);
  }

  @Override
  public Set<Book> findBooks() {
    return bookDao.findBooks();
  }

  @Override
  public void deleteBook(Long id) {
    bookDao.deleteBook(id);
  }

  @Override
  public void addBook(Book book) {
    bookDao.addBook(book);
  }

  @Override
  public void updateBook(Book book) {
    bookDao.update(book);
  }

  @Override
  public Set<Book> findBooks(String searchedWord, String searchParam) {
    if (searchedWord.isEmpty() || ASTERISK.equals(searchedWord)) {
      return bookDao.findBooks();
    }

    if (YEAR.equals(searchParam) && ParseUtils.tryParseNumber(searchedWord)) {
      int searchedNumber = Integer.parseInt(searchedWord);
      return bookDao.findBooks(searchedNumber, searchParam);
    }

    return bookDao.findBooks(searchedWord, searchParam);
  }
}
