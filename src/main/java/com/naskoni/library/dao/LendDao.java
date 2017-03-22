package com.naskoni.library.dao;

import java.sql.Date;
import java.util.Set;

import com.naskoni.library.entity.Lend;

public interface LendDao {

  Lend findLend(long id);

  Set<Lend> findLends();

  Set<Lend> findLends(String searchedWord, String searchParam);

  Set<Lend> findLends(Date searchedWord, String searchParam);

  void addLend(Lend lend);

  void update(Lend lend);

}
