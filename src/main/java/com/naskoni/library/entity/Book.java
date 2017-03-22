package com.naskoni.library.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book extends AbstractEntityIdName implements Serializable {

  private static final long serialVersionUID = 315247235922550809L;

  @Column(name = "AUTHOR")
  private String author;

  @Column(name = "YEAR")
  private int year;

  @Column(name = "ISBN")
  private String isbn;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
}
