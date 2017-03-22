package com.naskoni.library.service;

import java.util.Set;

import com.naskoni.library.entity.Lend;

public interface LendService {

  Lend findLend(long id);

  Set<Lend> findLends();

  void addLend(Lend lend);

  void updateLend(Lend lend);

  Set<Lend> findLends(String searchedWord, String searchParam);
}
