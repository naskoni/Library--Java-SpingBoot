package com.naskoni.library.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Client extends AbstractEntityIdName implements Serializable {

  private static final long serialVersionUID = -7265644640817017364L;

  @Column(name = "PID")
  private String pid;

  @Column(name = "BIRTHDATE")
  private Date birthdate;

  @ManyToOne
  @JoinColumn(name = "CREATED_BY")
  private LibraryUser createdBy;

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public LibraryUser getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(LibraryUser createdBy) {
    this.createdBy = createdBy;
  }
}
