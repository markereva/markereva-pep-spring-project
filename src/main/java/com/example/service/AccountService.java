package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
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
