package com.naskoni.library.dao;

import java.util.Set;

import com.naskoni.library.entity.LibraryUser;

public interface UserDao {

  LibraryUser findUser(String username);

  LibraryUser findUser(Long id);

  Set<LibraryUser> findUsers();

  Set<LibraryUser> findUsers(String searchedWord, String searchParam);

  void addUser(LibraryUser user);

  void updateUser(LibraryUser user);

}
