package com.naskoni.library.service.impl;

import static com.naskoni.library.constant.CommonConstants.ASTERISK;
import static com.naskoni.library.constant.UserConstants.ROLE;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.naskoni.library.dao.UserDao;
import com.naskoni.library.entity.LibraryUser;
import com.naskoni.library.service.UserService;

@Service
public final class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public Set<LibraryUser> findUsers() {
    return userDao.findUsers();
  }

  @Override
  public LibraryUser findUser(String username) {
    return userDao.findUser(username);
  }

  @Override
  public void addUser(LibraryUser user) {
    String enteredPassword = user.getPassword();
    String encryptedPassword = DigestUtils.md5DigestAsHex(enteredPassword.getBytes());
    user.setPassword(encryptedPassword);
    user.setStatus("active");

    userDao.addUser(user);
  }

  @Override
  public void updateUser(LibraryUser user) {
    String enteredPassword = user.getPassword();
    String encryptedPassword = DigestUtils.md5DigestAsHex(enteredPassword.getBytes());
    user.setPassword(encryptedPassword);

    userDao.updateUser(user);
  }

  @Override
  public void deactivateUser(Long id) {
    LibraryUser user = userDao.findUser(id);
    user.setStatus("deactivated");

    userDao.updateUser(user);
  }

  @Override
  public Set<LibraryUser> findUsers(String searchedWord, String searchParam) {
    if (searchedWord.isEmpty() || ASTERISK.equals(searchedWord)) {
      return userDao.findUsers();
    }

    if (ROLE.equals(searchParam)) {
      return userDao.findUsers(searchedWord.toUpperCase(), searchParam);
    }

    return userDao.findUsers(searchedWord, searchParam);
  }
}
