package com.naskoni.library.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LEND")
public class Lend extends AbstractEntityId implements Serializable {

  private static final long serialVersionUID = 215247235922550809L;

  @ManyToOne
  @JoinColumn(name = "BOOK")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "CLIENT")
  private Client client;

  @Column(name = "LENDING_DATE")
  private Date lendingDate;

  @Column(name = "RETURN_DATE")
  private Date returnDate;

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Date getLendingDate() {
    return lendingDate;
  }

  public void setLendingDate(Date lendingDate) {
    this.lendingDate = lendingDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }
}
