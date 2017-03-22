package com.naskoni.library.dao;

import java.sql.Date;
import java.util.Set;

import com.naskoni.library.entity.Client;

public interface ClientDao {

  Client findClient(String name);

  Client findClient(long id);

  Set<Client> findClients();

  Set<Client> findClients(String searchedWord, String searchParam);

  Set<Client> findClients(Date searchedWord, String searchParam);

  void addClient(Client client);

  void deleteClient(Long id);

  void update(Client client);

}
