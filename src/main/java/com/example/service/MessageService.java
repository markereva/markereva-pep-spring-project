package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
  private MessageRepository messageRepository;

  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message createMessage(Message message) {
    return messageRepository.save(message);
  }

  public Message getMessageById(int id) {
    return messageRepository.findById(id).orElse(null);
  }

  public List<Message> getMessagesByPostedBy(int id) {
    return messageRepository.findByPostedBy(id);
  }

  public List<Message> getAllMessages() {
    return messageRepository.findAll();
  }

  @Transactional
  public int deleteMessageById(int id) {
    return messageRepository.deleteById(id);
  }

  @Transactional
  public int updateMessageById(int id, String text) {
    return messageRepository.updateMessageById(id, text);
  }
  
}
