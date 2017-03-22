package com.naskoni.library.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import com.naskoni.library.dao.UserDao;
import com.naskoni.library.entity.LibraryUser;

public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserDao userDao;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(final String username) {

    LibraryUser user = userDao.findUser(username);

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole()));

    return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
  }
}
