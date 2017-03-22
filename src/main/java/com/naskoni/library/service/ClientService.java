package com.naskoni.library.service;

import java.util.Set;

import com.naskoni.library.entity.Client;

public interface ClientService {

  Client findClient(String name);

  Client findClient(long id);

  Set<Client> findClients();

  Set<Client> findClients(String searchedWord, String searchParam);

  void addClient(Client client);

  void deleteClient(Long id);

  void update(Client client);

}
