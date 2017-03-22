package com.naskoni.library.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class representing Entity with properties "id" and "name"
 * 
 * @author Atanas Atanasov
 * @version 1.0.0
 */

@MappedSuperclass
public abstract class AbstractEntityIdName extends AbstractEntityId {

  private static final long serialVersionUID = 4370835284628258652L;

  @Column(name = "NAME", nullable = false)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
