package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.BadRequestException;
import com.example.exception.UnauthorizedException;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



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
      int uLen = account.getUsername().length();
      int pLen = account.getPassword().length();
      if (uLen == 0) throw new BadRequestException("Username must not be blank.");
      if (pLen < 4) throw new BadRequestException("Password must be at least 4 characters.");

      return accountService.createAccount(account);
  }

  @PostMapping("login")
  public Account postLogin(@RequestBody Account account) {
      Account match = accountService.loginAccount(account);
      if (match == null) throw new UnauthorizedException("Invalid credentials.");

      return match;
  }
  
  @PostMapping("messages")
  public Message postMessage(@RequestBody Message message) {
    int textLen = message.getMessageText().length();
    if (textLen == 0) throw new BadRequestException("message_text must not be blank.");
    if (textLen > 255) throw new BadRequestException("message_text must not be over 255 characters.");

    // maybe repository .save() call will throw exception if postedBy relation
    // is invalid, so we don't need to query whether it exists here
    // Account match = accountService.getUserById(message.getPostedBy());
    // if (match == null) throw new UnauthorizedException("Author not found in database.");
    
    return messageService.createMessage(message);
  }

  @GetMapping("messages")
  public List<Message> getAllMessages() {
      return messageService.getAllMessages();
  }

  @GetMapping("messages/{id}")
  public Message getMessageById(@PathVariable("id") int id) {
      return messageService.getMessageById(id);
  }

  @DeleteMapping("messages/{id}")
  public int deleteMessageById(@PathVariable("id") int id) {
    return messageService.deleteMessageById(id);
  }

  @PatchMapping("messages/{id}")
  public int updateMessageById(@PathVariable("id") int id, @RequestBody Message message) {
    // Update query with invalid id where clause will return 0, not error
    int rowsChanged = messageService.updateMessageById(id, message);
    if (rowsChanged == 0) throw new BadRequestException("message_id must refer to an existing message.");
    return rowsChanged;
  }

}
