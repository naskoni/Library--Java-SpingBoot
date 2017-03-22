package com.naskoni.library.dao;

import java.util.Set;

import com.naskoni.library.entity.Book;

public interface BookDao {

  Book findBook(String name);

  Book findBook(long id);

  Set<Book> findBooks();

  Set<Book> findBooks(String searchedWord, String searchParam);

  Set<Book> findBooks(int searchedNumber, String searchParam);

  void addBook(Book book);

  void deleteBook(Long id);

  void update(Book book);

}
