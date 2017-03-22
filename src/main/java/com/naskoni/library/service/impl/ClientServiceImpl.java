package com.naskoni.library.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.dao.ClientDao;
import com.naskoni.library.dao.UserDao;
import com.naskoni.library.entity.Client;
import com.naskoni.library.entity.LibraryUser;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.util.ParseUtils;
import com.naskoni.library.util.UserUtils;

@Service
public final class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientDao clientDao;

  @Autowired
  private UserDao userdao;

  @Override
  public Client findClient(String name) {
    return clientDao.findClient(name);
  }

  @Override
  public Client findClient(long id) {
    return clientDao.findClient(id);
  }

  @Override
  public Set<Client> findClients() {
    return clientDao.findClients();
  }

  @Override
  public Set<Client> findClients(String searchedWord, String searchParam) {
    if (searchedWord.isEmpty() || CommonConstants.ASTERISK.equals(searchedWord)) {
      return clientDao.findClients();
    }

    if (CommonConstants.BIRTHDATE.equals(searchParam) && ParseUtils.tryParseDate(searchedWord)) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        java.util.Date parsed = sdf.parse(searchedWord);
        java.sql.Date searchedDate = new java.sql.Date(parsed.getTime());
        return clientDao.findClients(searchedDate, searchParam);
      } catch (ParseException e) {
        // already caught in ParseUtils
      }
    }

    return clientDao.findClients(searchedWord, searchParam);
  }

  @Override
  public void addClient(Client client) {
    LibraryUser user = userdao.findUser(UserUtils.getUser().getUsername());
    client.setCreatedBy(user);

    clientDao.addClient(client);
  }

  @Override
  public void deleteClient(Long id) {
    clientDao.deleteClient(id);
  }

  @Override
  public void update(Client client) {
    LibraryUser user = userdao.findUser(UserUtils.getUser().getUsername());
    client.setCreatedBy(user);

    clientDao.update(client);
  }
}
