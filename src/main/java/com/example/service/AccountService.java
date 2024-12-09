package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

public class AccountService {
  
  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account createAccount(Account account) {
    return accountRepository.save(account);
  }

  public Account loginAccount(String username, String password) {
    return accountRepository.findByUsernameAndPassword(username, password);
  }

}
