package com.naskoni.library.service;

import java.util.Set;

import com.naskoni.library.entity.LibraryUser;

public interface UserService {

  Set<LibraryUser> findUsers();

  Set<LibraryUser> findUsers(String searchedWord, String searchParam);

  LibraryUser findUser(String username);

  void addUser(LibraryUser user);

  void updateUser(LibraryUser user);

  void deactivateUser(Long id);

}
