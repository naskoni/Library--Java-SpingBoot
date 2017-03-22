package com.naskoni.library.service;

import java.util.Set;

import com.naskoni.library.entity.Book;

public interface BookService {

  Book findBook(String name);

  Book findBook(long id);

  Set<Book> findBooks();

  void deleteBook(Long id);

  void addBook(Book book);

  void updateBook(Book book);

  Set<Book> findBooks(String searchedWord, String searchParam);
}
