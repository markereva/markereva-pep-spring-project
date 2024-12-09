package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.exception.BadRequestException;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private MessageService messageService;

  @PostMapping("register")
  public Account postRegister(@RequestBody Account account) {
      //TODO: process POST request
      int uLen = account.getUsername().length();
      int pLen = account.getPassword().length();
      if (uLen == 0) throw new BadRequestException("Username must not be blank.");
      if (pLen < 4) throw new BadRequestException("Password must be at least 4 characters.");

      return accountService.createAccount(account);
  }
  
}
